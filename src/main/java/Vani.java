import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main entry point for Vani, a simple command-line task manager.
 *
 * <p>The program keeps accepting commands until the user enters {@code bye}.
 * Commands for listing, completing, and reopening tasks are handled directly;
 * any other input is treated as the description of a new task.</p>
 */
public class Vani {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String INDENT = "     ";

    /**
     * Starts the interactive Vani application.
     *
     * @param args command-line arguments, which are not used by this program
     */
    public static void main(String[] args) {
        printGreeting();

        List<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();
                System.out.println(LINE_SEPARATOR);

                if (command.equals("bye")) {
                    System.out.println(INDENT + "Bye. Hope to see you again soon!");
                    System.out.println(LINE_SEPARATOR);
                    break;
                } else if (command.equals("list")) {
                    printTaskList(tasks);
                } else if (command.startsWith("mark")) {
                    String[] parts = command.trim().split("\\s+");
                    if (parts.length < 2) {
                        System.out.println(INDENT + "You didn't specify the task number to mark as done.");
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber < 1 || taskNumber > tasks.size()) {
                                System.out.println(INDENT + "Invalid task number.");
                            } else {
                                Task task = tasks.get(taskNumber - 1);
                                String completedTask = task.getDescription();
                                task.markAsDone();
                                System.out.println(INDENT + "Nice! I've marked this task as done:");
                                System.out.println("       [X] " + completedTask);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(INDENT + "Invalid task number format.");
                        }
                    }
                } else if (command.startsWith("unmark")) {
                    String[] parts = command.trim().split("\\s+");
                    if (parts.length < 2) {
                        System.out.println(INDENT + "You didn't specify the task number to unmark.");
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber < 1 || taskNumber > tasks.size()) {
                                System.out.println(INDENT + "Invalid task number.");
                            } else {
                                Task task = tasks.get(taskNumber - 1);
                                String uncompletedTask = task.getDescription();
                                task.markAsNotDone();
                                System.out.println(INDENT + "OK, I've marked this task as not done yet:");
                                System.out.println("       [ ] " + uncompletedTask);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(INDENT + "Invalid task number format.");
                        }
                    }
                } else {
                    Task newTask = new Task(command);
                    tasks.add(newTask);
                    System.out.println(INDENT + "Added: " + command);
                }

                System.out.println(LINE_SEPARATOR);
            }
        }
    }

    /**
     * Prints the welcome banner and introduction text.
     */
    private static void printGreeting() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("|-------------------------|");
        System.out.println("  \\    /   /\\   |\\  |  |");
        System.out.println("   \\  /   /__\\  | \\ |  |");
        System.out.println("    \\/    |  |  |  \\|  |");
        System.out.println("|-------------------------|");
        System.out.println("Hello! I'm Vani.");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays all tasks in their current order.
     *
     * @param tasks the list of tasks to display
     */
    private static void printTaskList(List<Task> tasks) {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(INDENT + (i + 1) + ".[" + task.getStatusIcon() + "] "
                    + task.getDescription());
        }
    }
}
