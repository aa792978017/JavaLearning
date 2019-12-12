package java.Algorithm.offer;

/**
 * 树的子结构
 */
public class Main26 {
    /**
     * 入口函数
     * 优先检测空指针等特殊情况
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root2 == null){
            return true;
        }
        if (root1 == null && root2 != null){
            return false;
        }
        //从1树根节点开始,对1树的节点进行先序遍历,遍历的过程即判断1树节点值是否跟2树的节点值相同
        //若相同，是否为子结构
        return preOrder(root1, root2);


    }

    /**
     * 先序遍历,只要有满足存在一种存子结构,立即返回真
     * @param root1
     * @param root2
     * @return
     */
    public boolean preOrder(TreeNode root1,TreeNode root2){
        if (root1 != null){
            if (preCheck(root1, root2)){
                return true;
            }
            if (preOrder(root1.left,root2)){
                return true;
            }
            if (preOrder(root1.right,root2)){
                return true;
            }

        }
        return false;

    }

    /**
     * 对1树的当前节点检查是否含2树的子结构
     * @param root1
     * @param root2
     * @return
     */
    public boolean preCheck(TreeNode root1,TreeNode root2){
       //2树的节点为空返回真
       if (root2 == null){
           return true;
           //1树和2树的当前节点都不为空,则判断它们的子树是否对应相等,
           // 这里递归判断,只要有一个子树不等,则马上返回false
       }else if (root2 != null && root1 != null && root1.val == root2.val){
           return preCheck(root1.left,root2.left) && preCheck(root1.right, root2.right);
       }
       //若为其他情况都是不相等或者2树不为空,1树为空;
       //因此都不满足树子结构,返回假
       return false;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(8);
        treeNode.left = new TreeNode(8);
        treeNode.right = new TreeNode(7);
        treeNode.left.left = new TreeNode(9);
        treeNode.left.right = new TreeNode(2);
        treeNode.left.right.left = new TreeNode(4);
        treeNode.left.right.right = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(8);
        treeNode2.left = new TreeNode(9);
        treeNode2.right = new TreeNode(2);
        Main26 main26 = new Main26();
        main26.HasSubtree(treeNode,treeNode2);
    }
}
