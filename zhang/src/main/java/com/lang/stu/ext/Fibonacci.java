package com.lang.stu.ext;

/**
 * 迭代法通常更高效，因为它的时间复杂度是O(n)，而递归法的时间复杂度是指数级的，是O(2^n)，因此不太适合计算大的斐波那契数列
 */
public class Fibonacci {

    /**
     * 递归
     */
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    }

    /**
     * 迭代法通常更高效，因为它的时间复杂度是O(n)，而递归法的时间复杂度是指数级的，是O(2^n)，因此不太适合计算大的斐波那契数列
     * @param n
     * @return
     */
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        int fib = 0;
        int prev1 = 1;
        int prev2 = 0;

        for (int i = 2; i <= n; i++) {
            fib = prev1 + prev2;
            prev2 = prev1;
            prev1 = fib;
        }
        return fib;

    }


    public static void main(String[] args) {
        int n = 11; // 要计算的斐波那契数列的项数
        for (int i = 0; i < n; i++) {
            System.out.println("斐波那契数列（递归）第 " + i + " 项为：" + fibonacciIterative(i));
        }
    }
}
