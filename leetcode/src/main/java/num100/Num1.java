package num100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组和一个目标和，从数组中找两个数字相加等于目标和，输出这两个数字的下标。
 */
public class Num1 {

    public static void main(String[] args) {

        int target = 18;
        int[] array = new int[]{2, 7, 11, 15};

//        int[] ints = first(array, target);
//        int[] ints = second(array, target);
        int[] ints = twoSum(array, target);
        System.out.println(ints[0] + "  " + ints[1]);
    }

    /*
    First method: 两重循环，遍历所有情况看相加是否等于目标和，如果符合直接输出。
    时间复杂度: 两层 for 循环，O（n²）
    空间复杂度: O（1）
     */
    public static int[] first(int[] array, int target) {
        int[] result = new int[2];
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /*
     Second Method:
    第二层 for 循环无非是遍历所有的元素，看哪个元素等于 sub ，时间复杂度为 O（n）。
    有没有一种方法，不用遍历就可以找到元素里有没有等于 sub 的？
    hash table ！！！
    我们可以把数组的每个元素保存为 hash 的 key，下标保存为 hash 的 value 。
    这样只需判断 sub 在不在 hash 的 key 里就可以了，而此时的时间复杂度仅为 O（1）！
    需要注意的地方是，还需判断找到的元素不是当前元素，因为题目里讲一个元素只能用一次。
    时间复杂度：比解法一少了一个 for 循环，降为 O（n）
    空间复杂度：所谓的空间换时间，这里就能体现出来， 开辟了一个 hash table ，空间复杂度变为 O（n）
      */
    public static int[] second(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], i);
        }
        for (int i = 0; i < array.length; i++) {
            int sub = target - array[i];
            //if (map.get(sub) != null && map.get(sub) != i) {
            if (map.containsKey(sub) && map.get(sub) != i) {
                return new int[]{i, map.get(sub)};
            }
        }
        return null;
    }

    /*
    Thrid method:
    看解法二中，两个 for 循环，他们长的一样，我们当然可以把它合起来。复杂度上不会带来什么变化，
    变化仅仅是不需要判断是不是当前元素了，因为当前元素还没有添加进 hash 里。
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            //if (map.get(sub) != null && map.get(sub) != i) {
            if (map.containsKey(sub)) {
                return new int[]{i, map.get(sub)}; // 数组下标 小的在后面, 大的在前面
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 官方解法: 最后输出的顺序是 正序的
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum01(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
