package com.example.schoolbook;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CreateFriend {
    private static FileReader fr; // Declare a FileReader object to read the file data
    private static BufferedReader br; // Declare a BufferedReader object to buffer the input from the FileReader
    private static ArrayList<Friend> friends = new ArrayList<>(); // Declare an ArrayList to store the list of friends.

    // Method to read the data from the chosen file and parse it back to the program
    public static ArrayList CreateAllFriends(String fileName) throws IOException {
        friends.clear(); // Clear the previous friends in the array, so we will not duplicate the previous file
        fr = new FileReader(fileName);  // Create a FileReader object to read data from the file specified by 'fileName'
        br = new BufferedReader(fr); // Wrap the FileReader in a BufferedReader to buffer the output

        String line; // Variable to store each line read from the file
        String friendString = ""; // A string to temporarily store data for each friend

        // Read the file line by line until the end of the file
        while ((line = br.readLine()) != null) {
            // If the line is not a ';', it means that it does not end yet
            if (!line.equals(";")) {
                friendString += line; // add the whole line before the ';' to the string
            } else {
                // If a ';' is found, parse the stored friend data from string to the array
                parseFriend(friendString);
                friendString = ""; // Reset the string to store the next friend's data.
            }
        }
        br.close(); // Close the BufferedWriter to avoid errors
        fr.close(); // Close the file to avoid errors
        return friends; // Return the array after completed adding all the friends
    }


    // Method to identify each element of the friend's data which are name, age and game
    private static void parseFriend(String string) {
        int pos = 0; // Variable to store index position
        String name = ""; // Variable to store friend's name
        int age = 0; // Variable to store friend's age
        String game = ""; // Variable to store friend's game

        // loop each letters of the string
        for (int i = 0; i < string.length(); i++) {
            // if the ',' is found, it means that friend's name has been identified
            if (string.substring(i, i + 1).equals(",")) {
                pos = i; // Set the pos to the index position where the ',' is found
                name = string.substring(0, pos); // Take the string from the beginning up to before the ',' to get the friend's name data
                break; // Stop the loop after the friend's name was identified
            }
        }

        // loop each letters of the string
        for (int j = 0; j < string.length(); j++) {
            // if the '.' is found, it means that friend's age and game have been identified
            if (string.substring(j, j + 1).equals(".")) {
                // Take the string after the ',' was found to before the '.' was found to get the friend's age
                age = Integer.parseInt(string.substring(pos + 1, j));
                pos = j; // Set pos to be 'j'  (j is the index position where the '.' was found)
                // Take the string after where the '.' was found to the end of the string to get the friend's name
                game += string.substring(pos + 1);
                break; // Stop the loop after the friend's age and game were identified
            }
        }
        Friend newFriend = new Friend(name, age, game); // After identified all the friend's elements, create a friend object

        // Check if the friend already exists in the list using compareFriend method
        boolean exists = false;
        for (Friend friend : friends) {
            if (friend.compareFriend(newFriend)) { // If the friend already exists, don't add
                exists = true;
                break; // Stop the loop after found the duplicate
            }
        }
        // If friend doesn't exist
        if (!exists) {
            friends.add(newFriend); // add to the array
        }
    }
}