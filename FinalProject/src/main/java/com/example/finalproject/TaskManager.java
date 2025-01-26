package com.example.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskManager {
    private ObservableList<Task> taskList;
    private String filePath;
    private Label taskManagerTabNotification;
    private TableView<Task> taskTable;

    public ObservableList<Task> getTaskList() {
        return taskList;
    }

    public String getFilePath() {
        return filePath;
    }

    public Label getTaskManagerTabNotification() {
        return taskManagerTabNotification;
    }

    public TableView<Task> getTaskTable() {
        return taskTable;
    }

    public TaskManager(ObservableList<Task> taskList, Label taskManagerTabNotification, TableView<Task> taskTable, String filePath) {
        this.taskList = taskList;
        this.taskManagerTabNotification = taskManagerTabNotification;
        this.taskTable = taskTable;
        this.filePath = filePath;
    }

    public void login(String userName, Label userNameLabel, TabPane tabPane, Label userInformationTabNotification, Tab tabTaskManager){
        // Check if the user's name has been filled
        if (!userName.trim().isEmpty()) {
            userNameLabel.setText(userName);
            temporaryNotification(userInformationTabNotification,"Welcome to your Personal Task Manager!)", 4);
            tabPane.getSelectionModel().select(tabTaskManager); // Switch to the task manager tab
            userNameLabel.setText(userName); // Add the user's name to the name label
        } else { // If not, notify the user
            temporaryNotification(userInformationTabNotification,"Name cannot be empty!", 4);
        }
    }
    // Method to add a task to the table
    public void addTask(String taskName, LocalDate taskDueDate, int hours, int minutes) {

       // Check if task's name is empty
        if (taskName.trim().isEmpty()) {
            return; // Don't do anything
        }

        // Check if due date is empty
        if (taskDueDate == null) {
            return; // Don't do anything
        }

        // Write task Duration in a suitable format
        String taskDuration = String.format("%d hours %d minutes", hours, minutes);

        // Create a new task
        Task task = new Task(taskName, taskDuration, taskDueDate, false);
        // Add the new task to the list
        taskList.add(task);
    }

    // Method to delete a task from the table
    public void deleteTask() throws IOException {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        // If user clicked something on the table => selectedTask is not null. The condition below is true
        if (selectedTask != null) {
            taskList.remove(selectedTask); // Remove the task from observable list
            taskTable.getItems().remove(selectedTask); // Remove the task from TableView
            temporaryNotification(taskManagerTabNotification, "Task deleted successfully!", 4);

            // These condition will handle the case the users load a file, then want to delete a task from that file
            if (filePath != null) {
                // Create a FileWriter object to write data to the file specified by 'filePath'.
                FileWriter fw = new FileWriter(filePath);
                // Wrap the FileWriter in a BufferedWriter to improve efficiency by buffering the output.
                BufferedWriter bw = new BufferedWriter(fw);
                // for loop to overwrite each friend to the file ( update the file with the new list of tasks after deleting )
                for (Task task : taskList) {
                    // Write all the data to the file in the logical format, so it can be accessed later
                    bw.write(task.getTaskName() + ",\r");
                    bw.write(task.getTaskDuration().trim() + ".\r");
                    bw.write(task.getTaskDueDate() + "*\r");
                    bw.write(task.getTaskStatus() + "\r");
                    bw.write(";\r");
                }
                bw.close();
                fw.close();
            }
        } else {
            temporaryNotification(taskManagerTabNotification, "No task selected to delete!", 4);
        }
    }

    // Method to load tasks from a file
    public void loadTasks(File selectedFile) throws IOException {
        // If the users choose a file to open, therefore the selectedFile is not null, the condition will be implemented
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath(); // Storing the file address in case the users want to delete any task
            taskList.clear();// Clear the taskList to avoid displaying tasks which are not from the loaded file
            // Using the CreateTask class to read all the tasks data from .txt file, then add it to the array
            ArrayList<Task> tasks = CreateTask.LoadTasksTable(String.valueOf(selectedFile));
            // For loop to add all the tasks inside the array to the task list
            for (Task task : tasks) {
                taskList.add(task);
            }
        }

        // Conditions to check if the users load an empty file
        if (taskList.isEmpty()) {
            temporaryNotification(taskManagerTabNotification, "The file is empty!", 4);
        } else { // If observable list is not empty, notify the users of the successful loading
            temporaryNotification(taskManagerTabNotification, "Tasks loaded successfully", 4);
        }
    }

    // Method to save tasks to a file
    public void saveTasks() throws IOException {
        if (!taskList.isEmpty()) { // Check whether the list is empty ( we cannot save when there is no task on the list )
            // Create a FileChooser object to allow the user to select files.
            FileChooser fileChooser = new FileChooser();
            // Add a filter to the FileChooser to only display files with a .txt extension.
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            // Open the file selection dialog and store the selected file in the variable 'newFile'.
            File newFile = fileChooser.showSaveDialog(null);

            if (newFile != null) {  // Ensure the user didn't cancel the save dialog
                // For loop to add all the tasks inside the array to the newFile
                for (Task task : taskList) {
                    task.writeToFile(String.valueOf(newFile)); // writeToFile method is used to write all the tasks into the .txt file
                }

                // Clear the list after saving only if the user confirms
                taskList.clear(); // Clear the list after saving to avoid saving the same things again
                temporaryNotification(taskManagerTabNotification, "Tasks saved successfully!", 4);
            } else {
                // If the user cancels the save dialog, don't clear the tasks list
                temporaryNotification(taskManagerTabNotification, "Save operation was canceled.", 4);
            }
        } else { // If the list is empty, notify the users
            temporaryNotification(taskManagerTabNotification, "There is nothing to be saved!", 4);
        }
    }

    // Method executed to allow the notificationLabel to display the message for a particular duration
    private void temporaryNotification(Label notificationLabel, String message, int durationSecs) {
        notificationLabel.setText(message); // Set the message

        // Create a Timeline to clear the notification after durationSecs (seconds)
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(durationSecs),
                        event -> notificationLabel.setText(""))); // Clear the notification
        timeline.setCycleCount(1);  // Set the timeline to run only once
        timeline.play(); // Start the timeline
    }
}