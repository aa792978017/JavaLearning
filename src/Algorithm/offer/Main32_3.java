package Algorithm.offer;

import java.util.ArrayList;
import java.util.Stack;
/**
 * 之字形打印二叉树
 * 思想：基于两个栈解决，一改栈从左往右，一个栈从右往左，交替进行
 */
public class Main32_3 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        //存储遍历结果的list
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        //鲁棒性：如果根节点为null，则直接返回
        if(pRoot == null){
            return new ArrayList<ArrayList<Integer>>(0);
        }
        //新建两个栈，用于交替存储每层遍历的节点，存完一层就加入结果list
        Stack<TreeNode>[] stacks = new Stack[2];
        stacks[0] = new Stack<TreeNode>();
        stacks[1] = new Stack<TreeNode>();
        //两个指针，用于交替使用栈，当current栈存储节点的时候，next栈就进行遍历，两者同时为空时说明遍历完成
        int current = 0;
        int next = 1;
        //把第一层的根节点入站
        stacks[current].push(pRoot);
        //存储每层遍历结果
        ArrayList<Integer> cList = new ArrayList<>();
        //开始循环遍历
        while(!stacks[0].empty() || !stacks[1].empty()){
            //但前遍历栈弹出节点，存入遍历list
            TreeNode cuNode = stacks[current].pop();
            cList.add(cuNode.val);
            //如果是偶数，则从左往右存，存入奇数栈，这样出栈就是从右往左
            if(current == 0){
                if(cuNode.left != null){
                    stacks[next].push(cuNode.left);
                }
                if(cuNode.right != null){
                    stacks[next].push(cuNode.right);
                }
            }else{
                if(cuNode.right != null){
                    stacks[next].push(cuNode.right);
                }
                if(cuNode.left != null){
                    stacks[next].push(cuNode.left);
                }
            }
            //如果但前层的节点遍历完成，
            if(stacks[current].empty()){
                //则把当前层的遍历cList存入答案list
                list.add(cList);
                //新建下一层的遍历cList
                cList = new ArrayList<>();
                //交换两个栈，开始变量下一层的节点栈
                current = 1 - current;
                next = 1 - next;
            }
        }
        return list;
    }

}
