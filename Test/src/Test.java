import java.util.Scanner;

/**
 * Created by linyu on 4/27/14.
 */
public class Test {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(N);

        int[] bits = new int[N];
        for (int i = 0; i < N; i++) {
            bits[i] = sc.nextInt();
        }

        int rt = 0;
        //srt = findmax(bits);
        System.out.println(N);
    }

    private static int[] cacheOnesBefore(int[] bits) {
        int len = bits.length;
        int[] sum = new int[len + 1];

        sum[0] = 0;
        for (int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + bits[i];
        }

        return sum;
    }

    private static int findMax(int[] bits) {
        int[] table = cacheOnesBefore(bits);
        int len = bits.length;
        int maxOnes = 0;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int onesBefore = table[Math.max(0, i - 1)];
                int zerosBetween = table[Math.min(len, j + 1)] - table[Math.max(0, i - 1)];
                int onesBetween = j - i + 1 - zerosBetween;
                int onesAfter = table[len] - table[Math.min(len, j + 1)];
                maxOnes = Math.max(maxOnes, onesBefore + onesBetween + onesAfter);
            }
        }

        return maxOnes;
    }

}
