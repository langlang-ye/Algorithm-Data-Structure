package hw;

import java.util.Scanner;

/*
接受一个只包含小写字母的字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）

输入：
abcd
输出：
dcba
 */
public class HJ12 {

    public static void main(String[] args) {

//        test01();

        test02();
    }

    private static void test01() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        System.out.println(new StringBuilder(str).reverse().toString());
    }

    private static void test02() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }

    }
}
