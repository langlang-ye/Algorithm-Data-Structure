package hw;

import java.util.Scanner;
/*
字符串分隔
输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
输入：
abc
输出：
abc00000
 */
public class HJ4 {

    public static void main(String[] args) {

        test01();

        test02();
    }

    private static void test02() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        if(str.length() % 8 != 0) {
            str+="0000000"; //最多补7个0
        }

        while(str.length() >= 8) {
            System.out.println(str.substring(0, 8));
            str = str.substring(8);
        }


    }

    private static void test01() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        int len = str.length();
        if (len==0){
            System.out.println();
        }

        int tmp = len % 8;
        if(tmp!=0) {
            String add = "0000000";
            str += add.substring(0, 8-tmp);
        }

        for (int i = 0; i < len; ) {
            System.out.println(str.substring(i, i+8));
            i+=8;
        }


    }

}
