package Algorithm.offer;
import java.util.Stack;

/**
 * 两个链表中的第一个公共节点
 * 从头找不方便，那就从后往前找
 * 先把两个链表存到两个栈里面，然后不断弹出比较，最后一个相等的节点即为第一个公共的节点
 */
public class Main52 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }

        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        ListNode c1 = pHead1;
        while(c1!=null){
            s1.push(c1);
            c1 = c1.next;
        }
        ListNode c2 = pHead2;
        while(c2!=null){
            s2.push(c2);
            c2 = c2.next;
        }
        ListNode first = null;
        while(!s1.empty() && !s2.empty()){

            if(s1.peek() == s2.peek()){
                first = s1.pop();
                s2.pop();

            }else{
                break;
            }

        }
        return first;

    }
}
