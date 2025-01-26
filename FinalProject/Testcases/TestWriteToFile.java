import static org.junit.Assert.*;

import com.example.finalproject.Task;
import org.junit.Test;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;

public class TestWriteToFile {
    String taskName = "Learning";
    LocalDate taskDueDate = LocalDate.of(2025, 1, 25);

    // Test in normal condition
    @Test
    public void testWriteToFileNormalCondition() throws IOException {
        // Define the file name to use for the test
        String fileName = "testTask.txt";

        // Delete the file if it already exists to ensure a clean start for the test
        Files.deleteIfExists(Paths.get(fileName));

        // Create a Task object and call the writeToFile method
        Task task = new Task("Test Task", "5 hours", taskDueDate, false);
        task.writeToFile(fileName);

        // Read the file content after writing
        String content = new String(Files.readAllBytes(Paths.get(fileName)));

        // Check if the content in the file matches the expected format
        String expectedContent = "Test Task,\r5 hours.\r2025-01-25*\rfalse\r;\r";
        assertEquals(expectedContent, content); // Should be equal if the method works properly

        // Delete the file after the test to prevent errors
        Files.deleteIfExists(Paths.get(fileName));
    }

    // Test when adding multiple tasks at once
    @Test
    public void testWriteToFileWriteTwoTasksAtOnce() throws IOException {
        // Define the file name to use for the test
        String fileName = "testTaskTwoWrite.txt";

        // Delete the file if it already exists to ensure a clean start for the test
        Files.deleteIfExists(Paths.get(fileName));

        // Create two Task objects and write both to the file
        Task task1 = new Task("Task 1", "2 hours", LocalDate.of(2025, 1, 25), true);
        Task task2 = new Task("Task 2", "3 hours", LocalDate.of(2026, 2, 1), false);

        // Write the first task to the file
        task1.writeToFile(fileName);

        // Write the second task to the file (should be appended)
        task2.writeToFile(fileName);

        // Read the file content after writing
        String content = new String(Files.readAllBytes(Paths.get(fileName)));

        // Check if the content matches the expected format (both tasks should be written in the same file)
        String expectedContent = "Task 1,\r2 hours.\r2025-01-25*\rtrue\r;\rTask 2,\r3 hours.\r2026-02-01*\rfalse\r;\r";
        assertEquals(expectedContent, content); // Should be equal if the method works properly

        // Delete the file after the test to prevent errors
        Files.deleteIfExists(Paths.get(fileName));
    }


    // Test for writing data to a file when the file already exists (data is appended to the end of the file)
    @Test
    public void testWriteToFileAppendData() throws IOException {
        // Define the file name to use for the test
        String fileName = "testTaskAppend.txt";

        // Delete the file if it already exists to ensure a clean start for the test
        Files.deleteIfExists(Paths.get(fileName));

        // Create Task objects and call writeToFile method
        Task task1 = new Task("Test Task 1", "3 hours", LocalDate.of(2025, 1, 25), true);
        Task task2 = new Task("Test Task 2", "4 hours", LocalDate.of(2026, 2, 1), false);

        // Write the first task to the file
        task1.writeToFile(fileName);

        // Write the second task to the file
        task2.writeToFile(fileName);

        // Read the file content after writing
        String content = new String(Files.readAllBytes(Paths.get(fileName)));

        // Check if the content in the file matches the expected format (data should be appended)
        String expectedContent = "Test Task 1,\r3 hours.\r2025-01-25*\rtrue\r;\rTest Task 2,\r4 hours.\r2026-02-01*\rfalse\r;\r";
        assertEquals(expectedContent, content); // Should be equal if the method works properly

        // Delete the file after the test to prevent errors
        Files.deleteIfExists(Paths.get(fileName));
    }


    // Test when 3 tasks are different
    @Test
    public void testCompareTask() {
        // Create Task objects
        Task task1 = new Task("Task 1", "2 hours", LocalDate.of(2025, 1, 25), false);
        Task task2 = new Task("Task 1", "2 hours", LocalDate.of(2026, 1, 25), false);  // Same name and duration, different due date
        Task task3 = new Task("Task 2", "3 hours", LocalDate.of(2027, 1, 25), false);  // Different name and duration

        // Check if two tasks are identical
        assertTrue(task1.compareTask(task2)); // Should be true because task1 and task2 have the same name, duration, and task status

        // Check if two tasks are different
        assertFalse(task1.compareTask(task3)); // Should be false because task1 and task3 have different names and durations
    }

    // Test when just part of the tasks are the same
    @Test
    public void testCompareTaskPartialMatch() {
        // Create Task objects
        Task task1 = new Task("Task 1", "2 hours", LocalDate.of(2025, 1, 25), false);
        Task task2 = new Task("Task 1", "2 hours", LocalDate.of(2025, 1, 25), true);  // Only the status is different

        // Check if tasks have the same name, duration, and due date but different status
        assertFalse(task1.compareTask(task2)); // Should be false because the status of the tasks is different
    }
}