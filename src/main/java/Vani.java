import java.util.Scanner;

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

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();
                System.out.println("____________________________________________________________");

                if (command.equals("bye")) {
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                }

                System.out.println("     " + command);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
