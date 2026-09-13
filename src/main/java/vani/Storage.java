package vani;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import vani.task.Deadline;
import vani.task.Event;
import vani.task.Task;
import vani.task.Todo;

public class Storage {

    private static final Path DATA_FILE = Paths.get("data", "vani.txt");

    public static void save(List<Task> tasks) {
        try {
            Files.createDirectories(DATA_FILE.getParent());

            StringBuilder data = new StringBuilder();

            for (Task task : tasks) {
                if (task instanceof Todo) {
                    data.append("T | ")
                            .append(task.getStatusIcon().equals("X") ? "1" : "0")
                            .append(" | ")
                            .append(task.getDescription())
                            .append(System.lineSeparator());

                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;

                    data.append("D | ")
                            .append(task.getStatusIcon().equals("X") ? "1" : "0")
                            .append(" | ")
                            .append(task.getDescription())
                            .append(" | ")
                            .append(deadline.getBy())
                            .append(System.lineSeparator());

                } else if (task instanceof Event) {
                    Event event = (Event) task;

                    data.append("E | ")
                            .append(task.getStatusIcon().equals("X") ? "1" : "0")
                            .append(" | ")
                            .append(task.getDescription())
                            .append(" | ")
                            .append(event.getFrom())
                            .append(" | ")
                            .append(event.getTo())
                            .append(System.lineSeparator());
                }
            }

            Files.writeString(DATA_FILE, data.toString());

        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    public static List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        // The data file may not exist when Vani is run for the first time.
        if (!Files.exists(DATA_FILE)) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(DATA_FILE);

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s*\\|\\s*");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");

                Task task = null;

                if (type.equals("T") && parts.length == 3) {
                    task = new Todo(parts[2]);

                } else if (type.equals("D") && parts.length == 4) {
                    task = new Deadline(parts[2], parts[3]);

                } else if (type.equals("E") && parts.length == 5) {
                    task = new Event(parts[2], parts[3], parts[4]);
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }

                    tasks.add(task);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }

        return tasks;
    }
}