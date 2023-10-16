package com.lang.stu.array;

/**
 * 矩阵类
 */
public class Matrix {

    private int value[][]; //存储矩阵元素的二维数组

    public Matrix(int m, int n) { //构造m行n列的空矩阵
        this.value = new int[m][n];
    }

    public Matrix(int n) { //构造n行n列的空矩阵
        this(n,n);
    }

    public Matrix() {
        this(10, 10);
    }

    public Matrix(int mat[][]) { //构造矩阵，由数组 mat 提供矩阵元素
        this(mat.length, mat[0].length);
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[i].length; j++)
                this.value[i][j] = mat[i][j];
    }

    public int get(int i, int j) { //获得矩阵第i行第j列的元素，O(1)
        return  value[i][j];
    }

    public void set(int i, int j, int k) { //设置矩阵第i行第j列的元素，O(1)
        value[i][j] = k;
    }

    public String toString() { //行主序遍历，访问矩阵全部元素
        String str = "";
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++)
                str += " " + value[i][j];
            str += "\n";
        }

        return str;
    }

    public void add(Matrix b) { //this 和 b 两个矩阵相加，改变当前矩阵
        for (int i = 0; i < this.value.length; i++)
            for (int j = 0; j < this.value[i].length; j++)
                this.value[i][j] += b.value[i][j];
    }

    public Matrix transpose() { //返回转置矩阵
        Matrix trans = new Matrix(value[0].length, value.length);
        for (int i = 0; i < this.value.length; i++)
            for (int j = 0; j < this.value[i].length; j++)
                trans.value[j][i] = this.value[i][j];

        return trans;
    }

}

class Matrix_ex {

    public static void main(String[] args) {
        int m1[][] = {{1, 2, 3}, {4, 5, 6}};
        Matrix a = new Matrix(m1);

        int m2[][] = {{1, 0, 0}, {0, 1, 0}};
        Matrix b = new Matrix(m2);

        System.out.print("Matrix a:\n" + a.toString());
        System.out.print("Matrix b:\n" + b.toString());

        a.add(b);

        System.out.print("Matrix a:\n" + a.toString());
        System.out.println("a的转置矩阵：\n" + a.transpose().toString());
    }

}
/*
Matrix a:
 1 2 3
 4 5 6
Matrix b:
 1 0 0
 0 1 0
Matrix a:
 2 2 3
 4 6 6
a的转置矩阵：
 2 4
 2 6
 3 6
 */
