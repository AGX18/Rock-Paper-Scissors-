package rockpaperscissors;

import java.util.Scanner;
import java.util.Random;
public class Main {
     public enum Choice {
        ROCK,
        PAPER,
        SCISSORS
    }
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        Choice user = Choice.valueOf(userChoice.toUpperCase());
        Choice computer = ComputerChoice();


        String result = Result(user, computer);
        System.out.println(result);

    }
    public static String Result(Choice user, Choice computer) {
        if (user == computer) {
            return "There is a draw (%s)".formatted(computer.toString().toLowerCase());
        } else if (user == Choice.ROCK && computer == Choice.SCISSORS) {
            return "Well done. The computer chose scissors and failed";
        } else if (user == Choice.PAPER && computer == Choice.ROCK) {
            return "Well done. The computer chose rock and failed";
        } else if (user == Choice.SCISSORS && computer == Choice.PAPER) {
            return "Well done. The computer chose paper and failed";
        } else {
            return "Sorry, but the computer chose " + computer;
        }
    }
    public static Choice ComputerChoice() {
        Random random = new Random();
        int choice = random.nextInt(3);
        switch (choice) {
            case 0 -> {
                return Choice.ROCK;
            }
            case 1 -> {
                return Choice.PAPER;
            }
            case 2 -> {
                return Choice.SCISSORS;
            }
        }
        return null;
    }
}



