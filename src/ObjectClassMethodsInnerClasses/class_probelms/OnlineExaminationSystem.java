package ObjectClassMethodsInnerClasses.class_probelms;
import java.util.*;

abstract class Question {

    protected int questionNumber;
    protected String questionText;

    public Question(int questionNumber, String questionText) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
    }

    public abstract boolean evaluateAnswer(String answer);
}

class MultipleChoiceQuestion extends Question {

    private String correctAnswer;

    public MultipleChoiceQuestion(int number, String text, String correctAnswer) {
        super(number, text);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluateAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {

    private String correctAnswer;

    public TrueFalseQuestion(int number, String text, String correctAnswer) {
        super(number, text);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluateAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Attempt {

    private Student student;
    private Examination examination;
    private Map<Integer, String> answers;
    private boolean submitted;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        answers = new HashMap<>();
        submitted = false;
    }

    public void answerQuestion(int questionNumber, String answer) {

        if (submitted) {
            System.out.println("Cannot change answer after submission.");
            return;
        }

        answers.put(questionNumber, answer);
        System.out.println("Question " + questionNumber
                + " answered with '" + answer + "'.");
    }

    public void submit() {

        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }

        submitted = true;

        System.out.println("Examination '"
                + examination.getTitle()
                + "' submitted successfully.");

        evaluate();
    }

    private void evaluate() {

        int correct = 0;

        for (Question question : examination.getQuestions()) {

            String answer =
                    answers.get(question.questionNumber);

            if (answer != null &&
                    question.evaluateAnswer(answer)) {
                correct++;
            }
        }

        System.out.println("Result for '"
                + examination.getTitle()
                + "' attempt: "
                + correct + "/"
                + examination.getQuestions().size()
                + " correct");
    }
}

class Examination {

    private String title;
    private List<Question> questions;

    public Examination(String title) {
        this.title = title;
        questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Attempt startAttempt(Student student) {

        System.out.println("Examination '"
                + title
                + "' started by "
                + student.getName()
                + ".");

        return new Attempt(student, this);
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Student student = new Student("Student");

        Examination exam =
                new Examination("Math Quiz");

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        1,
                        "2 + 2 = ?",
                        "A"
                )
        );

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        2,
                        "5 + 5 = ?",
                        "B"
                )
        );

        Attempt attempt =
                exam.startAttempt(student);

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        attempt.submit();

        // This will not be allowed
        attempt.answerQuestion(1, "B");
    }
}