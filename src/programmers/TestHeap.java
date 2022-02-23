package programmers;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverse;

public class TestHeap {

    /**
     * <p>매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다. 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.</p>
     * <pre class="codehilite"><code>섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
     * </code></pre>
     * <p>Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.<br>
     * Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.</p>
     *
     * <h5>제한 사항</h5>
     *
     * <ul>
     * <li>scoville의 길이는 2 이상 1,000,000 이하입니다.</li>
     * <li>K는 0 이상 1,000,000,000 이하입니다.</li>
     * <li>scoville의 원소는 각각 0 이상 1,000,000 이하입니다.</li>
     * <li>모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.</li>
     * </ul>
     *
     * <h5>입출력 예</h5>
     * <table class="table">
     *         <thead><tr>
     * <th>scoville</th>
     * <th>K</th>
     * <th>return</th>
     * </tr>
     * </thead>
     *         <tbody><tr>
     * <td>[1, 2, 3, 9, 10, 12]</td>
     * <td>7</td>
     * <td>2</td>
     * </tr>
     * </tbody>
     *       </table>
     * <h5>입출력 예 설명</h5>
     *
     * <ol>
     * <li><p>스코빌 지수가 1인 음식과 2인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.<br>
     * 새로운 음식의 스코빌 지수 = 1 + (2 * 2) = 5<br>
     * 가진 음식의 스코빌 지수 = [5, 3, 9, 10, 12]</p></li>
     * <li><p>스코빌 지수가 3인 음식과 5인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.<br>
     * 새로운 음식의 스코빌 지수 = 3 + (5 * 2) = 13<br>
     * 가진 음식의 스코빌 지수 = [13, 9, 10, 12]</p></li>
     * </ol>
     *
     * <p>모든 음식의 스코빌 지수가 7 이상이 되었고 이때 섞은 횟수는 2회입니다.</p>
     *
     */
    public static void moreSpicy() {
        int [] scoville = {1, 2, 4, 3, 4, 3, 9, 10 ,12};
        final int K = 7;
        int answer = solutionOfSpicy(scoville, K);

        System.out.println("answer = " + answer);

    }

    public static int solutionOfSpicy(int [] scoville, int K) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Arrays.stream(scoville)
                .mapToObj(Integer::valueOf).collect(Collectors.toList()));
        int answer = 0;
        while( ! heap.isEmpty()) {

            if(heap.size() <= 1 && heap.peek() < K) {
                return -1;
            }
            Integer first = heap.poll();

            if(first < K) {
                Integer second = heap.poll();
                Integer result = first + (second * 2);
                heap.offer(result);
                answer++;
            }else {
                break;
            }
            System.out.println("heap = " + heap+", answer = " + answer);
        }

        return answer;
    }

    public static void diskController() {
        int [][] jobs = {{0, 3}, {1, 9}, {2, 6}};

        int answer = solutionOfDisk(jobs);

        System.out.println("answer = " + answer);
    }

    static int solutionOfDisk(int [][] jobs) {
        int answer = 0;

        PriorityQueue<DiskJob> heap = new PriorityQueue<>(Arrays.stream(jobs).map(p ->
                new DiskJob(p[0], p[1])).collect(Collectors.toList()));

        int time = 0;
        int total = 0;
        while(! heap.isEmpty()) {
            DiskJob job = heap.poll();
            System.out.println("job = " + job);
            int temp = 0;
            if(total != 0) {
                temp = total - job.req + job.time;
            } else {
                temp = job.time;
            }

            time += temp;
            total += job.time;
        }
        answer = (int) Math.floor(time / (float) jobs.length);
        return answer;
    }

    static class DiskJob implements Comparable {
        int req;
        int time;

        DiskJob (int req, int time) {
            this.req = req;
            this.time = time;
        }

        @Override
        public int compareTo(Object o) {
            if( ! (o instanceof DiskJob)) {
                throw new ClassCastException();
            }
            DiskJob another = (DiskJob) o;
            if(this.req == another.req)
                return this.time - another.time;
            return this.req - another.req;
        }

        @Override
        public String toString() {
            return String.format("{\"req\": %d, \"time\": %d}", this.req, this.time);
        }
    }
}
