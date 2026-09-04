package string.class_problems;
import java.util.*;

public class RockPaperScissorsGame {

    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove))
            return "Draw";

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper")))
            return "Player Wins";

        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random random = new Random();

        int wins = 0, losses = 0, draws = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.print("Enter your move: ");
            String player = sc.next();

            String computer = moves[random.nextInt(3)];
            String result = playRound(player, computer);

            System.out.println("Round " + i + ": " + result);

            if (result.equals("Player Wins"))
                wins++;
            else if (result.equals("Computer Wins"))
                losses++;
            else
                draws++;
        }

        System.out.println("\nWins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.println("Win Percentage: " + (wins / 5.0) * 100 + "%");
    }
}
