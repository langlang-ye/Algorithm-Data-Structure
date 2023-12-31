package num800.num707.v7;

// 与版本 v3 类似  参看占用内存最小的方法  结果暂用依然很高
public class MyLinkedList {

    int size;
    ListNode head;

    public MyLinkedList() {
        this.size = 0;
        this.head = new ListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index >= this.size) {
            return -1;
        }

        ListNode cur = this.head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(this.size, val);
    }
    public void addAtIndex(int index, int val) {
        if (index > this.size) {
            return;
        }

        if (index < 0) {
            index = 0;
        }

        this.size++;
        ListNode prev = this.head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        ListNode add = new ListNode(val);
        add.next = prev.next;
        prev.next = add;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            return;
        }
        size--;

        ListNode cur = this.head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.next = cur.next.next;
    }
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

        linkedList.addAtTail(11);
        linkedList.addAtTail(12);
        linkedList.addAtTail(15);
        linkedList.deleteAtIndex(0);

        linkedList.addAtTail(21);
        linkedList.addAtTail(34);
        linkedList.deleteAtIndex(1);

        linkedList.printListNode();
    }
    class ListNode {
        ListNode next;
        int val;
        public ListNode() {}
        public ListNode(int val) {
            this.val = val;
        }
    }


}
