package com.lang.stu.ext;

/**
 * 判断质数
 */
public class Prime {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": " + isPrime2(i));
        }
        System.out.println(13 + ": " + isPrime2(13));
    }

    /**
     * 思想: 逐个测试待判断的数是否能被小于它的所有正整数整除, sqrt(num) 求取平方根sqrt, 在 [3-sqrt] 范围内的奇数作为除数, 进行判断
     * 类似方法: {@link com.lang.stu.linearlist.PrimeRing#isPrime(int)}
     */
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断 num 内的所有的质数
     * 埃拉托斯特尼筛法: 从 2-N枚举每个数，如果当前数未被剔除，则其为素数，并将它的倍数全部剔除掉。循环结束之后，所有没被这种操作剔除的数都是素数。
     * i从2开始, 刷掉 2*2 4+2 6+2...
     * 3, 刷掉 3*3, 9+3, 12+3...
     * 以此类推
     *
     * @param num
     * @return
     */
    public static boolean isPrime2(int num) {
        if (num <= 1) {
            return false;
        }
        boolean[] isComposite = new boolean[num + 1];
        for (int i = 2; i * i <= num; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= num; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        return !isComposite[num];
    }

}
