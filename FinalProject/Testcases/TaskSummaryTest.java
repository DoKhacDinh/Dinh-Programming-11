import com.example.finalproject.Task;
import com.example.finalproject.TaskSummary;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class TaskSummaryTest {

    private ObservableList<Task> taskList; // List of tasks for testing
    private TaskSummary taskSummary;      // TaskSummary object to test

    // Sets up the test environment before each test case
    //  Initializes a sample task list and a TaskSummary object
    @Before
    public void setUp() {
        // Create a sample task list
        taskList = FXCollections.observableArrayList(
                new Task("Task 1", "1 hour", LocalDate.now().plusDays(1), true),  // Completed task
                new Task("Task 2", "2 hours", LocalDate.now().minusDays(1), false), // Overdue task
                new Task("Task 3", "3 hours", LocalDate.now().plusDays(2), false)   // Pending task
        );

        // Initialize the TaskSummary object
        taskSummary = new TaskSummary(taskList);
    }

    @BeforeClass
    public static void initToolkit() {
        // Create JavaFX toolKit
        new JFXPanel();
    }

    // Tests the updateSummary method to ensure it calculates task statistics correctly
    @Test
    public void testUpdateSummary() {
        // Verify the calculated statistics
        assertEquals(3, taskSummary.getTotalTasks());      // Total tasks should be 3
        assertEquals(1, taskSummary.getCompletedTasks());  // Completed tasks should be 1
        assertEquals(2, taskSummary.getIncompleteTasks()); // Pending tasks should be 2
        assertEquals(1, taskSummary.getOverdueTasks());    // Overdue tasks should be 1
    }

    // Tests the updatePieChart method to ensure it updates the PieChart correctly
    @Test
    public void testUpdatePieChart() {
        Platform.runLater(() -> {
            // Create a pie chart to test
            PieChart pieChart = new PieChart();

            // Create an example list
            ObservableList<Task> taskList = FXCollections.observableArrayList(
                    new Task("Task 1", "1 hour", LocalDate.now().plusDays(1), true),
                    new Task("Task 2", "2 hours", LocalDate.now().minusDays(1), false),
                    new Task("Task 3", "3 hours", LocalDate.now().plusDays(2), false)
            );

            // Create a TaskSummary object
            TaskSummary taskSummary = new TaskSummary(taskList);

            // Update the pie chart with the created data from the list
            taskSummary.updatePieChart(pieChart);

            // Check the data from in the pie chart
            assertEquals(3, pieChart.getData().size()); // the pie chart size should be equal as 3 tasks were added
        });
    }

    // Tests the updateSummaryLabels method to ensure it updates the labels correctly
    @Test
    public void testUpdateSummaryLabels() {
        // Create labels for testing
        Label sumTotalTasks = new Label();
        Label sumCompleteTasks = new Label();
        Label sumIncompleteTasks = new Label();
        Label sumOverDueTasks = new Label();

        // Update the labels with task statistics
        taskSummary.updateSummaryLabels(sumTotalTasks, sumCompleteTasks, sumIncompleteTasks, sumOverDueTasks);

        // Verify the label values
        assertEquals("3", sumTotalTasks.getText());      // Total tasks should be 3
        assertEquals("1", sumCompleteTasks.getText());   // Completed tasks should be 1
        assertEquals("2", sumIncompleteTasks.getText()); // Incomplete tasks should be 2
        assertEquals("1", sumOverDueTasks.getText());    // Overdue tasks should be 1
    }

    // Test when the task list is empty
    @Test
    public void testEmptyTaskList() {
        Platform.runLater(() -> {
            // Create a pie chart to test
            PieChart pieChart = new PieChart();
            // Create an example list
            ObservableList<Task> taskList = FXCollections.observableArrayList(
                    new Task("Task 1", "1 hour", LocalDate.now().plusDays(1), true),
                    new Task("Task 2", "2 hours", LocalDate.now().minusDays(1), false),
                    new Task("Task 3", "3 hours", LocalDate.now().plusDays(2), false)
            );

            // Clear the list
            taskList.clear();

            // Create a TaskSummary object
            TaskSummary taskSummary = new TaskSummary(taskList);

            // Update the pie chart with the created data from the list
            taskSummary.updatePieChart(pieChart);


            assertEquals(0, taskSummary.getTotalTasks());  // Total tasks should be 0
            assertEquals(0, taskSummary.getCompletedTasks()); // Completed tasks should be 0
            assertEquals(0, taskSummary.getIncompleteTasks());  // Incomplete tasks should be 0
            assertEquals(0, taskSummary.getOverdueTasks()); // Overdue tasks should be 0
        });
    }
}

