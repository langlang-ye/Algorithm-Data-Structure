package com.lang.stu.ext;

/**
 *
 * 水仙花数，也称为阿姆斯特朗数，是一个三位数，其各位数字的立方和等于该数本身。例如，
 * 153是一个水仙花数，因为1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153
 *
 */
public class NarcissisticNumber {

    public static boolean isNarcissistic(int num) {
        if (num < 100 || num > 999) {
            return false; // 不是三位数，肯定不是水仙花数
        }
        int originalNum = num;
        int sum = 0;
        while (num > 0) { // 不断获取到 个位 十位 百位的数字
            int digit = num % 10;
            sum += digit * digit * digit;
            num /= 10;
        }
        return sum == originalNum;
    }


    public static void main(String[] args) {
        int num = 407; // 待判断的数
        if (isNarcissistic(num)) {
            System.out.println(num + " 是水仙花数");
        } else {
            System.out.println(num + " 不是水仙花数");
        }

    }
}
