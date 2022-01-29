package dp.bj;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
     * 하샤드수
     * @throws Exception
     */
    public static void harshadNumber() throws Exception {
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



    public static void saveFailureLate() {
        int N = 5;
        int stages [] = {2, 1, 2, 6, 2, 4, 3, 3};
        int result [] = new int [N + 2];



        for(int i = 0;i < stages.length;i++) {
            result[stages[i]]++;
        }
        int people = stages.length;

        for(int i = 0;i < N;i++) {
            people -= result[i];
            float failure = ((float) result[i+1]) / ((float) people);
            System.out.printf("int %1.1f = %d / %d ",failure, result[i+1], people);
            System.out.println();
        }

        System.out.println(Arrays.toString(result));

    }


    /**
     * <b>문제</b>
     * <hr>
     * <p>상담원으로 일하고 있는 백준이는 퇴사를 하려고 한다.</p>
     * <p>오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.</p>
     * <p>백준이는 비서에게 최대한 많은 상담을 잡으라고 부탁을 했고, 비서는 하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.</p>
     * <p>각각의 상담은 상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi로 이루어져 있다.</p>
     * <p>N = 7인 경우에 다음과 같은 상담 일정표를 보자.</p>
     * <table>
     *     <thead>
     *         <tr>
     *             <th>#</th>
     *             <th>[1일]</th>
     *             <th>[2일]</th>
     *             <th>[3일]</th>
     *             <th>[4일]</th>
     *             <th>[5일]</th>
     *             <th>[6일]</th>
     *             <th>[7일]</th>
     *         </tr>
     *     </thead>
     *     <tbody>
     *         <tr>
     *             <th>T[i]</th>
     *             <th>[3]</th>
     *             <th>[5]</th>
     *             <th>[1]</th>
     *             <th>[1]</th>
     *             <th>[2]</th>
     *             <th>[4]</th>
     *             <th>[2]</th>
     *         </tr>
     *         <tr>
     *             <th>P[i]</th>
     *             <th>[10]</th>
     *             <th>[20]</th>
     *             <th>[10]</th>
     *             <th>[20]</th>
     *             <th>[15]</th>
     *             <th>[40]</th>
     *             <th>[200]</th>
     *         </tr>
     *     </tbody>
     * </table>
     * <p>1일에 잡혀있는 상담은 총 3일이 걸리며, 상담했을 때 받을 수 있는 금액은 10이다. 5일에 잡혀있는 상담은 총 2일이 걸리며, 받을 수 있는 금액은 15이다.</p>
     * <p>상담을 하는데 필요한 기간은 1일보다 클 수 있기 때문에, 모든 상담을 할 수는 없다. 예를 들어서 1일에 상담을 하게 되면, 2일, 3일에 있는 상담은 할 수 없게 된다. 2일에 있는 상담을 하게 되면, 3, 4, 5, 6일에 잡혀있는 상담은 할 수 없다.</p>
     * <p>또한, N+1일째에는 회사에 없기 때문에, 6, 7일에 있는 상담을 할 수 없다.</p>
     * <p>퇴사 전에 할 수 있는 상담의 최대 이익은 1일, 4일, 5일에 있는 상담을 하는 것이며, 이때의 이익은 10+20+15=45이다.</p>
     * <p>상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.</p>
     * <hr>
     * <p>입력 : </p>
     * <p>첫째 줄에 N (1 ≤ N ≤ 15)이 주어진다.</p>
     * <p>둘째 줄부터 N개의 줄에 Ti와 Pi가 공백으로 구분되어서 주어지며, 1일부터 N일까지 순서대로 주어진다. (1 ≤ Ti ≤ 5, 1 ≤ Pi ≤ 1,000)</p>
     * <p>출력 : </p>
     * <p>첫째 줄에 백준이가 얻을 수 있는 최대 이익을 출력한다.</p>
     *
     */
    public static void leaveWork() throws Exception {
        // 7 3 10 5 20 1 10 1 20 2 15 4 40 2 200
        Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();

        int [] T = new int[N + 5];
        int [] P = new int[N + 5];

        for(int i = 0; i < N;i++) {
            T[i] = scanner.nextInt();
            P[i] = scanner.nextInt();
        }

        int [] dp = new int [N + 5];

        for(int i = 0;i < N;i++) {
            if((i + T[i]) < (N + 1)) {
                dp[i + T[i]] = Math.max(dp[i + T[i]], P[i] + dp[i]);
            }

            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            System.out.printf("(%d) %s", i , Arrays.toString(dp));
            System.out.println();
        }

    }

    public static void nonFinishedPlayer() throws Exception {
        String [] participant= {"marina", "josipa", "nikola", "nikola", "vinko", "filipa"};
        String [] completion = {"josipa", "filipa", "marina", "nikola", "nikola"};

        Map<String, Long> collect = Arrays.stream(participant).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (int i = 0; i < completion.length; i++) {
            if(collect.get(completion[i]) < 1) {

            }
        }

        System.out.println("collect = " + collect);


    }
}
