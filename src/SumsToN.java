import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SumsToN {

    private static int[] numbers;
    private static ArrayList<String> printed = new ArrayList<>();

    public static void GenerateSums(int n, String s){
        // Calculate the sum of all nums in the string
        // sum = 0 if string is empty
        int sum = s.equals("") ? 0 : Pattern.compile("")
                                     .splitAsStream(s)
                                     .mapToInt(Integer::parseInt)
                                     .sum();
        if(sum == n){
            // Sort the string of ints, placing + ops in the process
            String sortedS = Arrays.stream(s.split(""))
                            .mapToInt(i -> Integer.parseInt(i))
                            .sorted()
                            .mapToObj(Integer::toString)
                            .collect(Collectors.joining(" + "));
            // Print the sorted num only if it hasn't been printed already.
            if(!printed.contains(sortedS)) {
                printed.add(sortedS);
                System.out.println(sortedS);
            }
        }
        else {
            // Recursively call GenerateSums() until it finds a fit
            for(int num : numbers){
                if((sum + num <= n) && (num != 0))
                    GenerateSums(n, s + num);
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter n: ");
        int n = new Scanner( System.in ).nextInt();

        numbers = new int[n];
        // generate list of viable numbers for addition into n
        for(int i = 0; i < n; i++) {
            if (i == 9)
                break;
            numbers[i] = i + 1;
        }

        GenerateSums(n, "");
    }
}