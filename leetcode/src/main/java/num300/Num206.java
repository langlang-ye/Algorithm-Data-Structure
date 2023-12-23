package num300;

import java.util.Stack;

/**
 * 题意：反转一个单链表。
 * 示例: 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
 */
public class Num206 {

    public static void main(String[] args) {
        ListNode node = init();
        printListNode(node);

//        ListNode listNode = reverseList(node);
//        printListNode(listNode);

//        ListNode listNode1 = reverseListRecursion(node);
//        printListNode(listNode1);

//        printListNode(reverseList1(node));
        printListNode(reverseListByStack(node));

    }

    /**
     * 直接处理: {@link num100.Num2 }
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next; // 保存下一个节点
            cur.next = pre; // 将之前逆序好的节点赋值给 当前节点
            pre = cur;  // 当前节点赋值给 pre  现在的pre 是已经逆序好的链表
            cur = tmp; // 下一个节点赋值给当前节点 准备下一轮的循环
        }
        return pre;
    }

    /**
     * 递归写法1: 访问一个节点, 对当前节点进行逆序处理.
     * 递归写法2: 从后向前递归   {@link num100.Num2 }
     */
    public static ListNode reverseListRecursion(ListNode head) {
        return reverseListRecursion(null, head);
    }

    /*
        访问一个节点, 对当前节点进行逆序处理.
     */
    private static ListNode reverseListRecursion(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;    // 递归结束, prev 表示已经逆序好的链表
        }
        ListNode temp = null;
        temp = cur.next;// 先保存下一个节点
        cur.next = prev;// 反转
        // 更新prev、cur位置
        prev = cur; // prev 已经逆序好的链表
        cur = temp; // 下一个节点 赋值给当前节点, 准备下一轮的循环
        return reverseListRecursion(prev, cur);
    }


    /**
     * 使用虚拟头结点，通过头插法实现链表的反转
     */
    public static ListNode reverseList1(ListNode head) {
        // 创建虚头结点
        ListNode dumpyHead = new ListNode(-1);
        dumpyHead.next = null;
        // 遍历所有节点
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next; // 保存下一个节点
            // 头插法
            cur.next = dumpyHead.next; // dumpyHead.next 表示已经逆序好的链表, 赋值给cur.next
            dumpyHead.next = cur; // 将cur 赋值给 dumpyHead.next, 实现了对 cur 的逆序.
            cur = temp; // 将下一个节点赋值给cur, 准备下一次的循环
        }
        return dumpyHead.next;
    }

    public static ListNode reverseListByStack(ListNode head) {
        // 如果链表为空，则返回空
        if (head == null) return null;
        // 如果链表中只有只有一个元素，则直接返回
        if (head.next == null) return head;
        // 创建栈 每一个结点都入栈
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 创建一个虚拟头结点
        ListNode pHead = new ListNode(0);
        cur = pHead;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            cur.next = node;
            cur = cur.next;
        }
        // 最后一个元素的next要赋值为空
        cur.next = null;
        return pHead.next;
    }


    private static ListNode init() {
        ListNode listNode4 = new ListNode(4, null);
        ListNode listNode2 = new ListNode(2, listNode4);
        ListNode listNode44 = new ListNode(4, listNode2);
        ListNode node = new ListNode(1, listNode44);
        return node;
    }


    public static void printListNode(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + "  ");
            listNode = listNode.next;
        }
        System.out.println();
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

}
