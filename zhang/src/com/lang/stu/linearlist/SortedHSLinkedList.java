package com.lang.stu.linearlist;

/**
 * 建立按升序排序的单链表
 */
public class SortedHSLinkedList<E> extends HSLinkedList<E> { //按升序排序的单链表类

    public SortedHSLinkedList() {
        super();
    }

    //根据指定对象的大小插入在合适位置
    public boolean add(E element) {   //若操作成功返回true，O(n)
        if (element == null || !(element instanceof Comparable))
            return false;    //不能插入null或非Comparable对象

        Comparable cmp = (Comparable) element;
        Node<E> front = this.head, p = front.next; //front是p的前驱结点
        while (p != null && cmp.compareTo(p.data) > 0) { // 寻找插入位置
            front = p;
            p = p.next;
        }
        front.next = new Node<E>(element, p); //插入
        if (p == null)
            this.rear = front.next; //尾插入要修改尾指针
        this.n++;
        return true;
    }

    /* // 原处代码是错误的
    public boolean remove(E element) { //删除指定对象
        if (element == null || !(element instanceof Comparable))  // 若操作成功返回true，O(n)
            return false;
        Comparable cmp = (Comparable) element;
        Node<E> front = this.head, p = front.next;  //front是p的前驱结点
        while (p != null && cmp.compareTo(p.data) > 0) { //寻找待删除的结点
            front = p;
            p = p.next;
        }
        if (p == null)
            return false;  //未找到指定结点，删除不成功

        front.next = p.next;     //删除p结点
        if (p == this.rear)
            this.rear = front;  // 删除的是最后一个结点， 修改尾指针
        this.n--;
        return true;
    }
    */

    // 方法一： while 条件里修改为， cmp.compareTo(p.data) ！= 0  如果没有找到指定元素，需要访问整个链表
    // 方法二： while 条件不变， 在 if 条件里加上一个判断是不是相等
    public boolean remove(E element) { //删除指定对象
        if (element == null || !(element instanceof Comparable))  // 若操作成功返回true，O(n)
            return false;
        Comparable cmp = (Comparable) element;
        Node<E> front = this.head, p = front.next;  //front是p的前驱结点
        while (p != null && cmp.compareTo(p.data) > 0) { //寻找待删除的结点
            front = p;
            p = p.next;
        }
        if (p == null && cmp.compareTo(p.data) == 0) // 如果不相等， 就是没有指定结点
            return false;  //未找到指定结点，删除不成功

        front.next = p.next;     //删除p结点
        if (p == this.rear)
            this.rear = front;  // 删除的是最后一个结点， 修改尾指针
        this.n--;
        return true;
    }

    public static void main(String[] args) {

        SortedHSLinkedList<Integer> lista = new SortedHSLinkedList<Integer>();
        int n = 10;
        System.out.print("insert： ");
        for (int i = 0; i < n; i++) {
            int k = (int) (Math.random() * 100);              //产生随机数
            if (lista.add(new Integer(k)))
                System.out.print(k + "  ");
        }
        System.out.println("\nlist: " + lista.toString());

    }

}

