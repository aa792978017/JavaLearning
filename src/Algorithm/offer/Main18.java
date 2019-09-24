package Algorithm.offer;

/**
 * 删除重复的节点
 * 用的指针比较多，注意程序的鲁棒性
 */
public class Main18 {
    public ListNode deleteDuplication(ListNode pHead) {

        //若头指针为null，直接返回
        if(pHead == null) {
            return null;
        }
        //定义不重复的上一个节点和遍历节点
        ListNode preNode = null;
        ListNode Node = pHead;
        //当前遍历的节点不为空
        while (Node != null){
            //标记这次遍历是否有遇到重复的元素
            boolean delete = false;
            //跟下一个节点比较,
            if (Node.next != null && Node.val == Node.next.val){
//                遇到重复的节点，更改标记
                delete = true;
            }
//            如果没有标记，则指向下一个节点
            if (!delete){
                preNode = Node;
                Node = Node.next;
            }else{
//                如果有标记，记录该重复节点的值，把后面相同值的节点都找到，一次性都删除
                int val = Node.val;
                while (Node != null && val == Node.val){
                    Node = Node.next;
                }
                //判断这时候上一个不重复节点是否为空，如果为空说明头节点也被删除了，这时候要重新赋予头节点值
                if (preNode == null){
                    pHead = Node;
                }else {
                    //若头节点未被删除，则指向当前节点
                    preNode.next = Node;
                }
            }
        }
        return pHead;

    }

    public static void main(String[] args) {
        Main18 main18 = new Main18();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        ListNode pHead = l1;

        main18.deleteDuplication(pHead);
    }
}
