package com.lang.stu.linearlist;

/**
 * 双链表结点类
 *
 */
public class DLinkNode<E> {

    public E data; // 数据元素
    public DLinkNode<E> prev, next; //前驱, 后继节点

    // 构造结点，指定对象、前驱和后继结点
    public DLinkNode(E data, DLinkNode<E> prev, DLinkNode<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public DLinkNode(E data) {
        this(data, null, null);
    }

    public DLinkNode() {
        this(null, null, null);
    }

    // 返回结点元素值对应的字符串
    public String toString() {
        return this.data.toString();
    }


}
