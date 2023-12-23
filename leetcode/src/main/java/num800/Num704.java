package num800;


/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 参考  com.lang.stu.search.BSArray#
 */
public class Num704 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 4;

        int index = search(nums, target);
        System.out.println(index);

        int find = binarySearch(nums, target);
        System.out.println(find);


    }

    /**
     * 递归实现
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        if (nums != null)
            return binarySearch(nums, target, 0, nums.length - 1);
        return -1;
    }

    private static int binarySearch(int[] table, int value, int low, int high) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (table[mid] == value)
                return mid;
            else if (table[mid] > value)
                return binarySearch(table, value, low, mid - 1);
            else
                return binarySearch(table, value, mid + 1, high);
        }
        return -1;
    }

    /**
     * 递归实现
     * 二分查找  start end middle 都参与了判断
     */
    private static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int middle = (start + end) / 2;
        if(nums[start] == target) return start;
        if(nums[end] == target) return end;
        if(nums[middle] == target) return middle;
        if(start + 2 >= end) {
            return -1;
        }
        if(nums[middle] > target) { // 前半段
            return search(nums, start + 1, middle - 1, target);
        } else { // 后半段
            return search(nums, middle + 1, end - 1 , target);
        }
    }

    private static int search(int[] nums, int start, int end, int target) {
        if(nums[start] == target) return start;
        if(nums[end] == target) return end;
        int middle = (start + end) / 2;
        if(nums[middle] == target) return middle;
        if(start + 2 >= end) {
            return -1;
        }
        if(nums[middle] > target) { // 前半段
            return search(nums, start + 1, middle - 1, target);
        } else { // 后半段
            return search(nums, middle + 1, end - 1 , target);
        }
    }


    /**
     * 第一种写法，我们定义 target 是在一个在左闭右闭的区间里，也就是[left, right] （这个很重要非常重要）。
     * 区间的定义这就决定了二分法的代码应该如何写，因为定义target在[left, right]区间，所以有如下两点：
     * while (left <= right) 要使用 <= ，因为left == right是有意义的，所以使用 <=
     * if (nums[middle] > target) right 要赋值为 middle - 1，因为当前这个nums[middle]一定不是target，那么
     * 接下来要查找的左区间结束下标位置就是 middle - 1
     */
    public static int search01(int[] nums, int target) {
        // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0, right = nums.length - 1;  // 定义 target 在左闭右闭的区间里，[left, right]
        while (left <= right) {  // 当left==right，区间[left, right]依然有效，所以用 <=
            int mid = left + ((right - left) >> 1);  // 防止溢出 等同于(left + right)/2  [>> 1 等同于 /2  ]
            if (nums[mid] == target)
                return mid;     // 数组中找到目标值，直接返回下标
            else if (nums[mid] < target)
                left = mid + 1;     // target 在右区间，所以[middle + 1, right]
            else if (nums[mid] > target)
                right = mid - 1; // target 在左区间，所以[left, middle - 1]
        }
        return -1; // 未找到目标值
    }

    /**
     * 如果说定义 target 是在一个在左闭右开的区间里，也就是[left, right) ，那么二分法的边界处理方式则截然不同。
     * 有如下两点：
     * while (left < right)，这里使用 < ,因为left == right在区间[left, right)是没有意义的
     * if (nums[middle] > target) right 更新为 middle，因为当前nums[middle]不等于target，去左区间继续寻找，而
     * 寻找区间是左闭右开区间，所以right更新为middle，即：下一个查询区间不会去比较nums[middle]
     */
    public int search02(int[] nums, int target) {
        // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }

        int left = 0, right = nums.length;      // 定义target在左闭右开的区间里，即：[left, right)
        while (left < right) {      // 因为 left == right的时候，在[left, right)是无效的空间，所以使用 <
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target)
                return mid; // 数组中找到目标值，直接返回下标
            else if (nums[mid] < target)
                left = mid + 1; // target 在右区间，在[middle + 1, right)中  下标可以取到mid 并且nums[mid] 不等于target
            else if (nums[mid] > target)
                right = mid; // target 在左区间，在[left, middle)中
        }
        return -1;
    }


}
