package rockpaperscissors;

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;


public class Main {
     public enum Choice {
        ROCK,
        PAPER,
        SCISSORS,
         EXIT,
         RATING


    }
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);
        String filePath = "rating.txt"; // Replace with your file path
        int score = getScore(filePath, name);


        while(true) {
            String userChoice = scanner.nextLine();
            Choice user = userChoice(userChoice, score);
            if(user == null || user == Choice.RATING) { // invalid input
                continue;
            }
            else if (user == Choice.EXIT){
                return;
            }
            Choice computer = computerChoice();
            score = Result(user, computer,score);
        }

     }



    private static int getScore(String filePath, String name) {
         int score = 0;
        try (Scanner scores = new Scanner(new File(filePath))) {
            while (scores.hasNextLine()) {
                String line = scores.nextLine();
                String[] parts = line.split(" ");
                if(name.equals(parts[0])) {
                    score = Integer.parseInt(parts[1]);
                    return score;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); // Handle any IOException that may occur
        }
        return score;
    }


    public static int Result(Choice user, Choice computer, int score) {
        if (user == computer) {
            score += 50;
            System.out.println("There is a draw (%s)".formatted(computer.toString().toLowerCase()));
        } else if (user == Choice.ROCK && computer == Choice.SCISSORS) {
            score += 100;
            System.out.println("Well done. The computer chose scissors and failed");
        } else if (user == Choice.PAPER && computer == Choice.ROCK) {
            score += 100;
            System.out.println("Well done. The computer chose rock and failed");
        } else if (user == Choice.SCISSORS && computer == Choice.PAPER) {
            score += 100;
            System.out.println("Well done. The computer chose paper and failed");
        } else {
            System.out.println("Sorry, but the computer chose " + computer);
        }
        return score;
    }



    public static Choice userChoice(String userChoice, int score) {
        switch (userChoice) {
            case "rock" -> {
                return Choice.ROCK;
            }
            case "paper" -> {
                return Choice.PAPER;
            }
            case "scissors" -> {
                return Choice.SCISSORS;
            }
            case "!exit" -> {
                System.out.println("Bye!");
                return Choice.EXIT;
            }
            case "!rating" -> {
                System.out.println("Your rating: " + score);
                return Choice.RATING;
            }
            default -> System.out.println("Invalid input");

        }
        return null;
    }

    public static Choice computerChoice() {
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



