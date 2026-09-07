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
        // Display greeting message
        printGreeting();

        List<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();
                System.out.println(LINE_SEPARATOR);

                boolean shouldExit = handleCommand(command, tasks);
                if (shouldExit) {
                    break;
                }

                System.out.println(LINE_SEPARATOR);
            }
        }
    }

    /**
     * Handles a single command from the user.
     *
     * @param command the command entered by the user
     * @param tasks the list of tasks to modify
     * @return true if the application should exit, false otherwise
     */
    private static boolean handleCommand(String command, List<Task> tasks) {
        // exit command
        if (command.equals("bye")) {
            System.out.println(INDENT + "uwu Bye. Hope to see you again soon!");
            System.out.println(LINE_SEPARATOR);
            return true;
        }
        // list command
        if (command.equals("list")) {
            printTaskList(tasks);
            return false;
        }
        // mark command
        if (command.startsWith("mark")) {
            handleMarkCommand(command, tasks);
            return false;
        }
        // unmark command
        if (command.startsWith("unmark")) {
            handleUnmarkCommand(command, tasks);
            return false;
        }
        // task creation commands
        if (command.startsWith("todo")) {
            handleTodoCommand(command, tasks);
            return false;
        }
        if (command.startsWith("deadline")) {
            handleDeadlineCommand(command, tasks);
            return false;
        }
        if (command.startsWith("event")) {
            handleEventCommand(command, tasks);
            return false;
        }
        // command not recognized error
        System.out.println(INDENT + "I'm sorry, I don't understand that command. T-T");
        return false;
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param command the mark command
     * @param tasks the list of tasks
     */
    private static void handleMarkCommand(String command, List<Task> tasks) {
        String[] parts = command.trim().split("\\s+");
        if (parts.length < 2) {
            System.out.println(INDENT + "You didn't specify the task number to mark as done. o_O");
            return;
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println(INDENT + "Invalid task number. >:[");
            } else {
                Task task = tasks.get(taskNumber - 1);
                task.markAsDone();
                System.out.println(INDENT + "Nice! I've marked this task as done: <3");
                System.out.println("       [X] " + task.getDescription());
            }
        } catch (NumberFormatException e) {
            System.out.println(INDENT + "Invalid task number format. >:[");
        }
    }

    /**
     * Handles the "unmark" command to mark a task as incomplete.
     *
     * @param command the unmark command
     * @param tasks the list of tasks
     */
    private static void handleUnmarkCommand(String command, List<Task> tasks) {
        String[] parts = command.trim().split("\\s+");
        if (parts.length < 2) {
            System.out.println(INDENT + "You didn't specify the task number to unmark. o_O");
            return;
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println(INDENT + "Invalid task number. >:[");
            } else {
                Task task = tasks.get(taskNumber - 1);
                task.markAsNotDone();
                System.out.println(INDENT + "OK, I've marked this task as not done yet: -.-");
                System.out.println("       [ ] " + task.getDescription());
            }
        } catch (NumberFormatException e) {
            System.out.println(INDENT + "Invalid task number format. >:[");
        }
    }

    /**
     * Handles the "todo" command to add a new todo task.
     *
     * @param command the todo command
     * @param tasks the list of tasks to add to
     */
    private static void handleTodoCommand(String command, List<Task> tasks) {
        String description = command.substring(4).trim();

        // description empty error
        if (description.isEmpty()) {
            System.out.println(INDENT
                    + "The description of a todo cannot be empty. o_O");
            return;
        }

        Todo newTask = new Todo(description);
        tasks.add(newTask);

        // Print confirmation message
        System.out.println(INDENT + "Got it. I've added this todo: ^-^");
        System.out.println(INDENT + "   " + newTask);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list. x_x");
    }

    /**
     * Handles the "deadline" command to add a new deadline task.
     *
     * @param command the deadline command
     * @param tasks the list of tasks to add to
     */
    private static void handleDeadlineCommand(String command, List<Task> tasks) {
        String content = command.substring(8).trim();

        // content empty error
        if (content.isEmpty()) {
            System.out.println(INDENT + "The description of a deadline cannot be empty. o_O");
            return;
        }

        int byIndex = content.indexOf("/by");

        // '/by' not found error
        if (byIndex == -1) {
            System.out.println(INDENT + "You didn't specify the due date for the deadline. o_O");
            return;
        }

        String description = content.substring(0, byIndex).trim();
        String by = content.substring(byIndex + 3).trim();

        // description empty error
        if (description.isEmpty()) {
            System.out.println(INDENT + "The description of a deadline cannot be empty. o_O");
            return;
        }

        // due date empty error
        if (by.isEmpty()) {
            System.out.println(INDENT + "The due date of a deadline cannot be empty. o_O");
            return;
        }

        Deadline newTask = new Deadline(description, by);
        tasks.add(newTask);

        // Print confirmation message
        System.out.println(INDENT + "Got it. I've added this deadline: ^-^");
        System.out.println(INDENT + "   " + newTask);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list. x_x");
    }

    /**
     * Handles the "event" command to add a new event task.
     *
     * @param command the event command
     * @param tasks the list of tasks to add to
     */
    private static void handleEventCommand(String command, List<Task> tasks) {
        String content = command.substring(5).trim();

        // content empty error
        if (content.isEmpty()) {
            System.out.println(INDENT + "The description of an event cannot be empty. o_O");
            return;
        }

        int fromIndex = content.indexOf("/from");
        int toIndex = content.indexOf("/to");

        // '/from' not found error
        if (fromIndex == -1) {
            System.out.println(INDENT + "You didn't specify the start date for the event. o_O");
            return;
        }

        // '/to' not found error
        if (toIndex == -1) {
            System.out.println(INDENT + "You didn't specify the end date for the event. o_O");
            return;
        }

        // '/to' appears before '/from' error
        if (toIndex < fromIndex) {
            System.out.println(INDENT + "Please specify /from before /to. o_O");
            return;
        }

        String description = content.substring(0, fromIndex).trim();
        String from = content.substring(fromIndex + 5, toIndex).trim();
        String to = content.substring(toIndex + 3).trim();

        // description empty error
        if (description.isEmpty()) {
            System.out.println(INDENT + "The description of an event cannot be empty. o_O");
            return;
        }

        // from empty error
        if (from.isEmpty()) {
            System.out.println(INDENT + "The start date of an event cannot be empty. o_O");
            return;
        }

        // to empty error
        if (to.isEmpty()) {
            System.out.println(INDENT + "The end date of an event cannot be empty. o_O");
            return;
        }

        Event newTask = new Event(description, from, to);
        tasks.add(newTask);

        // Print confirmation message
        System.out.println(INDENT + "Got it. I've added this event: ^-^");
        System.out.println(INDENT + "   " + newTask);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list. x_x");
    }

    // Displays the greeting message when the application starts.
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
        // list empty error
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "Yay! You have no tasks in your list. >.<");
            return;
        }
        // Print the list of tasks
        System.out.println(INDENT + "Here are the tasks in your list: =^-^=");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(INDENT + (i + 1) + "." + task.toString());
        }
    }
}
