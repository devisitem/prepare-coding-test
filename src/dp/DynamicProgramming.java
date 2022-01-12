package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

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

    public static void practiceDP1() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int [] T = new int[N];
        int [] P = new int[N];

        for(int i = 0;i < N;i++) {
            T[i] = Integer.parseInt(tokenizer.nextToken());
            P[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int [] dp = new int[N + 1];

        for(int i = 0;i < N;i++) {
            System.out.println("i = " + i);
            /// i일에 시작했을때, T[i]일동안 하게될 경우 N일 이내에 끝나야함
            if(i + T[i] <= N) {
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }

            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            for(int j = 0;j < dp.length;j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println(dp[N]);
    }

}
