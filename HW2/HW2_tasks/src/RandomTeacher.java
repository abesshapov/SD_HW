import java.util.Random;

class RandomTeacher {
    static Random randomizer = new Random();
    static String[] questions = {"The first letter of the alphabet?", "The president of the USA is?",
            "The first day of the week?", "The life of...?"};
    static String[] answers = {"a", "old asl", "monday", "Pablo"};
    public static void learnStudent(Attendant attendant) {
        int learntQuestions = 0;
        for (int i = 0; i < 4; i++) {
            if (Math.random() > 0.5) {
                attendant.knownQuestions[learntQuestions] = questions[i];
                attendant.knownAnswers[learntQuestions++] = answers[i];
            }
        }
    }

    public static Attendant[] generateStudents(int n) {
        String[] names = {"Adam", "Bozo", "Cuz", "Demar", "Earl", "Fakboi"};
        String[] surnames = {"Aldama", "Bedolaga", "Crystall", "DeLalo", "Er", "Fairytale"};
        Attendant[] students = new Attendant[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Attendant(names[randomizer.nextInt(6)], surnames[randomizer.nextInt(6)] + " the " + (i + 1));
        }
        return students;
    }

    public static boolean askStudent(Attendant student) {
        String question = questions[randomizer.nextInt(4)];
        System.out.println("teacher: " + question);
        System.out.print(student.name + " " + student.surname + ": ");
        boolean isCorrect = student.questionAnswered(question);
        System.out.println("teacher: " + (isCorrect?"Correct":"Incorrect"));
        return isCorrect;
    }
}