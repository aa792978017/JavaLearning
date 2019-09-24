package Algorithm.offer;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Queue;

/**
 * 分行从上到下打印二叉树
 * 思想：广度优先遍历树和图，都使用队列
 * 这里新建了两个变量用来记录每层剩余未打印的节点个数，来判断是否打印完该层节点
 */
public class Main32_2 {
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        //存放输出序列的lsit
        ArrayList<ArrayList<Integer>> list ;
        //如果根节点为null，返回
        if(pRoot == null){
            return new ArrayList<ArrayList<Integer>>(0);
        }
        //根节点不为空
        list = new ArrayList<>();
        Queue<TreeNode> qu = new LinkedBlockingQueue<>();
        qu.add(pRoot);
        int nextLevel = 0;
        int toBePrint = 1;
        ArrayList<Integer> cList = new ArrayList<Integer>();
        while(!qu.isEmpty()){
            //从队列取出节点
            TreeNode currentNode = qu.poll();
            //判断是否有左右孩子，加入队列，nextLevel++;
            if(currentNode.left!=null){
                qu.add(currentNode.left);
                nextLevel++;
            }
            if(currentNode.right!=null){
                qu.add(currentNode.right);
                nextLevel++;
            }
            //遍历
            cList.add(currentNode.val);
            //当前层剩余节点-1
            toBePrint--;
            //如果当前层已经没有节点了
            if(toBePrint == 0){
                //把这一层的所有元素放入总list
                list.add(cList);
                //新建下一层的cList
                cList = new ArrayList<Integer>();
                //更新toBePrint为下一层未遍历的元素
                toBePrint = nextLevel;
                //更新nextLevel为下下层节点的统计个数
                nextLevel = 0;
            }


        }
        return list;

    }

}
