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
                    System.out.println(INDENT + "uwu Bye. Hope to see you again soon!");
                    System.out.println(LINE_SEPARATOR);
                    break;
                } else if (command.equals("list")) {
                    printTaskList(tasks);
                } else if (command.startsWith("mark")) {
                    String[] parts = command.trim().split("\\s+");
                    if (parts.length < 2) {
                        System.out.println(INDENT + "You didn't specify the task number to mark as done. o_O");
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber < 1 || taskNumber > tasks.size()) {
                                System.out.println(INDENT + "Invalid task number. >:[");
                            } else {
                                Task task = tasks.get(taskNumber - 1);
                                String completedTask = task.getDescription();
                                task.markAsDone();
                                System.out.println(INDENT + "<3 Nice! I've marked this task as done:");
                                System.out.println("       [X] " + completedTask);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(INDENT + "Invalid task number format. >:[");
                        }
                    }
                } else if (command.startsWith("unmark")) {
                    String[] parts = command.trim().split("\\s+");
                    if (parts.length < 2) {
                        System.out.println(INDENT + "You didn't specify the task number to unmark. o_O");
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber < 1 || taskNumber > tasks.size()) {
                                System.out.println(INDENT + "Invalid task number. >:[");
                            } else {
                                Task task = tasks.get(taskNumber - 1);
                                String uncompletedTask = task.getDescription();
                                task.markAsNotDone();
                                System.out.println(INDENT + "-.- OK, I've marked this task as not done yet:");
                                System.out.println("       [ ] " + uncompletedTask);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(INDENT + "Invalid task number format. >:[");
                        }
                    }
                } else if (command.startsWith("todo")) {
                    String[] parts = command.split(" ", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        System.out.println(INDENT + "The description of a todo cannot be empty. o_O");
                    } else {
                        String description = parts[1].trim();
                        Todo newTask = new Todo(description);
                        tasks.add(newTask);
                        System.out.println(INDENT + "^-^ Got it. I've added this todo:");
                        System.out.println(INDENT + "   " + newTask.toString());
                        System.out.println(INDENT + "x_x Now you have " + tasks.size() + " tasks in the list.");
                    }
                } else if (command.startsWith("deadline")) {
                    String[] parts = command.split(" /by ");
                    if (parts.length < 2) {
                        System.out.println(INDENT + "You didn't specify the due date for the deadline. o_O");
                    } else {
                        String description = parts[0].substring(8).trim();
                        String by = parts[1].trim();
                        if (description.isEmpty()) {
                            System.out.println(INDENT + "The description of a deadline cannot be empty. o_O");
                        } else if (by.isEmpty()) {
                            System.out.println(INDENT + "The due date of a deadline cannot be empty. o_O");
                        } else {
                            Deadline newTask = new Deadline(description, by);
                            tasks.add(newTask);
                            System.out.println(INDENT + "^-^ Got it. I've added this deadline:");
                            System.out.println(INDENT + "   " + newTask.toString());
                            System.out.println(INDENT + "x_x Now you have " + tasks.size() + " tasks in the list.");
                        }
                    }
                } else if (command.startsWith("event")) {
                    String[] parts = command.split(" /from | /to ");
                    if (parts.length < 3) {
                        System.out.println(INDENT + "You didn't specify the start and end dates for the event. o_O");
                    } else {
                        String description = parts[0].substring(5).trim();
                        String from = parts[1].trim();
                        String to = parts[2].trim();
                        if (description.isEmpty()) {
                            System.out.println(INDENT + "The description of an event cannot be empty. o_O");
                        } else if (from.isEmpty() || to.isEmpty()) {
                            System.out.println(INDENT + "The start and end dates of an event cannot be empty. o_O");
                        } else {
                            Event newTask = new Event(description, from, to);
                            tasks.add(newTask);
                            System.out.println(INDENT + "^-^ Got it. I've added this event:");
                            System.out.println(INDENT + "   " + newTask.toString());
                            System.out.println(INDENT + "x_x Now you have " + tasks.size() + " tasks in the list.");
                        }
                    }
                } else {
                    System.out.println(INDENT + "I'm sorry, I don't understand that command. T-T");
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
        System.out.println("Hello! I'm Vani. >.<");
        System.out.println("What can I do for you? ._.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays all tasks in their current order.
     *
     * @param tasks the list of tasks to display
     */
    private static void printTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + ">.< Yay! You have no tasks in your list.");
            return;
        }
        System.out.println(INDENT + "=^-^= Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(INDENT + (i + 1) + "." + task.toString());
        }
    }
}
