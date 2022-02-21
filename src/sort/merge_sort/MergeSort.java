package sort.merge_sort;

import java.util.Arrays;

public class MergeSort {

    public static void startMergeSort() {
        int [] array = {1, 9, 8, 5, 4 ,7, 6, 2, 3};
        int [] sorted = new int [array.length];
        System.out.println("array : " + Arrays.toString(array));
        mergeSort(array, sorted, 0, array.length -1);
        System.out.println("array : " + Arrays.toString(array));
    }

    public static void mergeSort(int [] array, int [] sorted, int start, int end) {
        System.out.println("array = " + Arrays.toString(array));
        if (start < end) {
            int pivot = (start + end) / 2;

            mergeSort(array, sorted, start, pivot);
            mergeSort(array, sorted, pivot + 1, end);

            int p = start;
            int q = pivot + 1;
            int idx = p;

            while (p <= pivot || q <= end) {
                if(q > end || (p <= pivot && array[p] < array[q])) {
                    sorted[idx++] = array[p++];
                } else {
                    sorted[idx++] = array[q++];
                }
            }

            for(int i = start;i <= end;i++) {
                array[i] = sorted[i];
            }
        }
    }
}
