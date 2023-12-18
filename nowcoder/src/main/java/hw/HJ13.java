package hw;

import java.util.Scanner;

/*
将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
数据范围：输入的字符串长度满足 1≤n≤1000

注意本题有多组输入

输入：
I am a boy
输出：
boy a am I

输入：
nowcoder
输出：
nowcoder
 */
public class HJ13 {

    public static void main(String[] args) {
        test01();

    }

    private static void test01() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] split = str.split(" ");

        for (int i = split.length - 1; i >= 0; i--) {
            System.out.print(split[i] + " ");
        }

    }

}
