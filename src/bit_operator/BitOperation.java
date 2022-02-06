package bit_operator;

import util.Util;

public class BitOperation {


    public static void dropTheBit() {

        Util.explainProxy("비트 카운트는 말그대로 2진수로 표현했을때 차지하는 비트의 참값 카운트를 의미합니다.", () -> {

            int [] array1 = {0, 12, 4, 9, 5};

            System.out.println();
            for (int i = 0;i < array1.length;i++) {
                System.out.printf("Integer.bitCount(%d) : %d\n", array1[i],Integer.bitCount(array1[i]));
            }
            System.out.println();


            System.out.println("highest 또는 rowest는 해당 Byte를 이루는 Bit들중 가장 큰 값 또는 가장 작은 값을. 가져옵니다.");
            for (int i = 0;i < array1.length;i++) {
                System.out.printf("Integer.highestOneBit(%d) : %d , Integer.lowestOneBit(%d) : %d\n", array1[i],Integer.highestOneBit(array1[i]), array1[i],Integer.lowestOneBit(array1[i]));
            }
            System.out.println();

            for (int i = 0;i < array1.length;i++) {
                System.out.printf("Integer.toBinaryString(%2d) : %8s\n", array1[i], fillWithZero(array1[i], 6));
            }
            System.out.println();
        });
    }
    public static void andOperation() {
        Util.explainProxy("AND 연산자는 서로 참값인 경우 참을 반환합니다.", () -> {

            int [] array = {8, 9, 11, 12, 4, 6, 4};

            for(int i = 0;i < array.length;i++) {
                System.out.printf("(%d) 2진수 : %s\n", array[i], Integer.toBinaryString(array[i]));
            }

            for(int i = 0;i < array.length -1;i++) {
                System.out.printf("%s & %s = %s\n",fillWithZero(array[i], 6), fillWithZero(array[i + 1], 6), fillWithZero(array[i] & array[i + 1], 6));
            }
            System.out.println();
        });

    }

    public static void orOperation() {
        Util.explainProxy("OR 연산자는 한쪽만 참이라도 참값을 반환합니다.", () -> {

            int [] array = {1, 3, 4, 6, 15, 25};

            for(int i = 0;i < array.length;i++) {
                System.out.printf("(%d) 2진수 : %s\n", array[i], Integer.toBinaryString(array[i]));
            }

            for(int i = 0;i < array.length -1;i++) {
                System.out.printf("%s | %s = %s\n",fillWithZero(array[i], 6), fillWithZero(array[i + 1], 6), fillWithZero(array[i] | array[i + 1], 6));
            }
            System.out.println();
        });
    }

    public static void xOrOperation() {
        Util.explainProxy("XOR 연산자는 양쪽 모두 다를경우 참값을 반환합니다.", () -> {

            int [] array = {1, 3, 4, 6, 15, 25};

            for(int i = 0;i < array.length -1;i++) {
                System.out.printf("%s ^ %s = %s\n",fillWithZero(array[i], 6), fillWithZero(array[i + 1], 6), fillWithZero(array[i] ^ array[i + 1], 6));
            }
            System.out.println();
        });

    }

    public static void notOperation() {

        Util.explainProxy("NOT 연산자는 현재비트들을 반대로 바꿉니다.", () -> {

            int [] array = {1, 3, 4, 6, 15, 25};

            for(int i = 0;i < array.length -1;i++) {
                System.out.printf("~%d : %s\n", array[i], Integer.toBinaryString(~array[i]));
                System.out.printf("-%d : %s\n", array[i], Integer.toBinaryString(-array[i]));
            }
            System.out.println();
        });
    }

    public static void shiftOperation() {
        Util.explainProxy("Shift 연산은 좌항의 비트자릿수를 우항의 수만큼 이동시킵니다.\n따라서 다음과 같은 식이 성립됩니다. 2 << 2 : 2 * 2^2, 2 >> 3 : 2 / 2^3", () -> {

            int [] array = {1, 2, 3, 4, 5, 6};

            for(int i = 0;i < array.length;i++) {
                System.out.printf("%s << %s = %s\n",fillWithZero(array[i], 6), 2, fillWithZero(array[i] << 2, 6));
            }
            System.out.println();
        });
    }

    public static String fillWithZero(int integer, int length) {
        String target = Integer.toBinaryString(integer);
        if(target.length() >= length)
            return target;
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < (length - target.length());i++){
            builder.append("0");
        }
        return builder.append(target).toString();
    }
}
