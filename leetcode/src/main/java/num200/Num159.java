package num200;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 注: 本题没有在 leetcode 提交验证
 * 给定一个字符串 s，找出 至多 包含两个不同字符的最长子串 ，并返回该子串的长度.
 * 参考:
 * 输入: eceba
 * 输出: 3
 * 解释: t 是"ece", 长度为3
 * <p>
 * 输入: ccaabbb
 * 输出: 5
 * 解释: t 是"aabbb", 长度是5
 */
public class Num159 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int len = lengthOfLongestSubstringTwoDistinct01(str);
        System.out.println(len);
        // nooooow  nowcoder
        char[] charArray = str.toCharArray();
    }



    /**
     * map 记录一个字符和字符出现的次数, 当 map 长度大于 2 时, 通过 while 循环从左边往右边不断遍历字符,
     * 更新遍历到的特定字符在 map 中的次数, 直到这个特定字符出现的次数等于 0 后, 把它从 map 中移除, 之后计算最长子串
     *
     */
    private static int lengthOfLongestSubstringTwoDistinct(String str) {
        Map<Character, Integer> map = new HashMap<>();
        int length = str.length();
        int max = 0;

        for (int i = 0, j = 0; i < length; i++) {

            char c = str.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > 2) {
                char t = str.charAt(j++);
                map.put(t, map.get(t) - 1);

                if (map.get(t) == 0) {
                    map.remove(t);
                }
            }

            max = Math.max(max, i - j + 1);
        }

        return max;
    }

    /**
     * 快慢指针, 使用map, key 记录字符, value 记录字符的下标
     * 当map size==3, 更新 map, 删除掉保存的值最小的下标对应的字符, 更新 left 更新 max 长度
     * 对比上一版, 可以跳到需要删除字符的位置.
     */
    private static int lengthOfLongestSubstringTwoDistinct01(String str) {
        int len = str.length();
        if (len < 3) return len;

//        int left = 0;
        int left = -1; // 从负数 1 开始, right - left 不用加1

        int right = 0;

        int max = 2;

        Map<Character, Integer> map = new HashMap<>();

        while (right < len) {
            map.put(str.charAt(right), right);
            if (map.size() == 3) {
                // map 保存的值最小的下标对应的字符 即是需要删除的
                int index = Collections.min(map.values());
                map.remove(str.charAt(index));
//                left = index + 1;
                left = index;
            }
//            max = Math.max(max, right - left + 1);
            max = Math.max(max, right - left);
            right++;
        }
        return max;
    }


}
