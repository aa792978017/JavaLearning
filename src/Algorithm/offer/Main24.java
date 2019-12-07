package Algorithm.offer;


/**
 * 反转链表
 */
public class Main24 {
    public ListNode ReverseList(ListNode head) {

        if(head == null)
            return null;
        ListNode cur = head.next;
        ListNode newHead = head;
        newHead.next = null;
        ListNode backNode;
        while (cur != null){
            backNode = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = backNode;
        }
        return newHead;
    }


    public static void main(String[] args) {
        Main24 main24 = new Main24();
        main24.ReverseList(new ListNode(1));
    }
}
