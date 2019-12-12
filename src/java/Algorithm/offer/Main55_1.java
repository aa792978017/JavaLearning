package java.Algorithm.offer;

/**
 * 判断一棵树是否为平衡二叉树
 * 思想:1:正常的递归,求出每个节点左右子树的深度,直接判断,然后递归求每个节点是否都是正确的
 *     2:保存每个节点的递归:这样不用重复计算很多节点值,记录每节点深度,使用后序遍历
 */
public class Main55_1 {

    /**
     * 常规递归
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
            if(root == null){
                return true;
            }
            int left = TreeDepth(root.left);
            int right = TreeDepth(root.right);
            int dif = right-left;
            if(dif>1 || dif < -1){
                return false;
            }
            return IsBalanced_Solution(root.left)&&IsBalanced_Solution(root.right);
        }


        public int TreeDepth(TreeNode node){
            if(node == null){
                return 0;
            }
            int left = TreeDepth(node.left);
            int right = TreeDepth(node.right);
            return left>right?(1+left):(1+right);
        }

    /**
     * 后序遍历,记录节点的深度,只计算一次
      * @param root
     * @return
     */
    public boolean IsBalanced_Solution1(TreeNode root) {
        if(root == null){
            return true;
        }
        int[] depth = new int[1];
        return isBalanced(root,depth);

    }

    public boolean isBalanced(TreeNode node, int[] depth){
        if(node == null){
            depth[0] = 0;
            return true;
        }
        int[] left = new int[1]; //保存左子树的深度
        int[] right = new int[1]; //保存右子树的深度
        if(isBalanced(node.left,left) && isBalanced(node.right,right)){ //从叶子节点开始遍历,记录深度
            int dif = left[0] - right[0];  //
            if (dif <= 1 && dif >= -1){ //如果高度差在1之内,则返回正确
                depth[0] = 1 + ((left[0]>right[0]) ? left[0]:right[0] );
                return true;
            }
        }
        return false;
    }



}
