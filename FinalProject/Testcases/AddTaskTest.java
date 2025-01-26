import com.example.finalproject.Task;
import com.example.finalproject.TaskManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

public class AddTaskTest {
    TaskManager taskManager;

    String taskName = "Learning";
    LocalDate taskDueDate = LocalDate.of(2025, 1, 25);
    int hours = 1;
    int minutes = 3;

    @Before
    public void setup() {
        // Initialize an empty Observable List and Task Manager before each test
        ObservableList<Task> taskList = FXCollections.observableArrayList();
        taskManager = new TaskManager(taskList, null, null, null);
    }

    @Test
    public void testNormalCondition() {
        // Test adding a task with valid inputs
        taskManager.addTask(taskName, taskDueDate, hours, minutes);
        // Verify the task list contains exactly one task
        assertEquals(1, taskManager.getTaskList().size()); // Should be equal as the list's size should increase by 1
    }

    @Test
    public void testAddMultipleTasks() {
        // Test adding multiple tasks to ensure the task list grows correctly
        taskManager.addTask("Learning Java", taskDueDate, hours, minutes);
        taskManager.addTask("Study Physics", taskDueDate.plusDays(1), 2, 15);
        taskManager.addTask("Complete Project", taskDueDate.plusDays(2), 3, 30);
        // Verify the task list contains three tasks.
        assertEquals(3, taskManager.getTaskList().size()); // Should be equal as the list's size should increase by 3
    }

    @Test
    public void testAddTaskWithInvalidName() {
        // Test adding a task with an empty name
        taskManager.addTask("", taskDueDate, hours, minutes);
        // Verify that the task is not added to the task list
        assertEquals(0, taskManager.getTaskList().size()); // Should be equal as the list's size should not change
    }

    @Test
    public void testAddTaskWithNullDueDate() {
        // Test adding a task with a null due date
        taskManager.addTask(taskName, null, hours, minutes);
        // Verify that the task is not added to the task list
        assertEquals(0, taskManager.getTaskList().size()); // Should be equal as the list's size should not change
    }

    @Test
    public void testAddTaskWithLongName() {
        // Test adding a task with a very long name
        String longTaskName = "This is a very long task name that might exceed normal limits";
        taskManager.addTask(longTaskName, taskDueDate, hours, minutes);
        // Verify that the task is successfully added to the task list
        assertEquals(1, taskManager.getTaskList().size()); // Should be equal as the list's size should increase by 1
        // Verify that the task name matches the input long task name
        assertEquals(longTaskName, taskManager.getTaskList().get(0).getTaskName()); // Should be equal as the name in the list should be the same as it added
    }
}
