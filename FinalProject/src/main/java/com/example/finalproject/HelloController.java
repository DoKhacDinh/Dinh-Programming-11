package com.example.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class HelloController {
    public TaskSummary taskSummary;
    public Label sumTotalTasks;
    public Label sumCompletedTasks;
    public Label sumPendingTasks;
    public Label sumOverDueTasks;
    public Tab tabSummary;
    public PieChart taskSummaryChart;
    public String filePath;
    public TableView<Task> taskTable;
    public TableColumn<Task, String> statusColumn;
    public TableColumn<Task, LocalDate> dueDateColumn;
    public TableColumn<Task, Duration> durationColumn;
    public TableColumn<Task, String> taskNameColumn;
    public Label taskManagerTabNotification;
    public DatePicker dueDatePicker;
    public TextField textGetTaskTitle;
    public Spinner<Integer> hourSpinner;
    public Spinner<Integer> minutesSpinner;
    public TabPane tabPane;
    public Tab tabUserInformation;
    public Tab tabTaskManager;
    public Label userInformationTabNotification;
    public Label userNameLabel;
    public TextField textGetName;
    public ObservableList<Task> taskList = FXCollections.observableArrayList();
    private TaskManager taskManager;

    @FXML
    public void initialize() {
        // Initialize TaskSummary and TaskManager with taskList
        taskSummary = new TaskSummary(taskList);
        taskManager = new TaskManager(taskList, taskManagerTabNotification, taskTable, filePath);

        // Update pie chart and summary labels with task data
        taskSummary.updatePieChart(taskSummaryChart);
        taskSummary.updateSummaryLabels(sumTotalTasks, sumCompletedTasks, sumPendingTasks, sumOverDueTasks);

        // Set up taskTable and bind it with taskList (ObservableList)
        taskTable.setItems(taskList);
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("taskDuration"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("taskDueDate"));

        // Define how the status column displays the task's status as a checkbox
        statusColumn.setCellFactory(column -> new TableCell<Task, String>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty); // Call parent class's updateItem method

                if (empty || getTableRow() == null) {
                    setGraphic(null); // Clear graphic if the cell is empty
                } else {
                    Task task = getTableView().getItems().get(getIndex()); // Retrieve the task corresponding to the row
                    checkBox.setSelected(task.getTaskStatus()); // Set checkbox state based on task's completion status
                    setGraphic(checkBox); // Add checkbox to the cell

                    // Add listener to update task status when checkbox is clicked
                    checkBox.setOnAction(event -> {
                        task.setTaskStatus(checkBox.isSelected()); // Update task status
                        updateTabSummary(); // Refresh summary data
                    });
                }
            }
        });

        // Set up spinners for selecting task duration (hours and minutes)
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        // Set up custom cell factory for DatePicker to disable past dates
        dueDatePicker.setDayCellFactory(dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // Disable any date before today
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true); // Disable the cell
                    setStyle("-fx-background-color: #ffcccc;"); // Change background color to indicate unselectable dates
                }
            }
        });

        // Add a listener to handle the selection of a task from the table ( The task that the user clicks on the table, the computer will observe and do the task below)
        taskTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // These codes below will allow users to interact with the program easier by resetting their table selection when they do other things.
            // Listen for mouse click events on the tabPane (root container)
            tabPane.setOnMouseClicked(event -> {
                // Check if the click was not on the table and the table is not being hovered
                // event.getTarget() gets the element that was clicked.
                // taskTable.isHover() checks if the table is being hovered over.
                if (!taskTable.equals(event.getTarget()) && !taskTable.isHover()) {
                    // Clear the current selection in the ListView
                    taskTable.getSelectionModel().clearSelection();
                }
            });
        });
    }
        // Method executed when user clicks "Login" button
    @FXML
    private void login(MouseEvent event) {
        String userName = textGetName.getText(); // Gather user's name
        taskManager.login(userName, userNameLabel, tabPane, userInformationTabNotification, tabTaskManager);
        if (!userName.trim().isEmpty()) {
            tabTaskManager.setDisable(false); // Enable Task Manager tab
            tabSummary.setDisable(false); // Enable Task Summary tab
        }
    }
    @FXML
    public void add(MouseEvent event) {
        String taskName = textGetTaskTitle.getText(); // Gather task's name
        LocalDate taskDueDate = dueDatePicker.getValue(); // Gather task's due date
        Integer hours = hourSpinner.getValue(); // Gather hours of duration
        Integer minutes = minutesSpinner.getValue(); // Gather minutes of duration

        // Convert hours and minutes into a Duration string
        String taskDuration = String.format("%d hours %d minutes", hours, minutes);

        // Check whether any input fields are empty
        if (taskName.trim().isEmpty() || taskDueDate == null) {
            temporaryNotification(taskManagerTabNotification, "Please enter your task information!", 4); // Notify user to fill all fields
            return; // Stop the program here
        }

        // Loop through the list to check if the task already exists
        for (Task task : taskList) {
            if (task.getTaskName().equals(taskName) &&
                    task.getTaskDueDate().equals(taskDueDate) &&
                    task.getTaskDuration().equals(taskDuration)) { // Compare Duration as a String

                temporaryNotification(taskManagerTabNotification, "This task already exists!", 4); // Notify the users
                return; // Stop method and don't add duplicate tasks
            }
        }

        // Add the task to the table
        taskManager.addTask(taskName, taskDueDate, hours, minutes);
        // Clear the input fields after successful addition
        textGetTaskTitle.clear();  // Clear the task title input
        dueDatePicker.setValue(null);  // Clear the due date input
        hourSpinner.getValueFactory().setValue(0);  // Reset hour spinner to 0
        minutesSpinner.getValueFactory().setValue(0);  // Reset minutes spinner to 0

        updateTabSummary(); // Update the new data to the summary tab

        // Notify the user of the successful addition
        temporaryNotification(taskManagerTabNotification, "Task added successfully!", 4);
    }

    // Method executed when user clicks "Delete" button to delete a task
    @FXML
    private void delete(MouseEvent event) throws IOException {
        taskManager.deleteTask(); // Deletes the selected task
        updateTabSummary(); // Update the new data to the summary tab
    }

    // Method executed when user clicks "Load" button to load tasks from a file
    @FXML
    private void load(MouseEvent event) throws IOException {
        // Create a FileChooser object to allow the user to select files.
        FileChooser fileChooser = new FileChooser();
        // Add a filter to the FileChooser to only display files with a .txt extension.
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        // Open the file selection dialog and store the selected file in the variable 'selectedFile'.
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            taskManager.loadTasks(selectedFile); // Load tasks from the selected file
            updateTabSummary(); // Update the new data to the summary tab
        }
    }

    // Method executed when user clicks "Save" button to save tasks to a file
    @FXML
    private void save(MouseEvent event) throws IOException {
        taskManager.saveTasks(); // Save current tasks to a file
        updateTabSummary(); // Update the new data to the summary tab
    }

    // Method executed when user clicks "quit" button
    @FXML
    private void close(MouseEvent event) {
        // Terminate the program
        System.exit(0);
    }

    // Method to update the task summary on the summary tab
    private void updateTabSummary() {
        taskSummary.updateSummary(taskList); // Refresh task summary data
        taskSummary.updatePieChart(taskSummaryChart); // Refresh pie chart
        taskSummary.updateSummaryLabels(sumTotalTasks, sumCompletedTasks, sumPendingTasks, sumOverDueTasks); // Refresh summary labels
    }

    // Method to display temporary notifications
    private void temporaryNotification(Label notificationLabel, String message, int durationSecs) {
        notificationLabel.setText(message);

        // Create a timeline to remove the notification after the specified duration
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(durationSecs),
                        event -> notificationLabel.setText(""))); // Remove the notification after the duration
        timeline.setCycleCount(1);
        timeline.play(); // Start the timeline
    }
}
