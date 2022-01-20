package dp.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Practice01 {


    /**
     * <b>완주하지 못한 선수</b>
     * <hr>
     * <p>수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.</p>
     * <p>마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.</p>
     * <ul>
     *     <li>마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.</li>
     *     <li>completion의 길이는 participant의 길이보다 1 작습니다.</li>
     *     <li>참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.</li>
     *     <li>참가자 중에는 동명이인이 있을 수 있습니다.</li>
     * </ul>
     * <hr>
     * <table>
     *     <thead>
     *         <tr>
     *             <th>participant</th>
     *             <th>completion</th>
     *             <th>return</th>
     *         </tr>
     *     </thead>
     *     <tbody>
     *         <tr>
     *             <td>["leo", "kiki", "eden"]</td>
     *             <td>["eden", "kiki"]</td>
     *             <td>"leo"</td>
     *         </tr>
     *         <tr>
     *             <td>["marina", "josipa", "nikola", "vinko", "filipa"]</td>
     *             <td>["josipa", "filipa", "marina", "nikola"]</td>
     *             <td>"vinko"</td>
     *         </tr>
     *         <tr>
     *             <td>["mislav", "stanko", "mislav", "ana"]</td>
     *             <td>["stanko", "ana", "mislav"]</td>
     *             <td>"mislav"</td>
     *         </tr>
     *     </tbody>
     * </table>
     *
     */
    public static void test01() {
        String[] participant = {"marina", "marina", "nikola", "vinko", "filipa"};
        String[] completion = {"marina", "filipa", "marina", "nikola"};

        String answer = "";

        Map<String, Long> partiMap = Arrays.asList(participant).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("partiMap = " + partiMap);

        for(String name : completion) {
            Long value  = partiMap.get(name) - 1L;
            if(value == 0L) {
                partiMap.remove(name);
            } else {
                partiMap.put(name, value);
            }
        }
        answer = partiMap.keySet().iterator().next();
        System.out.println("answer = " + answer);
    }
}
