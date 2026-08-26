import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Vani {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("|-------------------------|");
        System.out.println("  \\    /   /\\   |\\  |  |");
        System.out.println("   \\  /   /__\\  | \\ |  |");
        System.out.println("    \\/    |  |  |  \\|  |");
        System.out.println("|-------------------------|");
        System.out.println("Hello! I'm Vani.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        List<String> tasks = new ArrayList<>();
        List<Boolean> taskStatus = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();
                System.out.println("____________________________________________________________");

                if (command.equals("bye")) {
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } 
                else if (command.equals("list")) {
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (taskStatus.get(i)) {
                            System.out.println("        " + (i + 1) + ".[X] " + tasks.get(i));
                        } else {
                            System.out.println("        " + (i + 1) + ".[ ] " + tasks.get(i));
                        }
                    }
                } 
                else if(command.startsWith("mark")) {
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        System.out.println("     You didn't specify the task number to mark as done.");
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber < 1 || taskNumber > tasks.size()) {
                                System.out.println("     Invalid task number.");
                            } else {
                                String completedTask = tasks.get(taskNumber - 1);
                                taskStatus.set(taskNumber - 1, true);
                                System.out.println("     Nice! I've marked this task as done:");
                                System.out.println("       [X] " + completedTask);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("     Invalid task number format.");
                        }
                    }
                }
                else if(command.startsWith("unmark")) {
                    String[] parts = command.split(" ");
                    if (parts.length < 2) {
                        System.out.println("     You didn't specify the task number to unmark.");
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber < 1 || taskNumber > tasks.size()) {
                                System.out.println("     Invalid task number.");
                            } else {
                                String uncompletedTask = tasks.get(taskNumber - 1);
                                taskStatus.set(taskNumber - 1, false);
                                System.out.println("     OK, I've marked this task as not done yet:");
                                System.out.println("       [ ] " + uncompletedTask);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("     Invalid task number format.");
                        }
                    }
                }
                else {
                    tasks.add(command);
                    taskStatus.add(false);
                    System.out.println("     Added: " + command);
                }

                System.out.println("____________________________________________________________");
            }
        }
    }
}
