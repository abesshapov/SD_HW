import java.util.Scanner;

class CommandHelper {
    public static void handler() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the students amount: ");
        int n = input.nextInt();
        Journal studentJournal = new Journal(n);
        System.out.println("The journal is filled.");
        System.out.println("/h - list of students;");
        System.out.println("/r - ask a question;");
        System.out.println("/l - list of students with grades;");
        System.out.println("/help - help;");
        System.out.println("To exit type 'enough';");
        System.out.print("Enter command: ");
        String answer = input.nextLine();
        while (!answer.equals("enough")) {
            if (answer.equals("/help")) {
                System.out.println("/h - list of students;");
                System.out.println("/r - ask a question;");
                System.out.println("/l - list of students with grades;");
                System.out.println("/help - help;");
                System.out.println("To exit type 'enough';");
            } else if (answer.equals("/h")) {
                studentJournal.writeAllStudents();
            } else if (answer.equals("/r")) {
                studentJournal.askStudent();
            } else if (answer.equals("/l")) {
                studentJournal.writeAnsweredStudents();
            } else {
                System.out.println("Command is unknown");
            }
            System.out.print("Enter command: ");
            answer = input.nextLine();
        }
        System.out.println("My work here is done!");
        input.close();
    }
}
