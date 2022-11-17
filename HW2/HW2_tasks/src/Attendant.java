class Attendant extends Person {
    String[] knownQuestions;
    String[] knownAnswers;
    boolean isPresent;
    Attendant(String name, String surname) {
        super(name, surname);
        knownQuestions = new String[4];
        knownAnswers = new String[4];
        RandomTeacher.learnStudent(this);
        isPresent = isPresent();
    }

    private boolean isPresent() {
        return Math.random() > 0.3?true:false;
    }

    boolean questionAnswered(String question) {
        for (int i = 0; i < 4; i++) {
            if (knownQuestions[i] == question) {
                System.out.println(knownAnswers[i]);
                return true;
            }
        }
        System.out.println("I don't know...");
        return false;
    }
}
