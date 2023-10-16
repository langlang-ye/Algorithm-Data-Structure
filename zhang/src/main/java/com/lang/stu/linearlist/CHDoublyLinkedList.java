package com.lang.stu.linearlist;

/**
 * 带头结点的循环双链表类
 */
public class CHDoublyLinkedList<E> implements LList<E> {

    protected DLinkNode<E> head; // 头指针

    public CHDoublyLinkedList() { // 构造空链表
        this.head = new DLinkNode<E>(); // 创建头结点 值是 null
        this.head.prev = head;
        this.head.next = head;
    }

    @Override
    public boolean isEmpty() { //判断双链表是否为空
        return head.next == head;
    }

    // 注意: 以下算法同循环单链表，与单链表的差别在于，循环条件不同
    @Override
    public int length() { //返回双链表长度
        int i = 0;
        DLinkNode<E> p = this.head.next; // 注意: 此句与单链表不同
        while (p != head) { //循环条件与单链表不同
            i++;
            p = p.next;
        }
        return i;
    }

    @Override
    public E get(int index) { //返回序号为index的对象
        if (index >= 0) {
            int j = 0;
            DLinkNode<E> p = this.head.next;
            while (p != head && j < index) {
                j++;
                p = p.next;
            }
            if (p != head)
                return (E) p.data;
        }
        return null;
    }

    @Override
    public E set (int index, E element) { //设置index序号对象为element
        if (index >= 0 && element != null) {
            int j = 0;
            DLinkNode<E> p = this.head.next;
            while (p != head && j < index) {
                j++;
                p = p.next;
            }
            if (p != head) {
                E old = (E) p.data;
                p.data = element;
                return old;
            }
        }
        return null;
    }

    public String toString() {
        String str = "(";
        DLinkNode<E> p = this.head.next;
        while (p != head) {
            str += p.data.toString();
            p = p.next;
            if (p != head)
                str += ", ";
        }
        return str + ")";
    }

    //双链表的插入、删除算法与单链表不同
    @Override
    public boolean add (int index, E element) { //插入element对象，插入后对象序号为index
        //若操作成功返回 true，O(n)
        if (element == null)
          return false; //不能添加空对象（null）

        int j = 0;
        DLinkNode<E> front = this.head;
        while(front.next != head && j < index) { //寻找插入位置，若 index <= 0，插入在头结点之后
            j++;
            front = front.next;
        }
        DLinkNode<E> q = new DLinkNode<E>(element, front, front.next);
        front.next.prev = q;
        front.next = q;
        return true;
    }

    @Override
    public boolean add(E element) {  //在链表最后添加对象，O(1)
        if (element == null)
            return false; //不能添加空对象

        DLinkNode<E> q = new DLinkNode<E>(element, head.prev, head);
        head.prev.next = q; //插入在头结点之前，相当于尾插入
        head.prev = q;
        return true;
    }

    @Override
    public E remove(int index) {
        E old = null;
        int j = 0;
        DLinkNode<E> p = this.head.next;
        while (p != head && j < index) {
            j++;
            p = p.next;
        }
        if(p != head) {
            old = (E) p.data;
            p.prev.next = p.next;
            p.next.prev = p.prev;
        }
        return old;
    }

    @Override
    public void clear() { // 清空线性表
        this.head.prev = head;
        this.head.next = head;
    }

    public static void main(String[] args) {

        int i = 0;
        CHDoublyLinkedList<String> list = new CHDoublyLinkedList<String>();
        System.out.println("删除第" + i + "个结点" + list.remove(0));
        System.out.println(list.toString());

        for (i = 5; i >= 0; i--)
            list.add(0, new String((char)('A' + i ) + ""));

        for (i = 0; i < 6; i++)
            list.add(new String((char)('H'+i)+""));
//        list.add(i, new String((char)('H'+i)+""));

        System.out.println(list.toString());

        System.out.println("删除第" + i + "个结点" + list.remove(i));
        System.out.println(list.toString());

    }
}
