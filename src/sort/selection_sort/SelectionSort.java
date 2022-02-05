package sort.selection_sort;

import java.util.Arrays;

/**
 * 선택 정렬
 */
public class SelectionSort {

    /**
     * i 와 j 값 변경
     * 각 인덱스로 접근하므로 시간복잡도는 상수시간을 갖는다. O(1)
     * @param array
     * @param i
     * @param j
     */
    public static void swapElements(int [] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * start 인덱스 부터 시작해서 끝까지 순회중 가장 작은 값을 리턴한다.
     * @param array
     * @param start
     * @return
     */
    public static int indexLowest(int [] array, int start) {
        int lowIndex = start;

        for(int i = start;i < array.length;i++) {
            if(array[i] < array[lowIndex]) {
                lowIndex = i;
            }
        }
        return lowIndex;
    }

    /**
     * indexLowest 메서드를 통해 얻어온 가장작은값의 인덱스를 현재 인덱스와 변경한다.
     * @param array
     */
    public static void selectionSort(int [] array) {
        System.out.printf("before selection sort : %s\n", Arrays.toString(array));
        for(int i = 0;i < array.length;i++) {
            int j = indexLowest(array, i);
            swapElements(array, i, j);

            System.out.printf("(i = %d) array : %s\n",i , Arrays.toString(array));
        }

    }
}
