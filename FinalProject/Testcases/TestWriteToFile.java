import static org.junit.Assert.*;

import com.example.finalproject.Task;
import org.junit.Test;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;

public class TestWriteToFile {
    String taskName = "Learning";
    LocalDate taskDueDate = LocalDate.of(2025, 1, 25);

    @Test
    public void testWriteToFile() throws IOException {
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
        assertEquals(expectedContent, content);

        // Delete the file after the test to prevent errors
        Files.deleteIfExists(Paths.get(fileName));
    }
}