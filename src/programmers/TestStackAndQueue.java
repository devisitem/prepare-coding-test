package programmers;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStackAndQueue {

    /**
     * <b>문제</b>
     * <hr>
     * <p>프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.</p>
     *
     * <p>또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.</p>
     *
     * <p>먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.</p>
     *
     * <h5>제한 사항</h5>
     *
     * <ul>
     * <li>작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.</li>
     * <li>작업 진도는 100 미만의 자연수입니다.</li>
     * <li>작업 속도는 100 이하의 자연수입니다.</li>
     * <li>배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.</li>
     * </ul>
     *
     * <h5>입출력 예</h5>
     * <table class="table">
     *         <thead><tr>
     * <th>progresses</th>
     * <th>speeds</th>
     * <th>return</th>
     * </tr>
     * </thead>
     *         <tbody><tr>
     * <td>[93, 30, 55]</td>
     * <td>[1, 30, 5]</td>
     * <td>[2, 1]</td>
     * </tr>
     * <tr>
     * <td>[95, 90, 99, 99, 80, 99]</td>
     * <td>[1, 1, 1, 1, 1, 1]</td>
     * <td>[1, 3, 2]</td>
     * </tr>
     * </tbody>
     *       </table>
     * <h5>입출력 예 설명</h5>
     *
     * <p>입출력 예 #1<br>
     * 첫 번째 기능은 93% 완료되어 있고 하루에 1%씩 작업이 가능하므로 7일간 작업 후 배포가 가능합니다.<br>
     * 두 번째 기능은 30%가 완료되어 있고 하루에 30%씩 작업이 가능하므로 3일간 작업 후 배포가 가능합니다. 하지만 이전 첫 번째 기능이 아직 완성된 상태가 아니기 때문에 첫 번째 기능이 배포되는 7일째 배포됩니다.<br>
     * 세 번째 기능은 55%가 완료되어 있고 하루에 5%씩 작업이 가능하므로 9일간 작업 후 배포가 가능합니다. </p>
     *
     * <p>따라서 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됩니다.</p>
     *
     * <p>입출력 예 #2<br>
     * 모든 기능이 하루에 1%씩 작업이 가능하므로, 작업이 끝나기까지 남은 일수는 각각 5일, 10일, 1일, 1일, 20일, 1일입니다. 어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능합니다.</p>
     *
     * <p>따라서 5일째에 1개의 기능, 10일째에 3개의 기능, 20일째에 2개의 기능이 배포됩니다.</p>
     *
     * <p>※ 공지 - 2020년 7월 14일 테스트케이스가 추가되었습니다.</p>
     *
     * <p><a href="https://programmers.co.kr/learn/courses/30/lessons/42586" target="_blank" rel="noopener">기능 개발</a></p>
     * @throws Exception
     */
    public static void developForFeature() throws Exception {
        int [] progresses = {93, 30, 55};
        int [] speeds = {1, 30, 5};

        Queue<Integer> que = new LinkedList<>();
        ArrayList<Integer> dayList = new ArrayList<>();
        for(int i = 0;i < progresses.length;i++) {
            int days = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);

            if( ! que.isEmpty() && que.peek() < days) {
                dayList.add(que.size());
                que.clear();
            }

            que.offer(days);
        }
        dayList.add(que.size());

        int [] answer = new int[dayList.size()];
        for(int i = 0;i < answer.length;i++) {
            answer[i] = dayList.get(i);
        }

    }

    /**
     * <b>문제</b>
     * <hr>
     * <p>일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다. 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.</p>
     *
     * <p>1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.</p>
     * <p>2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.</p>
     * <p>3. 그렇지 않으면 J를 인쇄합니다.</p>
     *
     * <p>예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.</p>
     *
     * <p>내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.</p>
     *
     * <p>현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.</p>
     *
     * <h5>제한사항</h5>
     *
     * <ul>
     * <li>현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.</li>
     * <li>인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.</li>
     * <li>location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.</li>
     * </ul>
     *
     * <h5>입출력 예</h5>
     * <table class="table">
     *         <thead><tr>
     * <th>priorities</th>
     * <th>location</th>
     * <th>return</th>
     * </tr>
     * </thead>
     *         <tbody><tr>
     * <td>[2, 1, 3, 2]</td>
     * <td>2</td>
     * <td>1</td>
     * </tr>
     * <tr>
     * <td>[1, 1, 9, 1, 1, 1]</td>
     * <td>0</td>
     * <td>5</td>
     * </tr>
     * </tbody>
     *       </table>
     * <h5>입출력 예 설명</h5>
     *
     * <p>예제 #1</p>
     *
     * <p>문제에 나온 예와 같습니다.</p>
     *
     * <p>예제 #2</p>
     *
     * <p>6개의 문서(A, B, C, D, E, F)가 인쇄 대기목록에 있고 중요도가 1 1 9 1 1 1 이므로 C D E F A B 순으로 인쇄합니다. </p>
     *
     * <p><a href="https://programmers.co.kr/learn/courses/30/lessons/42587" target="_blank" rel="noopener">프린터</a></p>
     * @throws Exception
     */
    public static void printer() throws Exception {
        int [] priorities = {2, 1, 3, 2};
        int location = 2;
        int answer = 0;

        Queue<Print> queue = new LinkedList<>();
        for(int i = 0;i < priorities.length;i++) {
            queue.offer(new Print(i, priorities[i]));
        }

        int now = 0;
        while( ! queue.isEmpty()) {
            Print current = queue.poll();
            boolean needAdd = false;
            for(Print mod : queue) {
                if(mod.getPriority() > current.getPriority()) {
                    needAdd = true;
                }
            }

            if(needAdd) {
                queue.offer(current);
            } else {
                now++;
                if(current.getLocation() == location) {
                    answer = now;
                    break;
                }
            }
        }
        System.out.println("answer = " + answer);


    }

    /**
     * <p>트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.</p>
     *
     * <p>예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다. 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.</p>
     * <table class="table">
     *         <thead><tr>
     * <th>경과 시간</th>
     * <th>다리를 지난 트럭</th>
     * <th>다리를 건너는 트럭</th>
     * <th>대기 트럭</th>
     * </tr>
     * </thead>
     *         <tbody><tr>
     * <td>0</td>
     * <td>[]</td>
     * <td>[]</td>
     * <td>[7,4,5,6]</td>
     * </tr>
     * <tr>
     * <td>1~2</td>
     * <td>[]</td>
     * <td>[7]</td>
     * <td>[4,5,6]</td>
     * </tr>
     * <tr>
     * <td>3</td>
     * <td>[7]</td>
     * <td>[4]</td>
     * <td>[5,6]</td>
     * </tr>
     * <tr>
     * <td>4</td>
     * <td>[7]</td>
     * <td>[4,5]</td>
     * <td>[6]</td>
     * </tr>
     * <tr>
     * <td>5</td>
     * <td>[7,4]</td>
     * <td>[5]</td>
     * <td>[6]</td>
     * </tr>
     * <tr>
     * <td>6~7</td>
     * <td>[7,4,5]</td>
     * <td>[6]</td>
     * <td>[]</td>
     * </tr>
     * <tr>
     * <td>8</td>
     * <td>[7,4,5,6]</td>
     * <td>[]</td>
     * <td>[]</td>
     * </tr>
     * </tbody>
     *       </table>
     * <p>따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.</p>
     *
     * <p>solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.</p>
     *
     * <h5>제한 조건</h5>
     *
     * <ul>
     * <li>bridge_length는 1 이상 10,000 이하입니다.</li>
     * <li>weight는 1 이상 10,000 이하입니다.</li>
     * <li>truck_weights의 길이는 1 이상 10,000 이하입니다.</li>
     * <li>모든 트럭의 무게는 1 이상 weight 이하입니다.</li>
     * </ul>
     *
     * <h5>입출력 예</h5>
     * <table class="table">
     *         <thead><tr>
     * <th>bridge_length</th>
     * <th>weight</th>
     * <th>truck_weights</th>
     * <th>return</th>
     * </tr>
     * </thead>
     *         <tbody><tr>
     * <td>2</td>
     * <td>10</td>
     * <td>[7,4,5,6]</td>
     * <td>8</td>
     * </tr>
     * <tr>
     * <td>100</td>
     * <td>100</td>
     * <td>[10]</td>
     * <td>101</td>
     * </tr>
     * <tr>
     * <td>100</td>
     * <td>100</td>
     * <td>[10,10,10,10,10,10,10,10,10,10]</td>
     * <td>110</td>
     * </tr>
     * </tbody>
     *       </table>
     * <p><a href="https://programmers.co.kr/learn/courses/30/lessons/42583" target="_blank" rel="noopener">다리를 지나는 트럭</a></p>
     *
     * <p>※ 공지 - 2020년 4월 06일 테스트케이스가 추가되었습니다.</p>
     *
     */
    public static void truckOnBridge() {
        int bridge_length = 2;
        int weight = 10;
        int [] truck_weights = {7,4,5,6};

        int answer = 0;
        int totalWeight = 0;
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0;i < truck_weights.length;i++) {
            int truck = truck_weights[i];

            while(true) {

                if(queue.isEmpty()) {
                    queue.offer(truck);
                    totalWeight += truck;
                    answer++;
                    break;
                } else if(queue.size() == bridge_length) {

                    totalWeight -= queue.poll().intValue();
                } else {
                    if(totalWeight + truck <= weight) {
                        queue.offer(truck);
                        totalWeight += truck;
                        answer++;
                        break;
                    } else {
                        queue.offer(0);
                        answer++;
                    }
                }
            }
        }
        answer += bridge_length;
        System.out.println("answer = " + answer);
    }

    public static void priceOfStock() {
        int [] prices = {1, 2, 3, 2, 3};
        int [] answer = new int [prices.length];
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0;i < prices.length;i++) {
            queue.offer(prices[i]);
        }

        int index = 1;
        while( ! queue.isEmpty()) {
            int price = queue.poll().intValue();
            int retain = 0;
            for(int i = index;i < prices.length;i++) {
                if(price > prices[i]) {
                    break;
                }
                retain++;
                answer[index] = retain;
            }
            index++;
        }
        System.out.println("answer = " + Arrays.toString(answer));
    }
}

class Print {

    private int location;
    private int priority;

    public Print(int location, int priority) {
        this.location = location;
        this.priority = priority;
    }

    public int getLocation() {
        return location;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "\nPrint{" +
                "location: " + location +
                ", priority :" + priority +
                "}";
    }
}
