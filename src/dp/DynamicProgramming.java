package dp;

import java.util.Scanner;

public class DynamicProgramming {

    static int [] fibo_memo;

    public static void calfibo() {

        Scanner scan = new Scanner(System.in);
        int a= scan.nextInt();

        fibo_memo = new int[45];
        fibo(a);
    }

    public static void fibo(int a) {
        fibo_memo[0] = 0;
        fibo_memo[1] = 1;

        for(int i = 2;(i <= a)&&(i < 45);i++) {
            fibo_memo[i] = fibo_memo[i - 2] + fibo_memo[i - 1];
        }
        System.out.println(fibo_dp(a));
    }

    public static int fibo_dp(int a) {
        if(a < 45)
            return fibo_memo[a];
        else
            return fibo_dp(a - 1) + fibo_dp(a - 2);
    }
}
