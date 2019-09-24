package Algorithm.offer;

/**
 * 二叉树的深度
 * 思想:递归遍历,
 */
public class Main55 {

        public int TreeDepth(TreeNode root) {
            if(root == null){
                return 0;
            }
            int left = TreeDepth(root.left);
            int right = TreeDepth(root.right);
            return (left>right)?(left+1):(right+1);
        }

}
