package java.Algorithm.offer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.ArrayList;
/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */

/**
 * 从上到下打印二叉树
 * 用队列广度优先遍历
 */
public class Main32 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {

        LinkedBlockingQueue<TreeNode> q1 = new LinkedBlockingQueue<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        TreeNode cu = root;
        while(true){
            if(q1.isEmpty()){
                q1.add(cu);
            }else{
                if(cu.left != null){
                    q1.add(cu.left);
                }
                if(cu.right != null){
                    q1.add(cu.right);
                }
                list.add(q1.poll().val);
                if(q1.isEmpty()){
                    break;
                }else{
                    cu = q1.peek();
                }

            }
        }
        return list;
    }
}
