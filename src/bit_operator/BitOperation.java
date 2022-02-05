package bit_operator;

public class BitOperation {


    public static void dropTheBit() {

        int [] array1 = {0, 12, 4, 9, 5};

        System.out.println("비트 카운트는 말그대로 2진수로 표현했을때 차지하는 비트의 참값 카운트를 의미합니다.");
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
            System.out.printf("Integer.toBinaryString(%2d) : %8s\n", array1[i],Integer.toBinaryString(array1[i]));
        }
        System.out.println();
    }
    public static void andOperation() {
        int [] array = {8, 9, 11};

        for(int i = 0;i < array.length;i++) {
            System.out.printf("(%d) 2진수 : %s\n", array[i], Integer.toBinaryString(array[i]));
        }

        System.out.printf("(0 & 1)  10진수 : %d, 2진수 %s\n", (array[0] & array[1]), Integer.toBinaryString(array[0] & array[1]));
        System.out.printf("(1 & 2)  10진수 : %d, 2진수 %s\n", (array[1] & array[2]), Integer.toBinaryString(array[1] & array[2]));

    }

    public static void orOperation() {

        int [] array = {1, 3, 4, 6, 15, 25};

        for(int i = 0;i < array.length;i++) {
            System.out.printf("(%d) 2진수 : %s\n", array[i], Integer.toBinaryString(array[i]));
        }

        for(int i = 0;i < array.length -1;i++) {
            System.out.printf("%s | %s = %s\n",Integer.toBinaryString(array[i]), Integer.toBinaryString(array[i + 1]), Integer.toBinaryString(array[i] | array[i + 1]));
        }
    }

    public static void xOrOperation() {

        int [] array = {1, 3, 4, 6, 15, 25};

        for(int i = 0;i < array.length -1;i++) {
            System.out.printf("%s ^ %s = %s\n",toBinaryAndFillWithZero(array[i], 6), toBinaryAndFillWithZero(array[i + 1], 6), toBinaryAndFillWithZero(array[i] ^ array[i + 1], 6));
        }

    }

    public static void notOperation() {


        int [] array = {1, 3, 4, 6, 15, 25};

        for(int i = 0;i < array.length -1;i++) {
            System.out.printf("~%d : %s\n", array[i], Integer.toBinaryString(~array[i]));
            System.out.printf("-%d : %s\n", array[i], Integer.toBinaryString(-array[i]));
        }
    }

    public static void shiftOperation() {

        int [] array = {1, 2, 3, 4, 5, 6};

        for(int i = 0;i < array.length;i++) {
            System.out.printf("%s << %s = %s\n",toBinaryAndFillWithZero(array[i], 6), 2, toBinaryAndFillWithZero(array[i] << 2, 6));
        }
    }

    public static String toBinaryAndFillWithZero(int integer, int length) {
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
