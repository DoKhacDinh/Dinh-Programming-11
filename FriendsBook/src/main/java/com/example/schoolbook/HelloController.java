package com.example.schoolbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class HelloController {
    // Declare everything featuring on Scene Builder
    public TextField textGetName; // Input field for friend's name
    public TextField textGetAge; // Input field for friend's age
    public TextField textGetGame; // Input field for friend's favorite game
    public Button deleteFriendButton;  // Button to delete a selected friend
    public Button displayAllFriendButton;  // Button to display all friends' details
    public Button quitButton; // Button to quit the application
    public Button addFriendButton; // Button to add a new friend
    public Label notificationLabel; // Label to show notifications/messages to the user
    public TextArea displayDetailArea; // TextArea to display friend details
    public ListView<Friend> friendsListView; // ListView to display all friends in a list
    public HBox rootContainer; // Root container
    private ObservableList<Friend> observableFriendsList = FXCollections.observableArrayList(); // Data model for storing friends

    // Observable List is used because It automatically updates the ListView when data changes (add/remove).
    // If we don't use Observable List, we have to add/remove object manually from the List View, which is time-consuming and inefficient

    @FXML
    private void initialize() {
        // Bind the ListView to the observable list of friends
        friendsListView.setItems(observableFriendsList);

        // Add a listener to handle the selection of a friend from the ListView ( The friend that the user clicks on the List View, the computer will observe and do the task below)
        friendsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Display details of the selected friend in the "Friend Detail" TextArea
                displayDetailArea.setText("Name: " + newValue.getName() + "\n" + "Age: " + newValue.getAge() + "\n" + "Game: " + newValue.getGame());
            }
        });

        // These codes below will allow users to interact with the program easier by resetting their List View selection when they do other tasks

        // Listen for mouse click events on the root container
        rootContainer.setOnMouseClicked(event -> {
            // Check if the click was not on the ListView and the ListView is not being hovered
            // event.getTarget() gets the element that was clicked. The condition checks if it's not the ListView.
            // friendsListView.isHover() checks if the ListView is being hovered over.
            if (!friendsListView.equals(event.getTarget()) && !friendsListView.isHover()) {

                // Clear the current selection in the ListView
                friendsListView.getSelectionModel().clearSelection();
            }
        });
    }

    // Method executed when user click "Add Friend" button
    @FXML
    private void addFriend (MouseEvent event) {

        friendsListView.getSelectionModel().clearSelection(); // Reset the selection in the List View ( avoiding errors)

        String friendName = textGetName.getText(); // Get name input
        String friendAge = textGetAge.getText(); // Get age input
        String friendGame = textGetGame.getText(); // Get game input

        // Check whether any input fields are empty
        if (friendName.trim().isEmpty() || friendGame.trim().isEmpty() || friendAge.trim().isEmpty()) {
            notificationLabel.setText("Please enter your friend information"); // Notify user to fill all fields
            return; // Stop the program here
        }

        // Check whether the age field is not a number
        try {

            int age = Integer.parseInt(friendAge.trim());  // Parse age input field to an integer
            observableFriendsList.add(new Friend(friendName.trim(),age,friendGame.trim()));  // Add a new Friend object to the observable list

            // Notify user of successful addition and clear input fields
            notificationLabel.setText("Friend added successfully!");
            textGetName.clear();
            textGetAge.clear();
            textGetGame.clear();

         //  Catch the invalid input ( not a number )
        } catch (NumberFormatException e) {
            // Notify user if age input is invalid (not a number) and clear age input field
            notificationLabel.setText("Age must be a number!"); // Notify user of their mistake
            textGetAge.clear(); // clear the age input field
        }
    }

        // Method executed when user clicks "Display All Friends" button
        @FXML
        private void displayAllFriends (MouseEvent event){
        friendsListView.getSelectionModel().clearSelection(); // Reset the selection in the List View ( avoiding errors)

        // Use StringBuilder to construct the display content
        StringBuilder friendInformationDisplay = new StringBuilder();

        for(Friend friend : observableFriendsList){ // loop over each friend in the observable list

            // Display friend's information in a proper String format
            friendInformationDisplay.append("Name: ").append(friend.getName()).append("\n");
            friendInformationDisplay.append("Age: ").append(friend.getAge()).append("\n");
            friendInformationDisplay.append("Game: ").append(friend.getGame()).append("\n\n");
        }
        // Display all friend details in the "Friend Detail" TextArea
        displayDetailArea.setText(friendInformationDisplay.toString());
    }

    // Method executed when user clicks "Delete Friend" button
    @FXML
    private void deleteFriend (MouseEvent event) {
        // Get the selected friend from the ListView ( what friend user clicked on in the ListView)
        Friend selectedFriend = friendsListView.getSelectionModel().getSelectedItem();

        // If user clicked something in the ListView => selectedFriend is not null. The condition below is true
        if (selectedFriend != null) {
            observableFriendsList.remove(selectedFriend); // Remove the friend from the observable list
            notificationLabel.setText("Friend deleted successfully!"); // Notify user of successful deletion
            return;

        } else {
            // Notify user to select a friend if none is selected
            notificationLabel.setText("Please select a friend to delete.");
        }
    }

    // Method executed when user clicks "Clear" button
    @FXML
    private void clearDetailArea (MouseEvent event){
        displayDetailArea.clear(); // Clear the "Friend Detail" TextArea
        friendsListView.getSelectionModel().clearSelection(); // Reset the selection in the List View ( avoiding errors), allowing listener to work properly

    }

    // Method executed when user clicks "quit" button
    @FXML
    private void quit (MouseEvent event){
        // Terminate the program
        System.exit(0);
    }
    }

