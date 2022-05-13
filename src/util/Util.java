package util;

import java.util.Arrays;

public class Util {

    public static void explainProxy(String explain, Runnable runnable) {
        System.out.println("------------------------------------");
        System.out.println(explain);
        runnable.run();
        System.out.println("------------------------------------");
    }

    public static void drawBinaryTree(int arr[][]) {
        StringBuilder builder = new StringBuilder();

        int nol = (int) (Math.log(arr.length) / Math.log(2)) + 1;
        int max = (int) Math.pow(2, nol - 1);

        int printed = 0;
        for (int i = 0; i < nol; i++) {
            int perFloor = (int) Math.pow(2, i);
            int tab = (max - perFloor) / 2 + (max - perFloor) % 2;
            int last = printed + perFloor;

            for (int j = 0; j < tab; j++) {
                builder.append("  ");
            }
            for (int j = printed; (j < arr.length && j < last); j++) {
                builder.append(String.format("(%s)", Arrays.toString(arr[j])));
            }
            builder.append("\n");
            printed += perFloor;
        }

        System.out.println(builder);
    }
}
