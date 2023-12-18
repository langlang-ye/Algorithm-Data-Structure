package hw;


import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
字符个数统计
编写一个函数，计算字符串中含有的不同字符的个数。字符在 ASCII 码范围内( 0~127 ，包括 0 和 127 )，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
例如，对于字符串 abaca 而言，有 a、b、c 三种不同的字符，因此输出 3 。
输入：
abc
输出：
3
输入：
aaa
输出：
1
 */
public class HJ10 {

    public static void main(String[] args) {

//        test01();
        test02();
    }

    private static void test02() {
        Scanner in = new Scanner(System.in);

        String str = in.nextLine();

        BitSet bitSet = new BitSet(128);

        for(char c: str.toCharArray()) {
            if(!bitSet.get(c)) {
                bitSet.set(c);
            }
        }

        System.out.println(bitSet.cardinality());

    }

    private static void test01() {

        Scanner in = new Scanner(System.in);

        String str = in.nextLine();
        Set<Byte> set = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            set.add((byte)str.charAt(i));
        }

        System.out.println(set.size());
    }
}
