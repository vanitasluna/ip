import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * The main entry point for Vani, a simple command-line task manager.
 *
 * The program keeps accepting commands until the user enters {@code bye}.
 * Commands for listing, completing, and reopening tasks are handled directly;
 * any other input is treated as the description of a new task.
 */
public class Vani {
    /**
     * Starts the interactive Vani application.
     *
     * @param args command-line arguments, which are not used by this program
     */
    public static void main(String[] args) {
        // Display the application banner before the command loop begins.
        System.out.println("____________________________________________________________");
        System.out.println("|-------------------------|");
        System.out.println("  \\    /   /\\   |\\  |  |");
        System.out.println("   \\  /   /__\\  | \\ |  |");
        System.out.println("    \\/    |  |  |  \\|  |");
        System.out.println("|-------------------------|");
        System.out.println("Hello! I'm Vani.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        List<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Each line is interpreted as one complete user command.
                String command = scanner.nextLine();
                System.out.println("____________________________________________________________");

                if (command.equals("bye")) {
                    // Stop immediately after displaying the farewell message.
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (command.equals("list")) {
                    // Preserve insertion order when displaying task numbers.
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("     " + (i + 1) + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
                    }
                } else if (command.startsWith("mark")) {
                    // The second space-separated item is the one-based task number.
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        System.out.println("     You didn't specify the task number to mark as done.");
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber < 1 || taskNumber > tasks.size()) {
                                System.out.println("     Invalid task number.");
                            } else {
                                String completedTask = tasks.get(taskNumber - 1).description;
                                tasks.get(taskNumber - 1).markAsDone();
                                System.out.println("     Nice! I've marked this task as done:");
                                System.out.println("       [X] " + completedTask);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("     Invalid task number format.");
                        }
                    }
                } else if (command.startsWith("unmark")) {
                    // Unmarking follows the same validation rules as marking.
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        System.out.println("     You didn't specify the task number to unmark.");
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber < 1 || taskNumber > tasks.size()) {
                                System.out.println("     Invalid task number.");
                            } else {
                                String uncompletedTask = tasks.get(taskNumber - 1).description;
                                tasks.get(taskNumber - 1).markAsNotDone();
                                System.out.println("     OK, I've marked this task as not done yet:");
                                System.out.println("       [ ] " + uncompletedTask);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("     Invalid task number format.");
                        }
                    }
                } else {
                    // Unknown commands become new tasks by design.
                    tasks.add(new Task(command));
                    System.out.println("     Added: " + command);
                }

                System.out.println("____________________________________________________________");
            }
        }
    }
}
