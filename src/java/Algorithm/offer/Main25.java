package java.Algorithm.offer;


/**
 * 合并两个递增的链表
 */
public class Main25 {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        ListNode head = new ListNode(0);
        ListNode curL1 = list1;
        ListNode curL2 = list2;
        ListNode current = head;
        while (curL1 != null && curL2 != null){

            if (curL1.val > curL2.val){
                current.next = curL2;
                curL2 = curL2.next;
            }else{
                current.next = curL1;
                curL1 = curL1.next;
            }
            current = current.next;
        }

        while (curL1 != null){
            current.next = curL1;
            curL1 = curL1.next;
            current = current.next;
        }

        while (curL2 != null){
            current.next = curL2;
            curL2 = curL2.next;
            current = current.next;
        }
        return head.next;
    }
}
