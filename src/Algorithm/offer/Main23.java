package Algorithm.offer;

/**
 * 链表中环的入口节点
 */
public class Main23 {
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null || pHead.next == null){
            return null;
        }
        //用两个速度不一样的指针判断是否存在环
        //指针速度一个快，一个慢
        ListNode fast = pHead.next.next;
        ListNode slow = pHead;
        boolean ifHuan = false;
        while (fast != null && slow != null && fast != slow){
            if (fast == slow){
                ifHuan = true;
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果因为指针为空跳出上面的循环,说明不存在环,则返回结果
        if (slow == null){
            return null;
        }
//        slow = pHead;
        //求环中节点的个数 n
        int n = 1;
        while (fast.next != slow){
            n++;
            fast = fast.next;
        }
        //用快指针先走n步,然后慢指针从头开始,两个指针一起走,相遇的时候就到了环入口
        fast = pHead;
        slow = pHead;
        while (n > 0){
            fast = fast.next;
            n--;
        }
        //两指针开始一起走
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        //相同的时候,即走到了环的入口
        return fast;

    }
}
