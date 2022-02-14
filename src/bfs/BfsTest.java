package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BfsTest {

    public static void bfsAsList() {
        // 배열의 인덱스를 노드와 매칭시켜 사용하기 위해 인덱스 0은 아무것도 저장하지 않음.
        // 1번 인덱스는 1번 노드를 뜻하고 노드의 배열의 값은 연결된 노드
        int [][] graph = {{}, {2, 3, 8}, {1, 6, 8}, {1, 5}, {5, 7}, {3, 4, 7}, {2}, {4, 5}, {1, 2}};

        boolean [] visited = new boolean[graph.length];

        String bfs = bfs(1, graph, visited);
        System.out.println("bfs = " + bfs);

    }

    static String bfs(int start, int [][] graph, boolean [] visited) {
        StringBuilder builder = new StringBuilder();

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while( ! queue.isEmpty()) {
            int nodeIndex = queue.poll();
            builder.append((nodeIndex + " -> "));

            for(int i = 0;i < graph[nodeIndex].length;i++) {
                int temp = graph[nodeIndex][i];

                if( ! visited[temp]) {
                    visited[temp] = true;
                    queue.offer(temp);
                }
            }
        }

        return builder.toString();
    }

    public static void bfsWithNumber() {
        int [] password = {3, 1, 4, 7, 9, 2, 8, 6, 5};
        int [][] array = {{},
                {2, 4, 5}, {1, 3, 4, 5, 6}, {2, 5, 6},
                {1, 2, 5, 7, 8}, {1, 2, 3, 4, 6, 7, 8, 9}, {2, 3, 5, 8, 9},
                {4, 5, 8}, {4, 5, 6, 7, 9}, {5, 6, 8}};
        boolean [] visited = new boolean[array.length];
        Queue<Integer> q = new LinkedList<>();


            q.offer(1);
            visited[1] = true;
            while( ! q.isEmpty()) {
                int nodeIndex = q.poll();
                System.out.print(nodeIndex+ " -> ");
                for(int i = 0;i < array[nodeIndex].length;i++) {
                    int temp = array[nodeIndex][i];

                    if( ! visited[temp]) {
                        visited[temp] = true;
                        q.offer(temp);
                    }
                }
            }

    }

}
