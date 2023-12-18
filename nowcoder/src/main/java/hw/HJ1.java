package hw;

import java.util.Scanner;

/*
    字符串最后一个单词的长度
    输入：hello nowcoder

    输出：8

    说明：
    最后一个单词为 nowcoder，长度为8
 */
public class HJ1 {

    public static void main(String[] args) {
//        test01();
         test02();
    }

    private static void test01() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

//        String[] strs = str.split(" ");
        String[] strs = str.split("\\s+");
        int len =  strs[strs.length-1].length();

        System.out.print(len);
    }

    private static void test02() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        int len = str.length();
        int count = 0;

        for(int i = len-1; i>=0; i--){
            if(str.charAt(i)==' ') {
                break;
            }
            count++;
        }
        System.out.println(count);
    }

}
