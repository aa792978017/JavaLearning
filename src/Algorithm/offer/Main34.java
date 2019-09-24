package Algorithm.offer;
import Algorithm.offer.TreeNode;

import java.util.ArrayList;

/**
 * 二叉树中和为某一直的路径
 * 递归思想
 */
public class Main34{

    public  ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    //存储路径
    public  ArrayList<Integer> cList = new ArrayList<>();

    public  ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if(root == null){
            return new ArrayList<>();
        }
        cList.add(root.val);
        target -= root.val;
        //已经到叶子节点，而且和为target，路径满足条件
        if (target == 0 && root.left == null && root.right == null){
            list.add(new ArrayList<>(cList));
        }
        FindPath(root.left,target);
        FindPath(root.right,target);
        //不满足的节点弹出cList
        cList.remove(cList.size()-1);
        //最后返回结果
        return list;



    }




}
