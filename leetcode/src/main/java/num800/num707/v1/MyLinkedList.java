package num800.num707.v1;

import num100.Num2;
import test.ListNode;

/**
 * 设计链表
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * 个人实现, 后面回头再看的时候, 发现写的有很多问题, 仅供参考.
 */
public class MyLinkedList {

    private int val;
    private MyLinkedList next;
    private int size;

    /**
     * 初始化 val: 0 next: null  size:0
     * Initialize your data structure here.
     */
    public MyLinkedList() {
    }

    /**
     * 没有利用到 size 进行判断 index 是否有效
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || size == 0) {
            return -1;
        }
        MyLinkedList list = this;

        for (int i = 0; i < index; i++) {
            if (list == null) {
                break;
            }
            list = list.next;
        }

        if (list != null) {
            return list.val;
        }
        return -1;

    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        if (size == 0) {    // 空链表的头插入
            this.val = val;
            size++;
            return;
        }
        MyLinkedList head = new MyLinkedList();
        head.val = this.val;
        head.next = this.next;
        this.val = val;
        this.next = head;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        if (size == 0) { // 空链表的尾插入 还是头插入
            this.val = val;
            size++;
            return;
        }
        MyLinkedList list = this;
        MyLinkedList rear = list;
        while (list.next != null) { // 快慢指针找到最后一个节点
            list = list.next;
            rear = list;
        }

        MyLinkedList add = new MyLinkedList();
        add.val = val;
        add.next = null;
        rear.next = add;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (size == 0) {
            if (index == 0) { // 其它情况 size++ 返回 可能存在问题
                this.val = val;
            }
            size++;
            return;
        }

        if (index <= 0) { // 头节点插入
            MyLinkedList tmp = new MyLinkedList();
            tmp.val = this.val;
            tmp.next = this.next;
            this.val = val;
            this.next = tmp;
            size++;
            return;
        }

        MyLinkedList list = this; // list 对应下标是 index, 在它之前 插入节点
        MyLinkedList cur = list; // 指定下标前一个节点  插入到 cur 后面
        int i = 0;
        for (; i < index; i++) {
            if (list == null) {
                break;
            }
            cur = list;
            list = list.next;
        }
        if (i == index) {
            MyLinkedList tmp = new MyLinkedList();
            tmp.val = val;
            tmp.next = cur.next;
            cur.next = tmp;
            size++;
        }

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndexT(int index) {
        if (index < 0 || size == 0) {
            return;
        }
        // 加入虚头节点
        MyLinkedList head = new MyLinkedList();
        head.val = -1;
        head.next = this;

        MyLinkedList d = head; // d 待删除节点的前一个节点
        MyLinkedList t = head.next; // t 要删除的节点
        for (int i = 0; i < index; i++) {
            if (t != null) {
                d = t;
                t = t.next;
            } else {
                return;
            }
        }

        if (t != null) {
            d.next = t.next;
            size--;
        }

        if (head.next != null) {
            this.val = head.next.val; // 处理添加的虚拟头节点
            this.next = head.next.next;
        } else {
            this.val = 0;
            this.next = null;
        }

    }

    public void deleteAtIndex(int index) {
        if (index < 0 || size == 0) {
            return;
        }
        if (index == 0) {   // 移除头节点
            MyLinkedList head = new MyLinkedList();
            if (this.next != null) {
                head.val = this.next.val;
                head.next = this.next.next;
            }
            this.val = head.val;
            this.next = head.next;
            size--;
            return;
        }
        MyLinkedList list = this;  // 需要删除的节点
        MyLinkedList cur = list;  // 待删除节点的前一个节点
        int i = 0;
        for (; i < index; i++) {
            if (list == null) {
                break;
            }
            cur = list;
            list = list.next;
        }
        if (i == index && list != null) { // 如果list == null, 就不用删除了
            cur.next = list.next;
            size--;
        }
    }


    public void printListNode() {
        MyLinkedList listNode = this;
        while (listNode != null) {
            System.out.print(listNode.val + "  ");
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
//        linkedList.addAtHead(0);
//        linkedList.addAtIndex(1, 1);
//        linkedList.get(2);   //链表变为1-> 2-> 3
//        linkedList.addAtHead(4);            //返回2
//        linkedList.get(2);  //现在链表是1-> 3
//        linkedList.addAtHead(4);
//        linkedList.get(2);            //返回3
//        linkedList.get(3);            //返回3
//        linkedList.addAtIndex(1,6);            //返回3
//        linkedList.addAtTail(1);
//        linkedList.addAtHead(0);
//        linkedList.addAtTail(1);
//        //linkedList.addAtHead(3);
//        linkedList.deleteAtIndexT(0);
//        System.out.println(linkedList);

        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);
        linkedList.get(1);
        linkedList.deleteAtIndexT(1);
        linkedList.get(1);

        linkedList.printListNode();
    }
}
