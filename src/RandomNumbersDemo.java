import java.util.*;

public class RandomNumbersDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lower = scanner.nextInt(); // lower bound of the interval
        int upper = scanner.nextInt(); // upper bound of the interval
        Random random = new Random();

        int intervalLength = upper - lower + 1; // length of the interval

        System.out.println(random.nextInt(intervalLength) + lower);
        System.out.println(random.nextInt(intervalLength) + lower);
        System.out.println(random.nextInt(intervalLength) + lower);
        System.out.println(random.nextInt(intervalLength) + lower);
    }
}