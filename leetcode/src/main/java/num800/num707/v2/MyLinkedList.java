package num800.num707.v2;

// 对 v1 版本的优化
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

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        MyLinkedList cur = this;

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        if (size == 0) {    // 空链表的头插入
            this.val = val;
            size++;
            return;
        }
        MyLinkedList head = new MyLinkedList(); // head 保存老的头节点
        head.val = this.val;
        head.next = this.next;
        this.val = val;
        this.next = head;
        size++;
    }

    public void addAtTail(int val) {
        if (size == 0) { // 空链表的尾插入 还是头插入
            this.val = val;
            size++;
            return;
        }
        MyLinkedList tail = this; // 循环出来 tail 就是最后一个节点
        while (tail.next != null) {
            tail = tail.next;
        }

        MyLinkedList add = new MyLinkedList();
        add.val = val;
        add.next = null;
        tail.next = add;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }

        // 头节点插入
        if (index == 0) {
            if (size > 0) {
                MyLinkedList head = new MyLinkedList(); // head 保存老的头节点
                head.val = this.val;
                head.next = this.next;
                this.next = head;
            }
            this.val = val;
        } else {
            MyLinkedList cur = this; // 循环出来 cur 就是 index 坐标前一个节点
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            MyLinkedList add = new MyLinkedList();
            add.val = val;
            add.next = cur.next;
            cur.next = add;
        }
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            MyLinkedList head = new MyLinkedList();
            if (this.next != null) {
                head.val = this.next.val;
                head.next = this.next.next;
            }
            this.val = head.val;
            this.next = head.next;
        } else {
            MyLinkedList cur = this; // 循环出来 cur 就是 index 坐标前一个节点
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        size--;
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

        linkedList.addAtIndex(0, 3);
        linkedList.addAtIndex(0, 4);

        linkedList.addAtIndex(0, 5);
        linkedList.addAtIndex(2, 7);

        linkedList.deleteAtIndex(0);

        linkedList.deleteAtIndex(3);


        linkedList.printListNode();
        System.out.println();
    }

}
