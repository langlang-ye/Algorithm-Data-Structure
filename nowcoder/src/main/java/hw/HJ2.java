package hw;

import java.util.Scanner;



/*
 计算某字符出现次数
 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）

数据范围：

1≤n≤1000
输入：
ABCabc
A

输出：
2
 */
public class HJ2 {

    public static void main(String[] args) {
//        test01();

        test02();
    }

    // 网友方法,
    private static void test02() {

        Scanner sc = new Scanner(System.in);
        String str =sc.nextLine().toLowerCase();
        String s = sc.nextLine(); // s 也要转小写

        String s1 = str.replaceAll(s, "");
        System.out.println(s1);

        System.out.print(str.length()-str.replaceAll(s,"").length());

    }

    private static void test01() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String str = in.nextLine().toLowerCase();
        char target = in.next().toLowerCase().charAt(0);

        int len = str.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == target) {
                count++;
            }
        }

        System.out.println(count);
    }
}
