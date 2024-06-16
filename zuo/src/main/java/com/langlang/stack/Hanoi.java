package com.langlang.stack;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 汉诺塔问题比较经典，这里修改一下游戏规则：现在限制不能从最左侧的塔直接移动到最
 * 右侧，也不能从最右侧直接移动到最左侧，而是必须经过中间。求当塔有 N 层的时候，打印最
 * 优移动过程和最优移动总步数。
 */
public class Hanoi {

    public static void main(String[] args) {
        // hanoi();
        testFn();
    }

    private static void testFn() {
        Stack<Integer> left = new Stack<Integer>();
        Stack<Integer> mid = new Stack<Integer>();
        Stack<Integer> right = new Stack<Integer>();

        left.push(5);
        left.push(4);
        left.push(3);
        left.push(2);
        left.push(1);
        AtomicInteger count = new AtomicInteger(0);
        fn(left.size(), left, mid, right, count);

    }

    /**
     * 方法: 利用三个栈递归实现汉诺塔问题
     * 递归通项式: fn(n) = fn(n-1) + leftToMid(left, mid)  + fnn (n-1) + midToRight(mid, right) + fn(n-1);  n>1
     * fnn(n) = fnn(n-1) + rightToMid(right, mid) + fn(n-1) + midToLeft(mid, left) + fnn(n-1);  n>1
     * n=1, fn(n)  left->mid->right
     * n=1, fnn(n)  right->mid->left
     * count 记录移动的总步数
     *
     * @param n
     * @param left
     * @param mid
     * @param right
     * @param count
     */
    public static void fn(int n, Stack<Integer> left, Stack<Integer> mid, Stack<Integer> right, AtomicInteger count) {
        if (n == 1) {
            // System.out.println("left ->mid -> right");
            leftToMid(left, mid, count);
            midToRight(mid, right, count);
        } else {
            // fn(n-1) + leftToMid(left, mid, count) + fnn (n-1) + (mid-right) + fn(n-1);
            fn(n - 1, left, mid, right, count);
            leftToMid(left, mid, count);
            fnn(n - 1, left, mid, right, count);
            midToRight(mid, right, count);
            fn(n - 1, left, mid, right, count);
        }
    }

    public static void fnn(int n, Stack<Integer> left, Stack<Integer> mid, Stack<Integer> right, AtomicInteger count) {
        if (n == 1) {
            // System.out.println("right ->mid -> left");
            rightToMid(right, mid, count);
            midToLeft(mid, left, count);
        } else {
            // fnn(n-1) + (mid->right) + fn(n-1) + (right->left) + fnn(n-1);
            fnn(n - 1, left, mid, right, count);
            rightToMid(right, mid, count);
            fn(n - 1, left, mid, right, count);
            midToLeft(mid, left, count);
            fnn(n - 1, left, mid, right, count);

        }
    }

    public static void hanoi() {
        Stack<Integer> left = new Stack<Integer>();
        Stack<Integer> mid = new Stack<Integer>();
        Stack<Integer> right = new Stack<Integer>();

        // left.push(3);
        left.push(2);
        left.push(1);
        AtomicInteger count = new AtomicInteger(0);
        while (!left.isEmpty() || !mid.isEmpty()) {
            leftToMid(left, mid, count);
            midToRight(mid, right, count);

            leftToMid(left, mid, count);
            midToRight(mid, right, count);

            rightToMid(right, mid, count);
            midToLeft(mid, left, count);

            midToRight(mid, right, count);
        }
        System.out.println("总数: " + count);


    }


    private static void leftToMid(Stack<Integer> left, Stack<Integer> mid, AtomicInteger count) {
        if (!left.isEmpty()) {
            if (mid.isEmpty() || mid.peek() > left.peek()) {
                Integer pop = left.pop();
                mid.push(pop);
                count.addAndGet(1);
                System.out.println("move " + pop + " from left to mid " + count);
            }
        }

    }

    private static void midToRight(Stack<Integer> mid, Stack<Integer> right, AtomicInteger count) {
        if (!mid.isEmpty()) {
            if (right.isEmpty() || right.peek() > mid.peek()) {
                Integer pop = mid.pop();
                right.push(pop);
                count.addAndGet(1);
                System.out.println("move " + pop + " from mid to right " + count);
            }

        }

    }

    private static void rightToMid(Stack<Integer> right, Stack<Integer> mid, AtomicInteger count) {
        if (!right.isEmpty()) {
            if (mid.isEmpty() || mid.peek() > right.peek()) {
                Integer pop = right.pop();
                mid.push(pop);
                count.addAndGet(1);
                System.out.println("move " + pop + " from right to mid " + count);
            }
        }
    }

    private static void midToLeft(Stack<Integer> mid, Stack<Integer> left, AtomicInteger count) {
        if (!mid.isEmpty()) {
            if (left.isEmpty() || left.peek() > mid.peek()) {
                Integer pop = mid.pop();
                left.push(pop);
                count.addAndGet(1);
                System.out.println("move " + pop + " from mid to left " + count);
            }
        }

    }


}