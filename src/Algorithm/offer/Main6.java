package Algorithm.offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 *反转链表，
 * 1\使用栈，
 * 本质是先遍历的后出去
 * 2\用递归，当不为空的时候继续往下找，空的时候返回，然后再输出
 *
 */
public class Main6 {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ListNode current = listNode;
        while (current != null){
            stack.push(current.val);
            current = current.next;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.empty()){
            list.add(stack.pop());
        }

        return list;
    }
}
