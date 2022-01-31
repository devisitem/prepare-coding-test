package dp.bj;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestHash {

    /**
     * <b>문제</b>
     * <hr>
     * <p>수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.</p>
     *
     * <p>마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.</p>
     *
     * <h5>제한사항</h5>
     *
     * <ul>
     * <li>마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.</li>
     * <li>completion의 길이는 participant의 길이보다 1 작습니다.</li>
     * <li>참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.</li>
     * <li>참가자 중에는 동명이인이 있을 수 있습니다.</li>
     * </ul>
     *
     * <h5>입출력 예</h5>
     * <table class="table">
     *         <thead><tr>
     * <th>participant</th>
     * <th>completion</th>
     * <th>return</th>
     * </tr>
     * </thead>
     *         <tbody><tr>
     * <td>["leo", "kiki", "eden"]</td>
     * <td>["eden", "kiki"]</td>
     * <td>"leo"</td>
     * </tr>
     * <tr>
     * <td>["marina", "josipa", "nikola", "vinko", "filipa"]</td>
     * <td>["josipa", "filipa", "marina", "nikola"]</td>
     * <td>"vinko"</td>
     * </tr>
     * <tr>
     * <td>["mislav", "stanko", "mislav", "ana"]</td>
     * <td>["stanko", "ana", "mislav"]</td>
     * <td>"mislav"</td>
     * </tr>
     * </tbody>
     *       </table>
     * <h5>입출력 예 설명</h5>
     *
     * <p>예제 #1<br>
     * "leo"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.</p>
     *
     * <p>예제 #2<br>
     * "vinko"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.</p>
     *
     * <p>예제 #3<br>
     * "mislav"는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.</p>
     *
     * <p><a href="https://programmers.co.kr/learn/courses/30/lessons/42576" target="_blank" rel="noopener">완주하지 못한 선수</a></p>
     * @throws Exception
     */
    public static void nonFinishedPlayer() throws Exception {
        String [] participant= {"marina", "josipa", "nikola", "nikola", "vinko", "filipa"};
        String [] completion = {"josipa", "filipa", "marina", "nikola", "nikola"};

        Map<String, Long> map = Arrays.stream(participant)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for(String name : completion) {

            Long count = map.get(name) - 1L;

            if(count == 0L) {
                map.remove(name);
            } else {
                map.put(name, count);
            }
        }

        String player = map.keySet().iterator().next();

        System.out.println("player = " + player);
    }

    /**
     * <b>문제</b>
     * <hr>
     * <div class="guide-section-description">
     *       <h6 class="guide-section-title">문제 설명</h6>
     *       <div class="markdown solarized-dark"><p>전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.<br>
     * 전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.</p>
     *
     * <ul>
     * <li>구조대 : 119</li>
     * <li>박준영 : 97 674 223</li>
     * <li>지영석 : 11 9552 4421</li>
     * </ul>
     *
     * <p>전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.</p>
     *
     * <h5>제한 사항</h5>
     *
     * <ul>
     * <li>phone_book의 길이는 1 이상 1,000,000 이하입니다.
     *
     * <ul>
     * <li>각 전화번호의 길이는 1 이상 20 이하입니다.</li>
     * <li>같은 전화번호가 중복해서 들어있지 않습니다.</li>
     * </ul></li>
     * </ul>
     *
     * <h5>입출력 예제</h5>
     * <table class="table">
     *         <thead><tr>
     * <th>phone_book</th>
     * <th>return</th>
     * </tr>
     * </thead>
     *         <tbody><tr>
     * <td>["119", "97674223", "1195524421"]</td>
     * <td>false</td>
     * </tr>
     * <tr>
     * <td>["123","456","789"]</td>
     * <td>true</td>
     * </tr>
     * <tr>
     * <td>["12","123","1235","567","88"]</td>
     * <td>false</td>
     * </tr>
     * </tbody>
     *       </table>
     * <h5>입출력 예 설명</h5>
     *
     * <p>입출력 예 #1<br>
     * 앞에서 설명한 예와 같습니다.</p>
     *
     * <p>입출력 예 #2<br>
     * 한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.</p>
     *
     * <p>입출력 예 #3<br>
     * 첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.</p>
     *
     * <hr>
     *
     * <p><strong>알림</strong></p>
     *
     * <p>2021년 3월 4일, 테스트 케이스가 변경되었습니다. 이로 인해 이전에 통과하던 코드가 더 이상 통과하지 않을 수 있습니다.</p>
     *
     * <p><a href="https://programmers.co.kr/learn/courses/30/lessons/42577" target="_blank" rel="noopener">전화번호 목록</a></p>
     *
     * @throws Exception
     */
    public static void phoneNumberList() throws Exception {

    }
}
