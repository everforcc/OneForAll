/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-12 18:10
 * Copyright
 */

package cn.cc.dawn.utils;

import cn.cc.dawn.utils.commons.lang.RRandomUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Slf4j
public class RandomUtils {

    /**
     * 数组
     */
    public static final String[] stringAry = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 数组长度
     */
    public static final int arySize = stringAry.length;
    //public static final int arySize = 2;

    // 测试字符
    public static char a = 'a';
    public static char A = 'A';
    public static char char_a = 97;
    public static char char_z = 122;
    public static char char_A = 65;
    public static char char_Z = 90;
    public static char char_91 = 91;

    private static DecimalFormat decimalFormat = new DecimalFormat("00000000000000000000");

    /**
     * 测试字符串
     */
    public static void charTests() {
        System.out.println(char_a);
        System.out.println(char_z);
        System.out.println(char_A);
        System.out.println(char_Z);
        System.out.println(char_91);
        System.out.println(a + 1);
        System.out.println(A);
        a++;
        A++;
        System.out.println(a);
        System.out.println(A);
    }

    /**
     * 生成字符串数组
     */
    public static void charAry() {
        StringBuilder stringBuilder = new StringBuilder("{");
        // 0 - 9
        for (int i = 0; i <= 9; i++) {
            stringBuilder.append("\"").append(i).append("\",");
        }
        // a - z
        for (int i = 97; i <= 122; i++) {
            stringBuilder.append("\"").append((char) i).append("\",");
        }
        // A - Z
        for (int i = 65; i <= 90; i++) {
            stringBuilder.append("\"").append((char) i).append("\",");
        }
        String str = stringBuilder.toString();
        str = str.substring(0, str.length() - 1) + "}";
        System.out.println(str);
    }

    /**
     * 生成 一个 随机字符
     */
    public static String randomStr() {
        int random = RRandomUtils.randomInt(0, arySize - 1);
        String randomStr = stringAry[random];
        log.info("randomStr: {}", randomStr);
        return randomStr;
    }

    /**
     * 根据长度生成随机字符串
     *
     * @param length 长度
     * @return 字符串
     */
    public static String randomString(int length) {
        if (length < 0) {
            throw new RuntimeException("长度必须大于0");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(randomStr());
        }
        return stringBuilder.toString();
    }

    /**
     * 一共有多少可能
     */
    public static BigDecimal repeatPossibility() {
        // 每一位有这么多可能性
        BigDecimal size = new BigDecimal(arySize);
        BigDecimal countPossibility = size.pow(6);
        System.out.println("一共有 " + countPossibility + "可能性: ");
        return countPossibility;
    }

    /**
     * 计算多少次不重复的概率
     *
     * @param already 多少次
     */
    public static void nextRepeat(int already) {
        if (already < 2) {
            throw new RuntimeException("长度必须大于1");
        }
        // 一共多少可能性
        BigDecimal totalSizeB = repeatPossibility();
        BigDecimal resultB = new BigDecimal(1);

        System.out.println("一共操作 " + already + " 次");
        //BigDecimal alreadyB = new BigDecimal(already);
        for (int i = 2; i <= already; i++) {
            BigDecimal tempRateB = calCurrentPossibility(totalSizeB, i);
            resultB = resultB.multiply(tempRateB);
        }
        System.out.println("总总总总体概率为: " + resultB);
    }

    public static void criticalValue() {
        // 计算多少次,会小于临界值
        BigDecimal criticalValueB = new BigDecimal(0.99);
        // 一共多少可能性
        BigDecimal totalSizeB = repeatPossibility();
        // 当前比率为1
        BigDecimal resultB = new BigDecimal(1);
        System.out.println(criticalValueB.compareTo(resultB) < 0);
        int i = 2;
        while (criticalValueB.compareTo(resultB) < 0) {
            System.out.print("当前次数: " + i);
            BigDecimal tempRateB = calCurrentPossibility(totalSizeB, i);
            // 设置 四舍五入 20位小数
            resultB = resultB.multiply(tempRateB).setScale(20, RoundingMode.HALF_UP);

            System.out.println(" 当前比率为: " + resultB);
            i++;
        }
    }

    /**
     * p(n) = (c - n) / c
     * 假如之前都没重复，当前这次也没重复的概率
     */
    public static BigDecimal calCurrentPossibility(BigDecimal totalSizeB, int i) {
        // 临时比率
        BigDecimal tempRateB = new BigDecimal(0);
        // 第一次就是全部,第二次就是 去掉一个就是在剩下的里面选一个
        BigDecimal alreadyB = new BigDecimal(i - 1);

        // 1. c - n
        tempRateB = totalSizeB.subtract(alreadyB);
        // 2. /c
        tempRateB = tempRateB.divide(totalSizeB, 20, RoundingMode.HALF_UP);
        // 3. 计算当前比率
        System.out.println("当前不重复概率为: " + tempRateB);
        return tempRateB;
    }


    public static void main(String[] args) {
        System.out.println("---");
        // charAry();
        // System.out.println(randomString(6));
        // repeatPossibility();
        //nextRepeat(4);
        criticalValue();
    }

}
