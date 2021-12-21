package com.lang.stu.linearlist;

/**
 * 问题描述：编号为 1-N 的 N 个士兵围坐在一起形成一个圆圈，从编号为 1 的士兵开始依次报数（1，2，3...这样依次报），数到 m 的 士兵会被杀死出列，之后的士兵再从 1 开始报数。直到最后剩下一士兵，求这个士兵的编号。
 */
public class Josephus {

    private LList<String> list; //存储约瑟夫环中多个对象

    // 创建约瑟夫环并求解
    public Josephus(int number, int start, int distance) { //参数指定环长度、起始位置、计数
        this.list = new SeqList<String>(number);  //顺序表元素类型是字符串，指定表容量

        for (int i = 0; i < number; i++)
            this.list.add(0, new String((char) ('A' + i) + "")); //添加字符串对象
        System.out.print("约瑟夫环(" + number + "," + start + "," + distance + ")，");
        System.out.println(this.list.toString());  //显示顺序表所有对象的字符串表示

        int index = start - 1; // 计数起始位置
        while (this.list.length() > 1) { //多于一个对象时循环
            index = (index + distance - 1) % this.list.length();
            System.out.print("删除" + this.list.remove(index).toString() + "，");  //删除指定位置对象
            System.out.println(this.list.toString());
        }
        System.out.println("被赦免者是" + list.get(0).toString());

    }


    public static void main(String[] args) {
        new Josephus(5,1,2);
    }
}


