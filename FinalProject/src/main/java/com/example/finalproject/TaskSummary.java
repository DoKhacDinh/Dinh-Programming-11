package com.example.finalproject;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class TaskSummary {

    private int totalTasks;
    public int pendingTasks;
    private int incompleteTasks;
    private int overdueTasks;

    // Constructor
    public TaskSummary (ObservableList<Task> taskList) {
        updateSummary(taskList);
    }

    // Method to calculate and update the summary data for the task list
    public void updateSummary(ObservableList<Task> taskList) {
        // Calculate the total number of tasks in the list
        totalTasks = taskList.size();

        // Count the number of tasks that are pending (where getTaskStatus() returns true)
        pendingTasks = (int) taskList.stream().filter(Task::getTaskStatus).count();

        // Calculate the number of incomplete tasks (total tasks minus pending tasks)
        incompleteTasks = totalTasks - pendingTasks;

        // Count the number of overdue tasks (tasks not completed and due date is before today)
        overdueTasks = (int) taskList.stream()
                .filter(task -> !task.getTaskStatus() && task.getTaskDueDate().isBefore(LocalDate.now()))
                .count();
    }

    // Method to update the new data to the pie chart
    public void updatePieChart(PieChart pieChart) {
        // Remove previous data
        pieChart.getData().clear();

        // Add the new data to the pie chart
        pieChart.getData().add(new PieChart.Data("Completed", pendingTasks));
        pieChart.getData().add(new PieChart.Data("Pending", incompleteTasks));
        pieChart.getData().add(new PieChart.Data("Overdue", overdueTasks));

        // Method to hide an element whose does not have value and display when it has
        ObservableList<PieChart.Data> chartData = pieChart.getData();
        for (PieChart.Data data : chartData) {
            if(data.getPieValue()==0){
                data.getNode().setVisible(false); // Hide the element if its value is 0
            }else{
                data.getNode().setVisible(true);  // Display the element if its value is not 0
            }
        }
    }

    // Method to update the new data to the summary labels
    public void updateSummaryLabels(Label sumTotalTasks, Label sumCompleteTasks,
                                    Label sumIncompleteTasks, Label sumOverDueTasks ){
       sumTotalTasks.setText(String.valueOf(totalTasks));
       sumCompleteTasks.setText(String.valueOf(pendingTasks));
       sumIncompleteTasks.setText(String.valueOf(incompleteTasks));
       sumOverDueTasks.setText(String.valueOf(overdueTasks));
    }

    public int getTotalTasks() { return totalTasks; }
    public int getCompletedTasks() { return pendingTasks; }
    public int getIncompleteTasks() { return incompleteTasks; }
    public int getOverdueTasks() { return overdueTasks; }

}


