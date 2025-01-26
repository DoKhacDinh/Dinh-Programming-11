package com.example.finalproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Task {


    private String taskName;
    private String taskDuration;
    private LocalDate  taskDueDate;
    private  Boolean taskStatus;

    public Boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(String taskDuration) {
        this.taskDuration = taskDuration;
    }

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public Task (String taskName, String taskDuration, LocalDate taskDueDate, Boolean taskStatus){
        this.taskName = taskName;
        this.taskDuration = taskDuration;
        this.taskDueDate = taskDueDate;
        this.taskStatus = taskStatus;

    }

    public void writeToFile(String fileName) throws IOException {
        // Create a FileWriter object to write data to the file
        FileWriter fw = new FileWriter(fileName,true);
        // Wrap the FileWriter in a BufferedWriter to buffer the output
        BufferedWriter bw = new BufferedWriter(fw);

        // Write all the data to the file in the logical format, so it can be accessed later
        bw.write( taskName + ",\r");
        bw.write(taskDuration.trim() + ".\r");
        bw.write(taskDueDate + "*\r");
        bw.write(taskStatus + "\r");
        bw.write(";\r");
        bw.close(); // Close the BufferedWriter to avoid errors
        fw.close(); // Close the file to avoid errors
    }

    // Method to identify duplicate task
    public boolean compareTask (Task task){
        if(this.taskName.equals(task.getTaskName()) && (this.taskDueDate.equals(getTaskDueDate())) && (this.taskDuration.equals(task.getTaskDuration()))
                && (this.taskStatus.equals(task.getTaskStatus()))){
            return true; // return true if duplicate
        }else {
            return false; // return false if not
        }
    }
}
