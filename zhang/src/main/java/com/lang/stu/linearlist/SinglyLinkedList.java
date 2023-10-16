package com.lang.stu.linearlist;

//单链表类，实现线性表接口
public class SinglyLinkedList<E> implements LList<E> {

    protected Node<E> head;        //头指针，指向单链表第 1 个结点

    public SinglyLinkedList() {
        this.head = null;     //构造空单链表
    }

    public SinglyLinkedList(Node<E> head) {
        this.head = head;     //构造指定头指针的单链表
    }

    public boolean isEmpty() {
        return this.head == null; // 判断单链表是否为空，O(1)
    }

    //返回单链表长度
    public int length() {    //单链表遍历算法，O(n)
        int i = 0;
        Node<E> p = this.head;
        while (p != null) {
            i++;
            p = p.next;
        }
        return i;
    }

    //返回序号为 index 的对象，index 初值为 0
    public E get(int index) {
        //若单链表空  或  序号错误返回null， O(n)
        if (this.head != null && index >= 0) {
            int j = 0;
            Node<E> p = this.head;
            while (p != null && j < index) {
                j++;
                p = p.next;
            }
            if (p != null)
                return (E) p.data;
        }
        return null;
    }

    // 设置序号为index的对象为element，O(n)
    public E set(int index, E element) {
        // 若操作成功返回原对象，否则返回null
        if (this.head != null && index >= 0 && element != null) {
            int j = 0;
            Node<E> p = this.head;
            while (p != null && j < index) {
                j++;
                p = p.next;
            }
            if (p != null) {
                E old = (E) p.data;
                p.data = element;
                return old;        //若操作成功返回原对象
            }
        }
        return null;    //操作不成功
    }

    //插入element对象，插入后对象序号为 index (插入到指定下标的位置)
    public boolean add(int index, E element) {
        //若操作成功返回true，O(n)
        if (element == null)
            return false;  //不能添加空对象（null）
        if (this.head == null || index <= 0)        // 头插入
            this.head = new Node<E>(element, this.head);
        else {
            int j = 0;
            Node<E> p = this.head;
            while (p.next != null && j < index - 1) {     //寻找插入位置
                j++;
                p = p.next;
            }
            p.next = new Node<E>(element, p.next);    //中间/尾插入
        }
        return true;
    }

    // 在单链表最后添加对象，重载，O(n)
    public boolean add(E element) {
        return add(Integer.MAX_VALUE, element);
    }

    // 移去序号为index的对象，O(n)
    public E remove(int index) {
        // 若操作成功返回被移去对象，否则返回null
        E old = null;
        if (this.head != null && index >= 0)
            if (index == 0) { // 头删除
                old = (E) this.head.data;
                this.head = this.head.next;
            } else { // 中间/尾删除
                int j = 0;
                Node<E> p = this.head;
                // 定位到待删除结点的前驱结点
                while (p.next != null && j < index - 1) {
                    j++;
                    p = p.next;
                }
                if (p.next != null) {
                    old = (E) p.next.data; // 操作成功，返回原对象
                    p.next = p.next.next; // 删除p的后继结点
                }
            }
        return old;
    }

    // 清空单链表，O(1)
    public void clear() {
        this.head = null;
    }

    public String toString() {
        String str = "(";
        Node<E> p = this.head;
        while (p != null) {
            str += p.data.toString();
            p = p.next;
            if (p != null)
                str += ", ";
        }
        return str + ")";
    }

    // 注： 最后两个 boolean remove(E element) 和 Node<E> search(E element)
    // 方法实现： langlang.ye

    // 删除 *首次* 出现的值为x的结点，若没找到指定结点则不删除。O(n)
    public boolean remove(E element) {
        if (element == null)
            return false;

        Node<E> p = this.head, front = null;
        if (p != null && p.data.equals(element)) { //头结点删除： 没有前一个结点...
            //p = p.next; 这样写头结点删除不掉
            this.head = p.next;
            return true;
        }
        while (p != null && !p.data.equals(element)) {
            front = p;
            p = p.next;
        }
        if (p != null && front != null) {    // 它的前一个结点的下一个结点指向它的下一个结点
            front.next = p.next;// 中间和尾删除
            return true;
        }
        return false;
    }

    public boolean removeT(E element) {
        if (element == null)
            return false;

        Node<E> p = this.head, front = null;
        if (p != null && p.data.equals(element)) { //头结点删除： 没有前一个结点...
            //p = p.next; 这样写头结点删除不掉
            //this.head = p.next; // hashtable 可以； old 包下的 hashtable 不行；
            //this.head = this.head.next; // 不行
            //this.head.data = null;
            /*if(p.next != null){
				Node<E> latter = p.next;
				head.data = latter.data;
				head.next = latter.next;
			} else {
				head = null;
			}*/
            return true;
        }
        while (p != null && !p.data.equals(element)) {
            front = p;
            p = p.next;
        }
        if (p != null && front != null) {    // 它的前一个结点的下一个结点指向它的下一个结点
            front.next = p.next;// 中间和尾删除
            return true;
        }
        return false;
    }

    // 顺序查找关键字为 element 元素，返回  *首次* 出现的元素，若查找不成功返回null
    // element 可以只包含关键字数据项，由E类的equals()方法提供比较对象相等的依据
    public Node<E> search(E element) {
        if (element == null)
            return null;
        for (Node<E> p = this.head; p != null; p = p.next)
            if (p.data.equals(element))
                return p;
        return null;
    }
}
