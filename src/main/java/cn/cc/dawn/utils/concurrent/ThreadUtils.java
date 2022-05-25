package cn.cc.dawn.utils.concurrent;

public class ThreadUtils {

    public static void sleep(int second){
        try {
            Thread.sleep(1000 * second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
