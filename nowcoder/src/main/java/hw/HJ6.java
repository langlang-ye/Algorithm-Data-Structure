package hw;

import java.util.Scanner;

/*
 质数因子
 功能: 输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
 输入：
180
输出：
2 2 3 3 5
 */
public class HJ6 {

    public static void main(String[] args) {
//        test01();

        test04();

    }

    private static void test01() {// 大数超时
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();

            for(int i = 2; i <= a; i++) {
                while(a % i ==0) {
                    System.out.print(i + " ");
                    a /= i;
                }
            }
        }

    }

    private static void test02() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int x = (int) Math.sqrt(a);
            for (int i = 2; i <= x; i++) {
                while (a % i == 0) {
                    System.out.print(i + " ");
                    a /= i;
                }

            }

            System.out.println(a == 1 ? "" : a + "");

        }

    }

    private static void test03() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int target = in.nextInt();
            int y = 2;

            while(target !=1) {
                if (target % y == 0) {
                    System.out.print(y + " ");
                    target /= y;
                } else {
                    if (y > target / y) {
                        y = target;
                    } else {
                        y++;
                    }
                }
            }

        }

    }

    private static void test04() {
        Scanner in = new Scanner(System.in);
        int target = in.nextInt();

        //计算到奇数
        while (target % 2 == 0) {
            System.out.print(2 + " ");
            target /= 2;
        }

        int y = 3;
        while (target != 1) {
            if (target % y == 0) {
                System.out.print(y + " ");
                target /= y;
            } else {
                if (y > target / y) {
                    y = target; // 此时 target 本身是质数
                } else {
                    y += 2;
                }
            }
        }
    }
}
