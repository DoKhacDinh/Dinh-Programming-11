package com.example.schoolbook;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HelloController {
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
    public Button saveButton; // Button to save all friends to a file
    public Button loadButton; // Button to load all friends from a file
    private String filePath; // A String to store the file's address after the users load their file
    protected ObservableList<Friend> observableFriendsList = FXCollections.observableArrayList(); // Data model for storing friends

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
    private void addFriend(MouseEvent event) {

        friendsListView.getSelectionModel().clearSelection(); // Reset the selection in the List View ( avoiding errors)

        String friendName = textGetName.getText(); // Get name input
        String friendAge = textGetAge.getText(); // Get age input
        String friendGame = textGetGame.getText(); // Get game input

        // Check whether any input fields are empty
        if (friendName.trim().isEmpty() || friendGame.trim().isEmpty() || friendAge.trim().isEmpty()) {
            showTemporaryNotification("Please enter your friend information", 4); // Notify user to fill all fields
            return; // Stop the program here
        }

        // Check whether the age field is not a number
        try {
            int age = Integer.parseInt(friendAge.trim());  // Parse age input field to an integer

            // for loop to check if the friend the users want to add already exists inside the list
            for(Friend friend: observableFriendsList) {
                if(friend.getName().equals(friendName) &&
                        friend.getAge()==age &&
                        friend.getGame().equals(friendGame)){

                    showTemporaryNotification("This friend already exists!", 4); // Notify the users
                    return; // Stop method and don't add new friends
                }
            }
                observableFriendsList.add(new Friend(friendName.trim(), age, friendGame.trim()));  // Add a new Friend object to the observable list
            // Notify user of successful addition and clear input fields
            showTemporaryNotification("Friend added successfully!", 4);
            textGetName.clear();
            textGetAge.clear();
            textGetGame.clear();

            //  Catch the invalid input ( not a number )
        } catch (NumberFormatException e) {
            // Notify user if age input is invalid (not a number) and clear age input field
            showTemporaryNotification("Age must be a number!", 4); // Notify user of their mistake
            textGetAge.clear(); // clear the age input field
        }
    }

    // Method executed when user clicks "Display All Friends" button
    @FXML
    private void displayAllFriends(MouseEvent event) {
        friendsListView.getSelectionModel().clearSelection(); // Reset the selection in the List View ( avoiding errors)

        // Use StringBuilder to construct the display content
        StringBuilder friendInformationDisplay = new StringBuilder();

        for (Friend friend : observableFriendsList) { // loop over each friend in the observable list

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
    private void deleteFriend(MouseEvent event) throws IOException {
        // Get the selected friend from the ListView ( what friend user clicked on in the ListView)
        Friend selectedFriend = friendsListView.getSelectionModel().getSelectedItem();

        // If user clicked something in the ListView => selectedFriend is not null. The condition below is true
        if (selectedFriend != null) {
            observableFriendsList.remove(selectedFriend); // Remove the friend from the observable list

            // These condition will handle the case the users load a file, then want to delete a friend from that file
            if (filePath != null) {
                // Create a FileWriter object to write data to the file specified by 'filePath'.
                FileWriter fw = new FileWriter(filePath);
                // Wrap the FileWriter in a BufferedWriter to improve efficiency by buffering the output.
                BufferedWriter bw = new BufferedWriter(fw);
                // for loop to overwrite each friend to the file ( update the file with the new list of friends after deleting )
                for (Friend friend : observableFriendsList) {
                    // Write all the data to the file in the logical format, so it can be accessed later
                    bw.write(friend.getName() + ",\r");
                    bw.write(Integer.toString(friend.getAge()) + ".\r");
                    bw.write(friend.getGame() + "\r");
                    bw.write(";\r");
                    bw.close(); // Close the BufferedWriter to avoid errors
                }
                showTemporaryNotification("Friend deleted successfully!", 3); // Notify user of successful deletion
            }
        } else {
            // Notify user to select a friend if none of friends is selected
            showTemporaryNotification("Please select a friend to delete.", 4);
        }
    }

    // Method executed when user clicks "Clear" button
    @FXML
    private void clearDetailArea(MouseEvent event) {
        displayDetailArea.clear(); // Clear the "Friend Detail" TextArea
        friendsListView.getSelectionModel().clearSelection(); // Reset the selection in the List View ( avoiding errors), allowing listener to work properly

    }

    // Method executed when user clicks "quit" button
    @FXML
    private void quit(MouseEvent event) {
        // Terminate the program
        System.exit(0);
    }


    // Method executed to allow the notificationLabel to display the message for a particular duration
    private void showTemporaryNotification(String message, int durationSecs) {
        notificationLabel.setText(message); // Set the message

        // Create a Timeline to clear the notification after durationSecs (seconds)
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(durationSecs),
                        event -> notificationLabel.setText(""))); // Clear the notification

        timeline.setCycleCount(1); // Set the timeline to run only once
        timeline.play(); // Start the timeline
    }


    // Method to handle "Save" button click
    @FXML
    private void save(MouseEvent event) throws IOException {
        if (!observableFriendsList.isEmpty()) { // Check whether the list is empty ( we cannot save when there is no friend on the list )
            // Create a FileChooser object to allow the user to select files.
            FileChooser fileChooser = new FileChooser();
            // Add a filter to the FileChooser to only display files with a .txt extension.
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            // Open the file selection dialog and store the selected file in the variable 'newFile'.
            File newFile = fileChooser.showSaveDialog(null);
            // For loop to add all the friends inside the array to the newFile
            for (Friend friend : observableFriendsList) {
                friend.writeToFile(String.valueOf(newFile)); // writeToFile method is used to write all the friends into the .txt file
            }
            observableFriendsList.clear(); // Clear the observable list after saving to avoid saving the same things again
        } else { // If the list is empty, notify the users
            showTemporaryNotification("There is nothing to be saved!", 4);
        }
    }



    // Method to handle "Load" button click
    @FXML
    private void load(MouseEvent event) throws IOException {
        // Create a FileChooser object to allow the user to select files.
        FileChooser fileChooser = new FileChooser();
        // Add a filter to the FileChooser to only display files with a .txt extension.
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        // Open the file selection dialog and store the selected file in the variable 'selectedFile'.
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) { // If the users choose a file to open, therefore the selectedFile is not null, the condition will be implemented
            filePath = selectedFile.getAbsolutePath(); // Storing the file address in case the users want to delete any friend
            observableFriendsList.clear(); // Clear the observableFriendList to avoid displaying friends which are not from the loaded file
            // Using the CreateFriend class to read all the friends data from .txt file, then add it to the array
            ArrayList<Friend> friends = CreateFriend.CreateAllFriends(String.valueOf(selectedFile));
            // For loop to add all the friends inside the array to the observable friend list
            for (Friend friend : friends) {
                observableFriendsList.add(friend); // Add friends to observable list
            }
        }

        // Conditions to check if the users load an empty file
        if (observableFriendsList.isEmpty()) {
            showTemporaryNotification("The file is empty!", 4);
        } else { // If observable list is not empty, notify the users of the successful loading
            showTemporaryNotification("Friends loaded successfully", 4);
        }
    }

    // Method executed when the users click "Clear" button below the listView
    @FXML
    private void clearListView (MouseEvent event) {
        observableFriendsList.clear();
    }
}


