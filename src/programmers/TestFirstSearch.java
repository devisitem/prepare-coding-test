package programmers;

import util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TestFirstSearch {


    /**
     * <b>문제 - 타겟넘버</b>
     * <hr/>
     * <div class="markdown solarized-dark"><p>n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.</p>
     * <div class="highlight"><pre class="codehilite"><code>
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     * </code></pre></div>
     * <p>사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.</p>
     *
     * <h5>제한사항</h5>
     *
     * <ul>
     * <li>주어지는 숫자의 개수는 2개 이상 20개 이하입니다.</li>
     * <li>각 숫자는 1 이상 50 이하인 자연수입니다.</li>
     * <li>타겟 넘버는 1 이상 1000 이하인 자연수입니다.</li>
     * </ul>
     *
     * <h5>입출력 예</h5>
     * <table class="table">
     *         <thead><tr>
     * <th>numbers</th>
     * <th>target</th>
     * <th>return</th>
     * </tr>
     * </thead>
     *         <tbody><tr>
     * <td>[1, 1, 1, 1, 1]</td>
     * <td>3</td>
     * <td>5</td>
     * </tr>
     * <tr>
     * <td>[4, 1, 2, 1]</td>
     * <td>4</td>
     * <td>2</td>
     * </tr>
     * </tbody>
     *       </table>
     * <h5>입출력 예 설명</h5>
     *
     * <p><strong>입출력 예 #1</strong></p>
     *
     * <p>문제 예시와 같습니다.</p>
     *
     * <p><strong>입출력 예 #2</strong></p>
     * <div class="highlight">
     *     <pre class="codehilite">
     *     <code>
     *     +4+1-2+1 = 4
     *         +4-1+2-1 = 4
     *         </code>
     *     </pre>
     * </div>
     * <ul>
     * <li>총 2가지 방법이 있으므로, 2를 return 합니다.</li>
     * </ul>
     * </div>
     * @throws Exception
     */
    public static void targetNumber(int [] numbers, int target) throws Exception {

        int [][] resultArray = new int [(int)(Math.pow(2, numbers.length + 1) -1)][numbers.length];
        resultArray[0] = numbers;

        final Stack<Node> stack = new Stack<>();
        stack.push(new Node(0, -1));
        Map<String, Integer> targetMap = new HashMap<>();

        while( ! stack.isEmpty()) {

            final Node parent = stack.pop();

            // first child of parent
            int fc = parent.firstChildNode();
            int[] parentArray = new int[numbers.length];
            System.arraycopy(resultArray[parent.getIndex()], 0, parentArray, 0 , resultArray[parent.getIndex()].length);

            int childDepth = parent.getDepth() + 1;
            if((childDepth == numbers.length)) {
                stack.pop();
            } else {
                // first child node
                System.arraycopy(parentArray, 0, resultArray[fc], 0, parentArray.length);
                stack.push(new Node(fc, childDepth));
                int firstSum = Arrays.stream(resultArray[fc]).sum();
                if(firstSum == target) {
                    targetMap.put(Arrays.toString(resultArray[fc]), fc);
                }

                // second child node
                parentArray[childDepth] *= -1;
                System.arraycopy(parentArray, 0, resultArray[fc + 1], 0, parentArray.length);
                stack.push(new Node(fc + 1, childDepth));
                int secondSum = Arrays.stream(resultArray[fc + 1]).sum();
                if (secondSum == target) {
                    targetMap.put(Arrays.toString(resultArray[fc + 1]), fc + 1);
                }
            }

        }

        targetMap.entrySet().stream().forEach(e -> {
            System.out.printf("(%3d) %s \n", e.getValue(), e.getKey());
        });
    }

}

class Node {
    private int index;
    private int depth;

    Node (int index,int depth) {
        this.index = index;
        this.depth = depth;
    }

    public int getIndex() {
        return index;
    }

    public int firstChildNode() {
        return ((this.index + 1) * 2) -1;
    }

    public int getDepth() {
        return depth;
    }
}
