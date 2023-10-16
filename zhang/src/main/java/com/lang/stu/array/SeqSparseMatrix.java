package com.lang.stu.array;

import com.lang.stu.linearlist.SeqList; //顺序表

/**
 * 稀疏矩阵三元组顺序表类
 */
public class SeqSparseMatrix {

    private int rowCount, columnCount;  //行数、列数
    private SeqList<Element> list; // 三元组顺序表

    //指定稀疏矩阵的行数、列数、非零元素个数
    public SeqSparseMatrix(int rowCount, int columnCount, int count) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.list = new SeqList<Element>(count);
    }

    public SeqSparseMatrix() {
        this(0, 0, 10);
    }

    //指定稀疏矩阵的行数、列数、三元组序列
    public SeqSparseMatrix(int rowCount, int columnCount, Element[] item) {
        this(rowCount, columnCount, item.length);
        for (int i = 0; i < item.length; i++)
            this.add(item[i]);
    }

    public SeqSparseMatrix(Element[] item) {
        this(0, 0, item);
    }

    //按行主序插入一个元素的三元组
    public boolean add(Element item) {
        if (this.rowCount <= item.row)
            this.rowCount = item.row + 1;
        if (this.columnCount <= item.column)
            this.columnCount = item.column + 1;

        int i = 0;
        Element temp = this.list.get(i);
        while (i < this.list.length() && item.compareTo(temp) > 0) {
            i++; // item “大”时向后走
            temp = this.list.get(i);
        }
        return this.list.add(i, item);
    }

    public String toString() { //稀疏矩阵描述
        String str = "稀疏矩阵 " + rowCount + "行," + columnCount + "列, " + this.list.length() + "个非零元素\n";
        str += "三元组线性表：" + this.list.toString() + "\n";
        str += "稀疏矩阵：\n";

        int k = 0;
        Element temp = this.list.get(k++);
        for (int i = 0; i < this.rowCount; i++) {
            for (int j = 0; j < this.columnCount; j++) // for if 等代码块中最好有一个 {, 方便查看理解源代码
                if (temp != null && i == temp.row && j == temp.column) {
                    str += temp.value + " ";
                    temp = this.list.get(k++);
                } else
                    str += 0 + " ";
            str += "\n";
        }
        return str;
    }

    //返回转置矩阵
    public SeqSparseMatrix transpose() {
        SeqSparseMatrix trans = new SeqSparseMatrix(columnCount, rowCount, this.list.length());
        for (int i = 0; i < this.list.length(); i++)
            trans.add(this.list.get(i).toSymmetry());
        return trans;
    }

    public SeqSparseMatrix add(SeqSparseMatrix matb) { //返回this和matb相加的矩阵，归并算法
        //不改变当前矩阵
        SeqSparseMatrix matc = new SeqSparseMatrix(rowCount, columnCount, this.list.length());
        int i = 0, j = 0;
        Element tempa = this.list.get(i++);
        Element tempb = matb.list.get(j++);
        while (tempa != null && tempb != null) {
            if (tempa.compareTo(tempb) == 0 ) { // tempa.row==tempb.row && tempa.column==tempb.column)
                matc.list.add(new Element(tempa.row, tempa.column, tempa.value + tempb.value));
                tempa = this.list.get(i++);
                tempb = matb.list.get(j++);
            } else if (tempa.compareTo(tempb) < 0) { //tempa.row<tempb.row || tempa.row==tempb.row && tempa.column<tempb.column)
                matc.list.add(new Element(tempa));
                tempa = this.list.get(i++);
            } else { //tempa.row>tempb.row || tempa.row==tempb.row && tempa.column>tempb.column)
                matc.list.add(new Element(tempb));
                tempb = matb.list.get(j++);
            }
        }

        while (tempa != null) {  //a有多余结点时，全部移给c
            matc.list.add(new Element(tempa));
            tempa = this.list.get(i++);
        }
        while (tempb != null) {   //b有多余结点时，全部移给c
            matc.list.add(new Element(tempb));
            tempb = matb.list.get(j++);
        }

        return matc;
    }

    public static void main(String[] args) {
        Element[] item = {new Element(0, 2, 11), new Element(0, 4, 17), new Element(1, 1, 20), new Element(3, 0, 19), new Element(3, 5, 28), new Element(4, 4, 50)};

        SeqSparseMatrix smata = new SeqSparseMatrix(item);
        System.out.println("A " + smata.toString());
        System.out.println("矩阵A转置 " + smata.transpose().toString());

        SeqSparseMatrix smatb = new SeqSparseMatrix(5,6,3);
        smatb.add(new Element(0,2,10));
        smatb.add(new Element(1,4,9));
        smatb.add(new Element(4,5,20));

        System.out.println("B " + smatb.toString());
        System.out.println("矩阵A+B为 " + smata.add(smatb).toString());

    }

}
/*

A 稀疏矩阵 5行,6列, 6个非零元素
三元组线性表： ((0, 2, 11), (0, 4, 17), (1, 1, 20), (3, 0, 19), (3, 5, 28), (4, 4, 50))
稀疏矩阵：
0 0 11 0 17 0
0 20 0 0 0 0
0 0 0 0 0 0
19 0 0 0 0 28
0 0 0 0 50 0

矩阵A转置 稀疏矩阵 6行,5列, 6个非零元素
三元组线性表： ((0, 3, 19), (1, 1, 20), (2, 0, 11), (4, 0, 17), (4, 4, 50), (5, 3, 28))
稀疏矩阵：
0 0 0 19 0
0 20 0 0 0
11 0 0 0 0
0 0 0 0 0
17 0 0 0 50
0 0 0 28 0

B 稀疏矩阵 5行,6列, 3个非零元素
三元组线性表： ((0, 2, 10), (1, 4, 9), (4, 5, 20))
稀疏矩阵：
0 0 10 0 0 0
0 0 0 0 9 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 20

矩阵A+B为 稀疏矩阵 5行,6列, 8个非零元素
三元组线性表： ((0, 2, 21), (0, 4, 17), (1, 1, 20), (1, 4, 9), (3, 0, 19), (3, 5, 28), (4, 4, 50), (4, 5, 20))
稀疏矩阵：
0 0 21 0 17 0
0 20 0 0 9 0
0 0 0 0 0 0
19 0 0 0 0 28
0 0 0 0 50 20


 */