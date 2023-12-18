package hw;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


/*
合并表记录
数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。

输入:
4
0 1
0 2
1 2
3 4

输出:
0 3
1 2
3 4

 */
public class HJ8 {

    public static void main(String[] args) {

//        test01();

        test02();
    }


    private static void test02() {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Integer> map = new TreeMap<>(); // TreeMap 保证顺序
        int n = sc.nextInt();
        for(int i = 0; i < n; ++i){
            int a = sc.nextInt();
            int b = sc.nextInt();
            map.put(a,map.getOrDefault(a,0) + b);
        }

        for (Integer i : map.keySet()) {
            System.out.println(i + " " + map.get(i));
        }

    }

    private static void test01() {

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        Map<Integer, Integer> map = new HashMap<>(num);

        int count = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int index = in.nextInt();
            int value = in.nextInt();


            if (map.containsKey(index)) {
                map.put(index, value + map.get(index));
            } else {
                map.put(index, value);
            }
            count++;
            if (count >= num) {
                break;
            }
        }

        Set<Integer> set = map.keySet();
        Object[] objs =  set.stream().sorted().toArray();

        for (Object obj : objs) {
            System.out.println(obj + " " + map.get(obj));
        }
    }
}
