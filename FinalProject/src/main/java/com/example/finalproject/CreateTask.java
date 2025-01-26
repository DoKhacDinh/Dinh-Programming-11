package com.example.finalproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateTask {

    private static FileReader fr; // Declare a FileReader object to read the file data
    private static BufferedReader br; // Declare a BufferedReader object to buffer the input from the FileReader
    private static ArrayList<Task> tasks = new ArrayList<>(); // Declare an ArrayList to store the table of tasks.


    // Method to read the data from the chosen file and parse it back to the program
    public static ArrayList LoadTasksTable  (String fileName) throws IOException{
        tasks.clear();// Clear the previous tasks in the array, so we will not duplicate the previous file

        fr = new FileReader(fileName); // Create a FileReader object to read data from the file specified by 'fileName'
        br = new BufferedReader(fr); // Wrap the FileReader in a BufferedReader to buffer the output

        String line; // Variable to store each line read from the file
        String taskString = ""; // A string to temporarily store data for each task

        // Read the file line by line until the end of the file
        while((line= br.readLine()) != null){
            // If the line is not a ';', the loop has not read all that task's elements
            if(!line.equals(";")){
                taskString += line; // add the line before the ';' to the string
            }else {
                // If a ';' is found,
                parseTask(taskString); // Parse the stored the task's elements from the string to the array
                taskString = ""; // Reset the string to store the next task's data.
            }
        }
        fr.close(); // Close the BufferedWriter to avoid errors
        br.close(); // Close the file to avoid errors
        return tasks; // Return the array after completed adding all the tasks from the file
        }

        private static void parseTask (String taskString){
            int pos = 0; // Variable to store index position
            String taskName = ""; // Variable to store task's name
            String taskDuration = " "; // Variable to store task's duration
            LocalDate taskDueDate = null; // Variable to store task's due date
            Boolean taskStatus = null; // Variable to store task's status

            // Loop each letters of the string
            for(int i = 0; i < taskString.length(); i++){
                // if the ',' is found, the task's name has been identified
                if(taskString.substring(i, i+1).equals(",")){
                    pos = i; // Set the pos to the index position where the ',' was found
                    taskName += taskString.substring(0,pos); // Take the string from the beginning up to before the ',' to get the task's name
                    break;  // Stop the loop after the task's name was identified
                }
            }

            // Loop each letters of the string
            for(int j = 0; j < taskString.length(); j++){
                // if the '.' is found, the task's duration has been identified
                if(taskString.substring(j,j+1).equals(".")){
                    // Take the string after the ',' was found to before the '.' was found to get the task's duration
                    taskDuration += taskString.substring(pos+1,j);
                    pos = j; // Set pos to be 'j'  (j is the index position where the '.' was found)
                    break; // Stop the loop after the friend's age and game were identified
                }
            }

            // Loop each letters of the string
            for(int k=0; k < taskString.length(); k++){
                // if the '*' is found, the task's due date and status have been identified
                if(taskString.substring(k,k+1).equals("*")){
                    // Take the string after the '.' was found to before the '*' was found to get the task's due date
                    taskDueDate = LocalDate.parse(taskString.substring(pos + 1, k));
                    pos = k; // Set pos to be 'k'  (k is the index position where the '*' was found)
                    // Take the string after where the '*' was found to the end of the taskString to get the task's status
                    taskStatus = Boolean.parseBoolean(taskString.substring(pos + 1));
                    break; // Stop the loop after the task's due date and status were identified
                }
            }
            Task newTask = new Task(taskName, taskDuration, taskDueDate, taskStatus); // After identified all the task's elements, create a task object

            // Check if the task already exists in the list using compareTask method
            boolean exists = false;
            for (Task task : tasks) {
                if (task.compareTask(newTask)) { // If the task already exists, don't add
                    exists = true;
                    break; // Stop the loop after found the duplicate
                }
            }
            // If task doesn't exist
            if (!exists) {
                tasks.add(newTask); // add to the array
            }
        }
}
