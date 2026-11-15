package JavaConcepts.class_probelms;
import java.util.*;

class Job {
    String name;
    int remainingTime;

    Job(String name, int time) {
        this.name = name;
        this.remainingTime = time;
    }
}

public class LabScheduler {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String label = sc.next();
        int quantum = sc.nextInt();

        Queue<Job> queue = new LinkedList<>();

        while (sc.hasNext()) {
            String name = sc.next();
            int time = sc.nextInt();

            queue.add(new Job(name, time));
        }

        int currentTime = 0;

        StringBuilder output = new StringBuilder();

        StringBuilder finish = new StringBuilder();

        while (!queue.isEmpty()) {

            Job job = queue.poll();

            int start = currentTime;

            int runTime = Math.min(
                    quantum, job.remainingTime
            );

            currentTime += runTime;

            job.remainingTime -= runTime;

            output.append(job.name)
                    .append("(")
                    .append(start)
                    .append("-")
                    .append(currentTime)
                    .append(") ");

            if (job.remainingTime > 0) {
                queue.add(job);
            }
            else {
                if (finish.length() > 0) {
                    finish.append(", ");
                }

                finish.append(job.name)
                        .append(" ")
                        .append(currentTime);
            }
        }

        System.out.println(output.toString().trim());

        System.out.println("finish " + finish);
    }
}