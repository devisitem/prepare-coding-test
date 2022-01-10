package dp;

import java.util.Scanner;

public class ChargeWithDP {

    private static int[] change;

    public static void start() {
        Scanner scan = new Scanner(System.in);

        change = new int[100];

        System.out.println("거스름돈을 입력해 주세요.");
        int i = scan.nextInt();

        calChange();
        System.out.println("지불해야하는 거스름동 개수 :" + change[i]);
    }

    public static void calChange() {
        change[0] = 0;

        for(int i = 1;i < 100;i++) {
            change[i] = min_change(i) + 1;
        }
    }

    public static int min_change(int i) {
        int min = change[i - 1];
        if(i >= 3){
            if(min > change[i - 3])
                min = change[i -3];
        }
        if(i >= 4) {
            if(min > change[i - 4])
                min = change[i - 4];
        }
        return min;
    }
}
