package com.lang.stu.linearlist;


//单链表逆转
public class ReverseLinkedList<E> extends SinglyLinkedList<E> {
    public ReverseLinkedList() {
        super(); // 调用父类同参数的构造方法
    }

    public void reverse() {  // 将单链表逆转
        // front 代表已经逆转的单链表， q 是待逆转链表， p 当前要逆转的元素
        Node<E> p = this.head, q = null, front = null;
        while (p != null) {
            q = p.next;        //设置q是p结点的后继结点
            p.next = front;      //使p.next指向p结点的前趋结点
            front = p;
            p = q;      //p向后走一步
        }
        this.head = front;
    }

    public static void main(String args[]) {
        ReverseLinkedList<Integer> list = new ReverseLinkedList<Integer>();
        for (int i = 1; i < 8; i++)
            list.add(0, new Integer(i));

        System.out.println("单链表 " + list.toString());
        list.reverse();
        System.out.println("逆转后 " + list.toString());
    }
}

