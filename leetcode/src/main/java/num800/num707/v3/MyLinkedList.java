package num800.num707.v3;


/**
 * 设计链表
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * 单链表的实现: 虚拟头节点
 */
public class MyLinkedList {

    // 虚拟头节点
    private ListNode head;

    // size 存储链表元素的个数
    private int size;

    /**
     * 初始化 虚拟头节点  长度
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    /**
     * 获取第 index 个节点的数值，注意 index 是从 0开始的，第 0 个节点就是头结点
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        // 如果 index 非法，返回 -1
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = head;

        //包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;

    }

    /**
     * 在链表最前面插入一个节点，等价于在第 0 个元素前添加
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * 在链表的最后插入一个节点，等价于在(末尾 + 1 )个元素前添加
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * 在第 index 个节点之前插入一个新节点，例如 index 为 0 或者 < 0 ，那么新插入的节点为链表的新头节点。
     * 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
     * 如果 index 大于链表的长度，则返回
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;

        //找到要插入节点的前驱
        ListNode pre = head;

        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        ListNode add = new ListNode(val);
        add.next = pre.next;
        pre.next = add;
    }

    /**
     * 删除第 index 个节点
     * 删除方法: index=0, head 直接取代虚拟头节点
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndexT(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        size--;
        if (index == 0) {
            head = head.next;
            return;
        }

        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
    }

    /**
     * 删除第 index 个节点
     * 删除方法: index=0, 删除头节点 不修改虚拟头节点
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        size--;
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
    }

    // 通过 size 遍历
    public void printListNode() {
        ListNode cur = head;

        //包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i < size; i++) {
            cur = cur.next;
            System.out.print(cur.val + "  ");
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

//        linkedList.printListNode(linkedList);
//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1, 2);
//        linkedList.get(1);
//        linkedList.deleteAtIndex(1);
//        linkedList.get(1);

        linkedList.addAtTail(11);
        linkedList.addAtTail(12);
        linkedList.addAtTail(15);
        linkedList.deleteAtIndex(0);

        linkedList.addAtTail(21);
        linkedList.addAtTail(34);
        linkedList.deleteAtIndex(1);

        linkedList.printListNode();
    }


    // 定义单链表
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

}
