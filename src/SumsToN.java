import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SumsToN {

    private static int[] numbers;
    private static ArrayList<String> printed = new ArrayList<>();

    public static void GenerateSums(int n, String s){
        String output = "";

        // Calculate the sum of all nums in the string
        // sum = 0 if string is empty
        int sum = s.equals("") ? 0 : Arrays.stream(s.split(" \\+ "))
                                     .mapToInt(i -> Integer.parseInt(i))
                                     .sum();
        if(sum == n){
            // Sort the string of ints, placing + ops in the process
            String sortedS = Arrays.stream(s.split(" \\+ "))
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
                    // If first num of string s, don't print a + op
                    output = s.equals("") ? (s + num) : (s + " + " + num);
                    GenerateSums(n, output);
            }
        }
    }

    public static void main(String[] args) {
        int n = 0;
        Scanner scan = new Scanner(System.in);

        while(n == 0){
            System.out.print("Enter n: ");
            try{
                n = scan.nextInt();
                if(n > 0){
                    break;
                } else{
                    System.out.println("n must be positive!");
                    n = 0;
                }
            } catch(Exception InputMismatchException){
                System.out.println("n must be an integer!");
                scan.nextLine();
            }
        }

        numbers = new int[n];
        // generate list of viable numbers for addition into n
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }

        GenerateSums(n, "");
    }
}