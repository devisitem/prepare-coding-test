package programmers;

import java.util.*;
import java.util.stream.Collectors;

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
        int [][] jobs = {{0, 3}, {1, 9}, {2 ,6}, {20, 1}, {2, 4}, {9, 1}, {7, 4}, {7, 12}, {64, 9}, {80, 4}};

//        int answer = solutionOfDisk(jobs);
        int answer = solution(jobs);

        System.out.println("answer = " + answer);
    }

    static int solutionOfDisk(int [][] jobs) {
        int answer = 0, proceedTime = 0, totalProcessTime = 0;
        PriorityQueue<DiskJob> heap = new PriorityQueue<>();
        Queue<DiskJob> sortedJobs = new LinkedList<>(Arrays.stream(jobs).sorted((o1, o2) -> o1[0] - o2[0]).map(a -> new DiskJob(a[0], a[1])).collect(Collectors.toList()));

        while( ! sortedJobs.isEmpty() || ( ! heap.isEmpty())) {

            while (( ! sortedJobs.isEmpty()) && proceedTime >= sortedJobs.peek().req) {
                DiskJob next = sortedJobs.poll();
                heap.offer(next);
//                System.out.printf("(%2d)sec    add   → %-25s heap -> %s\n", proceedTime, next, heap);
            }

            if(( ! sortedJobs.isEmpty()) && heap.isEmpty() && proceedTime < sortedJobs.peek().req){
                System.out.println("+1 sec");
                proceedTime++;
                continue;
            }

            DiskJob poll = heap.poll();
            System.out.printf("(%2d)sec selected → %-25s heap → %s\n", proceedTime, poll, heap);
            totalProcessTime += proceedTime <= poll.req ? poll.time : (proceedTime - poll.req) + poll.time;
            proceedTime += poll.time;

        }
        System.out.println("total: " + totalProcessTime);
        answer = (int) Math.floor(totalProcessTime / jobs.length);

        return answer;
    }

    public static int solution(int [][] jobs) {
        int answer = 0;
        int end = 0; // 수행되고난 직후의 시간
        int jobsIdx = 0; // jobs 배열의 인덱스
        int count = 0; // 수행된 요청 갯수

        // 원본 배열 오름차순 정렬 (요청시간 오름차순)
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 처리 시간 오름차순으로 정렬되는 우선순위 큐(Heap)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        // 요청이 모두 수행될 때까지 반복
        while (count < jobs.length) {

            // 하나의 작업이 완료되는 시점(end)까지 들어온 모든 요청을 큐에 넣음
            while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= end) {
                pq.add(jobs[jobsIdx++]);
            }

            // 큐가 비어있다면 작업 완료(end) 이후에 다시 요청이 들어온다는 의미
            // (end를 요청의 가장 처음으로 맞춰줌)
            if (pq.isEmpty()) {
                end = jobs[jobsIdx][0];

                // 작업이 끝나기 전(end 이전) 들어온 요청 중 가장 수행시간이 짧은 요청부터 수행
            } else {

                int[] temp = pq.poll();
                answer += temp[1] + end - temp[0];
                end += temp[1];
                count++;
            }
        }

        System.out.println("total: " + answer);
        return (int) Math.floor(answer / jobs.length);
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

            int orderTime = this.time - another.time;
            if(orderTime == 0)
                return this.req - another.req;

            return orderTime;
        }

        @Override
        public String toString() {
            return String.format("{\"req\": %d, \"time\": %d}", this.req, this.time);
        }
    }
}
