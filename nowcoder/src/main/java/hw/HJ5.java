package hw;

import java.util.Scanner;

/*  进制转换
写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
输入：
0xAA
输出：
170

 */
public class HJ5 {

    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();

            str = str.substring(2);
            int i = Integer.parseInt(str, 16);
            System.out.println(Integer.parseInt(str,16));



        }
    }
}
