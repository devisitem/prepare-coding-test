package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class DynamicProgramming {

    static int [] fibo_memo;
    static int [] fiboArray1;

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
        // 7 3 10 5 20 1 10 1 20 2 15 4 40 2 200
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


    public static void carryForCargo() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        // 8 1 3 5 2 7 4 4
        float N = Float.parseFloat(tokenizer.nextToken());

        List<Float> list = new ArrayList<>();
        List<Float> count = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            list.add(Float.parseFloat(tokenizer.nextToken()));
        }

        int binIndex = 0;
        for(int i = 0; i < list.size();i++) {
            float max = N;
            if(count.size() == 0) count.add(0.0f);
            for(int j = binIndex;j < count.size();j++) {
                float remain = N - count.get(j);
                if(remain >= list.get(i)) {
                    count.set(j, count.get(j) + list.get(i));
                    binIndex = (count.size() - 1);
                } else if(remain < list.get(i)) {
                    count.add(list.get(i));
                    binIndex = (count.size() - 1);
                    break;
                }
            }

            String listString = Arrays.toString(list.toArray());
            String countString = Arrays.toString(count.toArray());
            System.out.printf(" i = %d list: %s, count: %s", i, listString, countString);
            System.out.println();
        }

    }

}
