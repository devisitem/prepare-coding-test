package util;

public class Util {

    public static void explainProxy(String explain, Runnable runnable) {
        System.out.println("------------------------------------");
        System.out.println(explain);
        runnable.run();
        System.out.println("------------------------------------");
    }
}
