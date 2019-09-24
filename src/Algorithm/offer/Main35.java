package Algorithm.offer;

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

/**
 * 复杂链表复制
 * 思想：
 * 1、使用哈希表，先存入对称的两组，然后再依次新建其随机联系
 * 2、画图理清思路，先把新建的节点插入原来的节点后面，这样新建的节点的随机节点也就在原来节点的随机节点的后一个
 * 即分三步
 *
 */
public class Main35 {
    public static RandomListNode Clone(RandomListNode pHead){
        if (pHead == null){
            return null;
        }
        //在N后面插入N`
        RandomListNode cu = pHead;
        while(cu != null){
            RandomListNode next = new RandomListNode(cu.label);
            next.next = cu.next;
            cu.next = next;
            cu = cu.next.next;
        }
        //更新random
        cu = pHead;
        while (cu != null){
            if (cu.random != null){
                RandomListNode next = cu.next;
                next.random = cu.random.next;
            }
            cu = cu.next.next;
        }
        //分裂成两个链表
        cu = pHead;
        RandomListNode nextHead = new RandomListNode(0);
        RandomListNode s = nextHead;

        while (cu != null){
            RandomListNode q = cu.next;
            cu.next = q.next;
            s.next = q;
            s = s.next;
            cu = q.next;
        }
        return nextHead;
    }

    public static void main(String[] args) {
        RandomListNode l1 = new RandomListNode(1);
        RandomListNode l2 = new RandomListNode(2);
        RandomListNode l3 = new RandomListNode(3);
        RandomListNode l4 = new RandomListNode(4);
        RandomListNode l5 = new RandomListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l1.random = l3;
        l2.random = l5;
        l4.random = l2;
        Clone(l1);

    }
}
