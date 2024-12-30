package com.example.schoolbook;

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
}