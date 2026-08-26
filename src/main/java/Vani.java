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
                        System.out.println("        " + (i + 1) + ". " + tasks.get(i));
                    }
                }
                else {
                    tasks.add(command);
                    System.out.println("     Added: " + command);
                }

                System.out.println("____________________________________________________________");
            }
        }
    }
}
