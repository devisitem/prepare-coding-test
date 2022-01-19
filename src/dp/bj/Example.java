package dp.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Example {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * <b>문제</b>
     * <hr>
     * <p>상근이는 요즘 설탕공장에서 설탕을 배달하고 있다. 상근이는 지금 사탕가게에 설탕을 정확하게 N킬로그램을 배달해야 한다. 설탕공장에서 만드는 설탕은 봉지에 담겨져 있다. 봉지는 3킬로그램 봉지와 5킬로그램 봉지가 있다.</p>
     * <p>상근이는 귀찮기 때문에, 최대한 적은 봉지를 들고 가려고 한다. 예를 들어, 18킬로그램 설탕을 배달해야 할 때, 3킬로그램 봉지 6개를 가져가도 되지만, 5킬로그램 3개와 3킬로그램 1개를 배달하면, 더 적은 개수의 봉지를 배달할 수 있다.</p>
     * <p>상근이가 설탕을 정확하게 N킬로그램 배달해야 할 때, 봉지 몇 개를 가져가면 되는지 그 수를 구하는 프로그램을 작성하시오.</p>
     * <hr>
     * <p><b>입력:</b> 첫째 줄에 N이 주어진다. (3 ≤ N ≤ 5000)</p>
     * <p><b>출력:</b> 상근이가 배달하는 봉지의 최소 개수를 출력한다. 만약, 정확하게 N킬로그램을 만들 수 없다면 -1을 출력한다.</p>
     * @throws Exception
     */
    public static void deliveryForSugar() throws Exception {

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        if(N >= 3)
            dp[3] = 1;
        if(N >= 5)
            dp[5] = 1;

        for(int i = 6;i <= N;i++) {
            if(i % 5 == 0) {
                dp[i] = dp[i - 5] + 1;
            } else if(i % 3 == 0){
                dp[i] = dp[i - 3] + 1;
            } else {
                if(dp[i - 3] != 0 && dp[i - 5] != 0) {
                    dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
                }
            }
        }
        if(dp[N] == 0) {
            System.out.println("-1");
            return;
        }
        System.out.println(dp[N]);
    }

    /**
     * <b>문제</b>
     * <hr>
     * <p>보물 수집가 태구는 고대사원에서 보물을 훔쳐 집으로 돌아가는길에 아주 약해보이는 외나무 다리를 건너야 하는 상황을 마주했습니다.</p>
     * <p>외나무 다리는 한번에 68키로의 무게 밖에 견딜수 없기에 60키로인 태구는 가지고 있는 짐들의 무게를 계산하여 최소한의 이동으로 보물들을 옮기려합니다.</p>
     * <p>예를들어, 보물의 무게가 list [1, 3, 5, 2, 7, 4, 4]에 각각 있을때 최대 추가 되는 무게 N 8이 주어질때 태구가 최소 몇번의 이동으로 보물을 모두 옮길수 있는지 작성합니다.</p>
     * <hr>
     * <p>입력값 예: 8 1 3 5 2 7 4 4</p>
     * <p>출력값 예: 4 [3, 5],[1, 7],[2],[4, 4]</p>
     * @throws Exception
     */
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

    /**
     * 돌다리 건너기
     * @throws Exception
     */
    public static void jumpAndJump() throws Exception {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();

        String num = String.valueOf(x);
        int result = 0;
        for(int i = 0;i < num.length();i++) {
            result += (num.charAt(i)) - 48;
        }

        if(x % result == 0) {
            System.out.println("ㅎㅏ샤드 수 " + x);
        }
    }
    public static void lint() {
        int N = 5;
        int stages [] = {2, 1, 2, 6, 2, 4, 3, 3};
        int result [] = new int [N + 2];



        for(int i = 0;i < stages.length;i++) {
            result[stages[i]]++;
        }
        int people = stages.length;

        for(int i = 0;i < N;i++) {
            people -= result[i];
            int failure = result[i+1] / people ;
            System.out.printf("int %d = %d / %d ",failure, result[i+1], people);
            System.out.println();
        }

        System.out.println(Arrays.toString(result));

    }
}
