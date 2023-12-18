package hw;

import java.util.Scanner;

/*
 数字颠倒
 输入一个整数，将这个整数以字符串的形式逆序输出
程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
输入:
1516000
输出：
0006151
输入：
0
输出：
0
 */
public class HJ11 {

    public static void main(String[] args) {

//        test01();
        test02();
    }

    private static void test02() {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        StringBuilder builder = new StringBuilder("").append(a);
        builder.reverse();

        System.out.println(builder.toString());


    }

    private static void test01() {

        Scanner in = new Scanner(System.in);

        int a = in.nextInt();

        String str = String.valueOf(a);

        for(int i =  str.length() - 1; i >= 0; i--){
            System.out.print(str.charAt(i));
        }

    }
}
