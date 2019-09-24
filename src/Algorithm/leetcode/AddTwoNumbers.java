package Algorithm.leetcode;

import java.util.List;
import java.util.Stack;

public class AddTwoNumbers {
    class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        ListNode v1 = l1;
        ListNode v2 = l2;
        while(true){
            if(v1==null && v2 == null){
                break;
            }
            if(v1 != null){
                sb1.append(v1.val);
                v1 = v1.next;
            }
            if(v2 != null){
                sb2.append(v2.val);
                v2 = v2.next;
            }
        }
        int ans1 = Integer.valueOf(sb1.reverse().toString());
        int ans2 = Integer.valueOf(sb1.reverse().toString());
        ans1 = ans2 + ans1;
        String ls = String.valueOf(ans1);
        ListNode ansList = new ListNode(Integer.valueOf(ls.charAt(ls.length() - 1)));
        ListNode currentNode = ansList;
        for (int i = ls.length() - 2; i >= 0; i--){
            ListNode node = new ListNode(Integer.valueOf(ls.charAt(i)));
            currentNode.next = node;
            currentNode = currentNode.next;
        }
        return ansList;
    }
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode v1 = l1, v2 = l2;
        int carry = 0;
        ListNode head = new ListNode(0);
        ListNode cur= head;
        while ( v1 != null || v2 != null){
            int x = (v1 == null) ? 0:v1.val;
            int y = (v2 == null) ? 0:v2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            ListNode next = new ListNode(carry%10);
            cur.next = next;
            cur = cur.next;
            if (v1 != null) v1 = v1.next;
            if (v2 != null) v2 = v2.next;
        }
        if (carry > 0)
            cur.next = new ListNode(carry);
        return head.next;
    }
}
