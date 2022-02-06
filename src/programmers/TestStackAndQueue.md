 ## 프로그래머스 스택/큐
 
### 기능개발

[문제링크](https://programmers.co.kr/learn/courses/30/lessons/42586)

| #| progresses | speeds | return |
| --- | --- | ---- | --- |
| case 1| [93, 30, 55] | [1, 30, 5] | [2, 1] |
| case 1| [95, 90, 99, 99, 80, 99]	 | [1, 1, 1, 1, 1, 1] | [1, 3, 2] |

문제는 간단하다, progresses[i]%의 진행률을 가지는 작업이 있는데 하루에 speeds[i] 만큼 작업이 가능하다.
따라서, 각 i 번째 기능의 진행완료일은 `k = ⎡(남은 퍼센트 / 일일 진행속도)⎤`일이 된다. 이 내용을 아래와 같이 작성한다.  


```java
public static void developForFeature() throws Exception {
        int [] progresses = {93, 30, 55};
        int [] speeds = {1, 30, 5};
        int [] answer = {};

        Queue<Integer> que = new LinkedList<>();
        ArrayList<Integer> dayList = new ArrayList<>();
        for(int i = 0;i < progresses.length;i++) {
            int days = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);
            //i번째 작업의 완료일이 현재 큐의 첫번째 값(이전 진행일 목록중 가장 긴 일수) 보다,
            // 크다면 현재까지의 기능개수(que size)를 넣어준다.  
            if( ! que.isEmpty() && que.peek() < days) {
                dayList.add(que.size());
                que.clear();
            }

            que.offer(days);
        }
        //현재까지의 기능 개수를 넣어주고 다음 기능의 작업 완료일수를 넣기 때문에
        //마지막 루프에선 if문을 탈수 없으므로, 리스트에 한번 더 넣어준다.
        dayList.add(que.size());

        System.out.println("dayList = " + dayList);
    }
```  
  
간단하게 queue 제일 앞 값이
