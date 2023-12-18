package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
给定 n 个字符串，请对 n 个字符串按照字典序排列。
数据范围：1≤n≤1000  ，字符串长度满足 1≤len≤100

输入：
9
cap
to
cat
card
two
too
up
boat
boot

输出：
boat
boot
cap
card
cat
to
too
two
up
 */
public class HJ14 {

    public static void main(String[] args) {

//        test01();
//        test02();
        test03();

    }

    private static void test01() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        String[] array = new String[num];
        for (int j = 0; j < num; j++) {
            array[j] = in.next();
        }

        Arrays.sort(array);

        for (String s : array) {
            System.out.println(s);
        }

    }

    private static void test02() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine(); // 输入数字后的一行 特殊处理

        String[] array = new String[num];
        for (int j = 0; j < num; j++) {
            array[j] = in.nextLine();
        }

        Arrays.sort(array);

        for (String s : array) {
            System.out.println(s);
        }

    }

    // PriorityQueue
    private static void test03() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < num; i++) {
            pq.offer(in.next());
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

    }

    private static void test04() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(in.next());
        }

        // 实现 Comparator 接口 自定义排序
        list.sort((s1, s2) -> {
            int i = 0;
            while (i < s1.length() && i < s2.length()) {
                if (s1.charAt(i) > s2.charAt(i)) {
                    return 1;
                } else if (s1.charAt(i) < s2.charAt(i)) {
                    return -1;
                } else {
                    i++;
                }
            }
            return s1.length() - s2.length();
        });

        list.forEach(System.out::println);
    }
}
