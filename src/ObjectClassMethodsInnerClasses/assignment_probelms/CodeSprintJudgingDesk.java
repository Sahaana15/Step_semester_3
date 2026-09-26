package ObjectClassMethodsInnerClasses.assignment_probelms;
import java.util.*;

interface ScoringRule {
    double calculateScore(double idea, double execution, double presentation);
}

class InnovationScoring implements ScoringRule {

    @Override
    public double calculateScore(double idea, double execution,
                                 double presentation) {
        return idea * 0.50
                + execution * 0.30
                + presentation * 0.20;
    }
}

class OpenScoring implements ScoringRule {

    @Override
    public double calculateScore(double idea, double execution,
                                 double presentation) {
        return (idea + execution + presentation) / 3.0;
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

class Project {

    private String projectName;

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }
}

class Team {

    private String teamName;
    private List<Student> members;
    private ScoringRule scoringRule;
    private Project project;

    public Team(String teamName, List<Student> members,
                ScoringRule scoringRule) {

        this.teamName = teamName;
        this.members = members;
        this.scoringRule = scoringRule;
    }

    public boolean validTeamSize() {
        return members.size() >= 2 && members.size() <= 4;
    }

    public String getTeamName() {
        return teamName;
    }

    public void submitProject(String projectName) {

        if (project != null) {
            System.out.println("Project already submitted.");
            return;
        }

        project = new Project(projectName);

        System.out.println(
                "Project '" + projectName
                        + "' submitted by " + teamName + "."
        );
    }

    public Project getProject() {
        return project;
    }

    public double calculateScore(double idea,
                                 double execution,
                                 double presentation) {

        return scoringRule.calculateScore(
                idea, execution, presentation
        );
    }
}

class Judge {

    private String name;

    public Judge(String name) {
        this.name = name;
    }

    public void scoreProject(Project project,
                             double idea,
                             double execution,
                             double presentation,
                             Hackathon hackathon) {

        hackathon.recordScore(
                project,
                idea,
                execution,
                presentation
        );
    }
}

class Score {

    private Project project;
    private double finalScore;

    public Score(Project project, double finalScore) {
        this.project = project;
        this.finalScore = finalScore;
    }

    public double getFinalScore() {
        return finalScore;
    }
}

class Hackathon {

    private String name;
    private String state;
    private List<Team> teams;
    private Set<Student> registeredStudents;
    private Map<Project, Score> scores;

    public Hackathon(String name) {

        this.name = name;
        state = "OPEN";
        teams = new ArrayList<>();
        registeredStudents = new HashSet<>();
        scores = new HashMap<>();
    }

    public boolean registerTeam(Team team) {

        if (!team.validTeamSize()) {

            System.out.println(
                    "Registration failed: A team must have "
                            + "2 to 4 members."
            );

            return false;
        }

        for (Student student : getStudents(team)) {

            if (registeredStudents.contains(student)) {

                System.out.println(
                        "Registration failed: "
                                + student.getName()
                                + " already belongs to a team."
                );

                return false;
            }
        }

        teams.add(team);

        registeredStudents.addAll(
                getStudents(team)
        );

        System.out.println(
                "Team " + team.getTeamName()
                        + " registered ("
                        + getStudents(team).size()
                        + " members)."
        );

        return true;
    }

    private List<Student> getStudents(Team team) {

        try {
            java.lang.reflect.Field field =
                    Team.class.getDeclaredField("members");

            field.setAccessible(true);

            return (List<Student>) field.get(team);

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void startJudging() {
        state = "JUDGING";
    }

    public void recordScore(Project project,
                            double idea,
                            double execution,
                            double presentation) {

        if (state.equals("PUBLISHED")) {

            System.out.println(
                    "Rescore rejected: Results have already been published."
            );

            return;
        }

        Team team = findTeam(project);

        if (team == null) {
            return;
        }

        double finalScore =
                team.calculateScore(
                        idea,
                        execution,
                        presentation
                );

        scores.put(
                project,
                new Score(project, finalScore)
        );

        System.out.println(
                "Score recorded for '"
                        + project.getProjectName()
                        + "'."
        );

        System.out.printf(
                "Final score: %.2f%n",
                finalScore
        );
    }

    private Team findTeam(Project project) {

        for (Team team : teams) {

            if (team.getProject() == project) {
                return team;
            }
        }

        return null;
    }

    public void publishResults() {

        state = "PUBLISHED";

        System.out.println("Results published.");
    }
}

public class CodeSprintJudgingDesk {

    public static void main(String[] args) {

        Hackathon hackathon =
                new Hackathon("Code Sprint");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student kiran = new Student("Kiran");

        Team byteBusters =
                new Team(
                        "ByteBusters",
                        Arrays.asList(asha, ravi, neha),
                        new InnovationScoring()
                );

        hackathon.registerTeam(byteBusters);

        Team soloCoder =
                new Team(
                        "SoloCoder",
                        Arrays.asList(kiran),
                        new OpenScoring()
                );

        hackathon.registerTeam(soloCoder);

        byteBusters.submitProject("SmartAttend");

        hackathon.startJudging();

        Judge judge =
                new Judge("Judge 1");

        judge.scoreProject(
                byteBusters.getProject(),
                8,
                7,
                9,
                hackathon
        );

        hackathon.publishResults();

        judge.scoreProject(
                byteBusters.getProject(),
                10,
                7,
                9,
                hackathon
        );
    }
}
