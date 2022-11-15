import java.lang.Math;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
abstract class Person {
    protected String Name;
    protected String Surname;
    Person(String name, String surname) {
        Name = name;
        Surname = surname;
    }
}

class Attendant extends Person {
    String[] KnownQuestions;
    String[] KnownAnswers;
    boolean IsPresent;
    Attendant(String name, String surname) {
        super(name, surname);
        KnownQuestions = new String[4];
        KnownAnswers = new String[4];
        RandomTeacher.LearnStudent(this);
        IsPresent = isPresent();
    }
    private boolean isPresent() {
        return Math.random() > 0.3?true:false;
    }
    boolean questionAnswered(String question) {
        for (int i = 0; i < 4; i++) {
            if (KnownQuestions[i] == question) {
                System.out.println(KnownAnswers[i]);
                return true;
            }
        }
        System.out.println("I don't know...");
        return false;
    }
}

class RandomTeacher {
    static Random randomizer = new Random();
    static String[] Questions = {"The first letter of the alphabet?", "The president of the USA is?",
            "The first day of the week?", "The life of...?"};
    static String[] Answers = {"a", "old asl", "monday", "Pablo"};
    public static void LearnStudent(Attendant attendant) {
        int learntQuestions = 0;
        for (int i = 0; i < 4; i++) {
            if (Math.random() > 0.5) {
                attendant.KnownQuestions[learntQuestions] = Questions[i];
                attendant.KnownAnswers[learntQuestions++] = Answers[i];
            }
        }
    }
    public static Attendant[] GenerateStudents(int n) {
        String[] names = {"Adam", "Bozo", "Cuz", "Demar", "Earl", "Fakboi"};
        String[] surnames = {"Aldama", "Bedolaga", "Crystall", "DeLalo", "Er", "Fairytale"};
        Attendant[] students = new Attendant[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Attendant(names[randomizer.nextInt(6)], surnames[randomizer.nextInt(6)] + " the " + (i + 1));
        }
        return students;
    }
    public static boolean AskStudent(Attendant student) {
        String question = Questions[randomizer.nextInt(4)];
        System.out.println("teacher: " + question);
        System.out.print(student.Name + " " + student.Surname + ": ");
        boolean isCorrect = student.questionAnswered(question);
        System.out.println("teacher: " + (isCorrect?"Correct":"Incorrect"));
        return isCorrect;
    }
}
class Journal {
    static Random randomizer = new Random();
    Attendant[] students;
    String[] arePresent;
    int[] marks;
    Attendant[] answeredStudents;
    int studentAnswers;
    int studentsNotPresent;
    Journal(int n) {
        students = RandomTeacher.GenerateStudents(n);
        arePresent = new String[n];
        marks = new int[n];
        studentAnswers = 0;
        studentsNotPresent = 0;
        answeredStudents = new Attendant[n];
        for (int i = 0; i < n; i++) {
            arePresent[i] = students[i].IsPresent? "Present":"Not present";
            marks[i] = students[i].IsPresent? -1:0;
            studentsNotPresent += students[i].IsPresent? 0:1;
        }
    }
    public void WriteAllStudents() {
        System.out.println("Students:");
        for (int i = 0; i < students.length; i++) {
            String mark = marks[i] == -1? "not given":String.valueOf(marks[i]);
            System.out.println(students[i].Name + " " + students[i].Surname +
                    " is " + arePresent[i] + ", mark: " + mark);
        }
    }
    public void AskStudent() {
        if (studentAnswers + studentsNotPresent == students.length) {
            System.out.println("All present students answered");
            return;
        }
        int studentNumber = randomizer.nextInt(students.length);
        while (!students[studentNumber].IsPresent || marks[studentNumber] != -1) {
            studentNumber = randomizer.nextInt(students.length);
        }
        marks[studentNumber] = RandomTeacher.AskStudent(students[studentNumber])?10:0;
        studentAnswers += 1;
    }
    public void WriteAnsweredStudents() {
        System.out.println("Students with marks:");
        for (int i = 0; i < students.length; i++) {
            if (marks[i] == -1) {
                continue;
            }
            System.out.println(students[i].Name + " " + students[i].Surname +
                    " is " + arePresent[i] + ", mark: " + marks[i]);
        }
    }
}
class CommandHelper {
    public static void Handler() {
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
                studentJournal.WriteAllStudents();
            } else if (answer.equals("/r")) {
                studentJournal.AskStudent();
            } else if (answer.equals("/l")) {
                studentJournal.WriteAnsweredStudents();
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
public class Main {
    public static void main(String[] args) {
        CommandHelper.Handler();
    }
}