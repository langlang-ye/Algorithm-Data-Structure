package hw;

import java.util.Scanner;

/*
求int型正整数在内存中存储时1的个数

输入：
5
输出：
2
输入：
0
输出：
0

 */
public class HJ15 {

    public static void main(String[] args) {

        test01();

        test02();

        test03();

    }

    private static void test01() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        String str = Integer.toBinaryString(num);
        String result = str.replaceAll("0", "");
        System.out.println(result.length());

    }

    private static void test02() {

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        int n = 0;
        for (; num > 0; ) { // n = 0 就没有1了
            if ((num & 1) == 1) // 判断末位数字 是不是1
                n++;
            num = num >>> 1;    // 无符号右移
        }
        System.out.println(n);
    }

    private static void test03() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        String str = Integer.toBinaryString(num);
        //用于返回1的数量
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '1') {
                count++;
            }
        }
        System.out.println(count);

    }

    private static void test04() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        int count = 0;
        while (num > 0) {
            if (1 == num % 2) count++; // 一个数对2取余为1, 二进制表达式最后一位是1
            num = num >>> 1;
        }
        System.out.println(count);

    }


    private static void test05() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        int count = 0;
        for (int i = 0; i < 32; i++) {
            //判断当前位是否为1
            if ((num & (1 << i)) != 0) {  // 对数字1 移位操作
                count++;//如果为1就加1
            }
        }
        System.out.println(count);

    }

    /*
    借助公式n&(n-1),该公式结果刚好是把n的二进制位中的最低位1变成0之后的结果
    5 & (5-1) = 4,5 = (101)2 , 4 = (100)2，从结果可以看出4就是把5的二进制位中的最低位1变成0之后的结果。
    4 & (4-1) = 0,4 = (100)2,0 = (000)2,此时n=0，可以结束。
    计数 count = 2，所以5的二进制中有两个1.
     */
    private static void test06() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        int count = 0;
        while(num >0){  // num 等于0 循环结束
            count++;
            num  = num &(num -1);
        }
        System.out.println(count);

    }

}
