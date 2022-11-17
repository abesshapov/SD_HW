import java.util.Random;

class Journal {
    static Random randomizer = new Random();
    Attendant[] students;
    String[] arePresent;
    int[] marks;
    Attendant[] answeredStudents;
    int studentAnswers;
    int studentsNotPresent;
    Journal(int n) {
        students = RandomTeacher.generateStudents(n);
        arePresent = new String[n];
        marks = new int[n];
        studentAnswers = 0;
        studentsNotPresent = 0;
        answeredStudents = new Attendant[n];
        for (int i = 0; i < n; i++) {
            arePresent[i] = students[i].isPresent? "Present":"Not present";
            marks[i] = students[i].isPresent? -1:0;
            studentsNotPresent += students[i].isPresent? 0:1;
        }
    }

    public void writeAllStudents() {
        System.out.println("Students:");
        for (int i = 0; i < students.length; i++) {
            String mark = marks[i] == -1? "not given":String.valueOf(marks[i]);
            System.out.println(students[i].name + " " + students[i].surname +
                    " is " + arePresent[i] + ", mark: " + mark);
        }
    }

    public void askStudent() {
        if (studentAnswers + studentsNotPresent == students.length) {
            System.out.println("All present students answered");
            return;
        }
        int studentNumber = randomizer.nextInt(students.length);
        while (!students[studentNumber].isPresent || marks[studentNumber] != -1) {
            studentNumber = randomizer.nextInt(students.length);
        }
        marks[studentNumber] = RandomTeacher.askStudent(students[studentNumber])?10:0;
        studentAnswers += 1;
    }

    public void writeAnsweredStudents() {
        System.out.println("Students with marks:");
        for (int i = 0; i < students.length; i++) {
            if (marks[i] == -1) {
                continue;
            }
            System.out.println(students[i].name + " " + students[i].surname +
                    " is " + arePresent[i] + ", mark: " + marks[i]);
        }
    }
}
