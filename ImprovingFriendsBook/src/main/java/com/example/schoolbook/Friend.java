package com.example.schoolbook;

import java.io.*;

public class Friend {
    private String name;
    private int age;
    private String game;

    // Constructor
    public Friend(String name, int age, String game) {
        this.name = name;
        this.age = age;
        this.game = game;
    }

    // Getters and Setters

    // Requires clause: none
    // Modifies clause: nothing
    // Effects clause: return the value of the name field
    public String getName() {
        return name;
    }

    // Requires clause: none
    // Modifies clause: nothing
    // Effects clause: return the value of the age field
    public int getAge() {
        return age;
    }

    // Requires clause: none
    // Modifies clause: nothing
    // Effects clause: return the value of the game field
    public String getGame() {
        return game;
    }


    // Requires clause: Friend object
    // Modifies clause: nothing
    // Effects clause: return a String representation of the Friend object, consisting the name field only
    @Override
    public String toString() { // ToString Method (Used when friend's name is added to the ListView)
        return name; // just display the friend's name in the ListView
    }


    // Method to write friends from the list view to the chosen file
    public void writeToFile(String fileName) throws IOException {
        // Create a FileWriter object to write data to the file
        FileWriter fw = new FileWriter(fileName, true);
        // Wrap the FileWriter in a BufferedWriter to buffer the output
        BufferedWriter bw = new BufferedWriter(fw);

        // Write all the data to the file in the logical format, so it can be accessed later
        bw.write(name + ",\r");
        bw.write(Integer.toString(age) + ".\r");
        bw.write(game + "\r");
        bw.write(";\r");
        bw.close(); // Close the BufferedWriter to avoid errors
        fw.close(); // Close the file to avoid errors
    }

    // Method to identify duplicate friend
    public boolean compareFriend (Friend friend){
        if(this.name.equals(friend.getName()) && (this.age==friend.getAge()) && (this.game.equals(friend.getGame()))){
            return true; // return true if duplicate
        }else {
            return false; // return false if not
        }
    }
}