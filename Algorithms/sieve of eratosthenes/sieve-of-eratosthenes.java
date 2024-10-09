import java.util.Scanner;

public class SieveOfEratosthenes {

    public static void sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        for (int p = 2; p <= n; p++) {
            if (prime[p])
                System.out.print(p + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the limit: ");
        int n = sc.nextInt();
        sieveOfEratosthenes(n);
        sc.close();
    }
}
