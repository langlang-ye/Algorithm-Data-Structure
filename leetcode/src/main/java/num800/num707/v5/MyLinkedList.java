package num800.num707.v5;


public class MyLinkedList {
    // 记录链表的虚拟头结点和尾结点
    private ListNode head, tail;

    // 记录链表中元素的数量
    private int size;

    public MyLinkedList() {
        this.size = 0;
        this.head = new ListNode(0);
        this.tail = new ListNode(0);

        //这一步非常关键，否则在加入头结点的操作中会出现null.next的错误！！！
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        // 判断 index 是否有效
        if (index < 0 || index >= size) {
            return -1;
        }

        ListNode cur = this.head;

        //判断是哪一边遍历时间更短
        if (index >= size / 2) {
            // tail 开始
            cur = tail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
        } else {
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        }

        return cur.val;
    }

    public void addAtHead(int val) {
        //等价于在第0个元素前添加
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        //等价于在最后一个元素(null)前添加
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        // index 大于链表长度
        if (index > size) {
            return;
        }
        // index 小于 0
        if (index < 0) {
            index = 0;
        }

        // 新建节点
        ListNode add = new ListNode(val);
        if (index < size / 2) {
            // 找到前驱节点
            ListNode pre = this.head;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }

            add.next = pre.next;
            pre.next.prev = add;
            add.prev = pre;
            pre.next = add;
        } else {
            // 从尾部 tail 开始
            ListNode cur = this.tail; // cur 即 index 下标对应的节点

            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }

            add.next = cur;
            add.prev = cur.prev;
            cur.prev.next = add;
            cur.prev = add;
        }

        size++;
    }

    public void deleteAtIndex(int index) {
        // 判断索引是否有效
        if (index < 0 || index >= size) {
            return;
        }

        if (index < size / 2) {
            ListNode pre = this.head;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            pre.next.next.prev = pre;
            pre.next = pre.next.next;
        } else {
            // 从尾部 tail 开始
            ListNode cur = this.tail; // cur 即 index 下标对应的节点

            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }

        // 删除操作
        size--;

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
        linkedList.printListNode();

        linkedList.addAtHead(2);
        linkedList.addAtHead(3);
        linkedList.addAtHead(4);

        linkedList.addAtTail(11);
        linkedList.addAtTail(12);
        linkedList.addAtTail(15);
        linkedList.deleteAtIndex(0);

        linkedList.addAtTail(21);
        linkedList.addAtTail(34);
        linkedList.deleteAtIndex(1);

        linkedList.deleteAtIndex(5);
        linkedList.deleteAtIndex(0);

        linkedList.printListNode();

    }

    // 定义双链表
    class ListNode {
        int val;
        ListNode next, prev;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }
}
