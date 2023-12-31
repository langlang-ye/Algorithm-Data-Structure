package num100;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class Num27 {

    public static void main(String[] args) {

        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;

//        int length = removeElementViolence(nums, val);

        int len = removeElement01(nums, val);


        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }

    /**
     * 暴力的解法就是两层for循环，一个for 循环遍历数组元素 ，第二个for 循环更新数组。
     * 时间复杂度是 O(n^2)  空间复杂度：O(1)
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElementViolence(int[] nums, int val) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val) { // 发现需要移除的元素，就将数组集体向前移动一位
                for (int j = i + 1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                i--; // 因为下标i以后的数值都向前移动了一位，所以i也向前移动一位
                size--; // 此时数组的大小-1
            }
        }
        return size;
    }


    /**
     * 先确保最后一个元素是有效的, 然后从末尾找到一个需要覆盖的下标, 直接覆盖,可能中间跳过一些元素, 改变了数组的相对顺序
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;
        while (nums[len - 1] == val) { // 数组最后一个元素是 val
            if (len > 1) {
                len--;
            } else {
                return 0;
            }
        }

        for (int f = len - 1; f >= 1; f--) {// len-1  最后一个元素肯定不是 val
            if (nums[f - 1] == val) { // 使用最后一个有效数组元素往前覆盖 val
                nums[f - 1] = nums[len - 1]; // 直接覆盖, 可能中间跳过一些元素, 改变了数组的相对顺序
                len--;
            }
        }
        return len;
    }

    /**
     * 快慢指针法:
     * 快指针：寻找新数组的元素 ，新数组就是不含有目标元素的数组
     * 慢指针：指向更新 新数组下标的位置
     * slow 循环中++, 准备下一个有效元素的下标, 同时也是数组的长度
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement01(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];    // 可以合并 nums[slow++] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    /**
     * 相向双指针方法，基于元素顺序可以改变的题目描述改变了元素相对位置，确保了移动最少元素
     * 将右边不等于val的元素 覆盖左边等于val的元素
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int removeElement02(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] != val) { // 找左边等于val的元素
                left++; // ++left;
            }

            while (left <= right && nums[right] == val) {   // 找右边不等于val的元素
                right--; // --right;
            }
            // 将右边不等于val的元素 覆盖左边等于val的元素
            if (left < right) {
                nums[left++] = nums[right--];
            }
        }
        return left;   // left一定指向了最终数组末尾的下一个元素
    }

    /**
     * 相向双指针法
     * 先确保最后一个元素是有效的, 然后从左边找到一个需要覆盖的下标, 并进行替换
     * 注意 有可能由于最后一个元素替换走了, 又是从左边开始替换的, 循环里必须再次判断 确保最后一个元素是有效的
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement03(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (right >= 0 && nums[right] == val) right--; //将 right 移到从右数第一个值不为val的位置

        while (left <= right) {
            if (nums[left] == val) { //  left 位置的元素需要移除
                //将 right 位置的元素移到 left（覆盖），right 位置移除
                nums[left] = nums[right];
                right--;
            }
            left++;
            while (right >= 0 && nums[right] == val) right--;  // 由于最后一个元素替换走了, 又是从左边开始替换的, 循环里必须再次判断 确保最后一个元素是有效的
        }
        return left;
    }


    /**
     * 相向双指针法:
     * 思路: 从左边找到一个val 的下标left 直接与 right 进行替换, 不断的把 nums[left] == val left 下标与right 交换, right-- 来删除val
     * 如果 right=val, 交换后换到 left 位置, 再次进行判断, 下次交换 就是 [left] 与 [right-1]
     * 有可能出现末尾连续的 val 值, 不断的同一个 left 来回交换.
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement04(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {  // 通过循环不管 right 的值, 不断的把 nums[left] == val left 下标与right 交换, right-- 来删除val
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;    // 删除了 left 下标的元素  这里兼容了right指针指向的值与val相等的情况
            } else {
                left++; // 不等于val, 有效数组长度++
            }
        }
        return left;
    }
}
