package Algorithm.offer;

/**
 * 求链表的倒数第k个节点
 * 注意鲁棒性,一些特殊条件
 */
public class Main22 {

    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k == 0){
            return null;
        }

        ListNode fastIndex = head;
        ListNode currentIndex = head;
        int n = 1;
        while (currentIndex.next != null){
            n++;
            currentIndex = currentIndex.next;
        }
        if (k > n){
            return null;
        }

        for (int i = 0; i < k - 1; i++){
            fastIndex = fastIndex.next;
        }
        currentIndex = head;
        while (fastIndex.next != null){
            currentIndex = currentIndex.next;
            fastIndex = fastIndex.next;
        }

        return currentIndex;

    }

    public static void main(String[] args) {
        System.out.println(100 >> 4);
    }
}
