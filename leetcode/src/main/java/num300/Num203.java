package num300;


/**
 * 移除链表元素
 * 题意：删除链表中等于给定值 val 的所有节点。
 * 示例 1： 输入：head = [1,2,6,3,4,5,6], val = 6 输出：[1,2,3,4,5]
 * 示例 2： 输入：head = [], val = 1 输出：[]
 * 示例 3： 输入：head = [7,7,7,7], val = 7 输出：[]
 */
public class Num203 {

    public static void main(String[] args) {

        ListNode node = init();
        printListNode(node);

        ListNode listNode = removeElements(node, 1);
        printListNode(listNode);

    }

    // 直接删除: 删除头节点与删除普通节点
    public static ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) { // 处理头节点是val的 或者val->val->val... 保证了第一个节点不是 val
            head = head.next;
        }

        if (head == null) {
            return null;
        }
        ListNode t = head;
        while (t.next != null) {
            if (t.next.val == val) {
                t.next = t.next.next; // 删除节点
            } else {
                t = t.next;
            }
        }
        return head;
    }

    /**
     * 不添加虚拟节点方式, pre前一个节点, cur当前节点
     * 时间复杂度 O(n)  空间复杂度 O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements01(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return head;  // 已经为 null，提前退出
        }
        // 当前 head.val != val
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    /**
     * 加入虚拟节点的 虚拟节点不参与判断
     */
    public static ListNode removeElements02(ListNode head, int val) {
        ListNode n = new ListNode(0, head);
        ListNode t = n;

        while (t.next != null) { // t.next 表示原链表的头节点
            if (t.next.val == val) {
                t.next = t.next.next;
            } else {
                t = t.next;
            }
        }
        return n.next;
    }

    // 加入虚拟节点的
    public static ListNode removeElements03(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        // 因为删除可能涉及到头节点，所以设置dummy节点，统一操作
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
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

    private static class ListNode {
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

