package rockpaperscissors;

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;


public class Main {
     public enum State {
         WIN,
        LOSE,
        DRAW
    }
    public static void main(String[] args) {

         CircularList<String> options = new CircularList<>();



        // put your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);
        String filePath = "rating.txt"; // Replace with your file path
        int score = getScore(filePath, name);

        //Read the input with the list of options for the game (options are separated by comma).
        // If the input is an empty line, play with the default options;

        System.out.println("Enter the list of options separated by comma: ");
        String input = scanner.nextLine();
        if(input.isEmpty()) {
            options.append("rock");
            options.append("paper");
            options.append("scissors");

        }
        else {
            String[] parts = input.split(",");
            for(String part : parts) {
                options.append(part);
            }
        }
        System.out.println("Okay, let's start");


        while(true) {
            String userChoice = scanner.nextLine();
            if(userChoice.equals("!exit")) {
                System.out.println("Bye!");
                return;
            } else if (userChoice.equals("!rating")) {
                System.out.println("Your rating: " + score);
                continue;
            }

            boolean isValid = validateUserChoice(userChoice, options);
            if(!isValid) { // invalid input
                System.out.println("Invalid input");
                continue;
            }

            String computer = computerChoiceV2(options);
            score = ResultV2(userChoice, computer,score, options);
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



    public static int ResultV2(String user, String computer, int score, CircularList<String> options) {
         State state = ResultV2Helper(user, computer, options);
         if( state == State.WIN) {
            score += 100;
            System.out.printf("Well done. The computer chose %s and failed%n", computer.toString().toLowerCase());
            return score;
        } else if (state == State.DRAW) {
             score += 50;
            System.out.printf("There is a draw (%s)%n", computer);
            return score;
         } else {
            System.out.println("Sorry, but the computer chose " + computer);
            return score;
        }

    }



    // helper method for ResultV2
    // returns false if computer wins
    // returns true if user wins

    public static State ResultV2Helper(String user, String computer, CircularList<String> options) {
         if(user.equals(computer)) {
             return State.DRAW; // draw
         }
         Node<String> current = options.getHead();
         int count = options.size / 2;
         while (!current.data.equals(user)) {
                current = current.next;
         }

         // now we have found the user choice
        // every choice that beats the user choice is in the next count nodes
         while(count > 0) {
             current = current.next;
             if(current.data.equals(computer)) {
                 return State.LOSE; // computer wins
             }
             count--;
         }
         return State.WIN; // user wins
    }

    public static boolean validateUserChoice(String userChoice, CircularList<String> options) {
         Node<String> current = options.getHead();
         int count = options.size;
         while(count > 0) {
             if(current.data.equals(userChoice)) {
                 return true;
             }
             current = current.next;
             count--;
         }
         return false;
    }





    public static String computerChoiceV2(CircularList<String> options) {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        int choice = random.nextInt(options.size); // random number between 0 and size - 1

        Node<String> current = options.getHead();
        while(choice > 0) {
            current = current.next;
            choice--;
        }
        return current.data;
    }




}



