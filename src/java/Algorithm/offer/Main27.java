package java.Algorithm.offer;

/**
 * 二叉树的镜像
 */
public class Main27 {
    /**
     * 先判断空树的特殊情况
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null)
            return;
        //直接遍历即可,只要该节点不为空,交换左右子树
        //然后对左右子树递归
        order(root);

    }

    public void order(TreeNode node){
        //该节点不为null,交换左右子树,左右子树是null也没有关系,直接交换
        if (node != null){
            TreeNode temp = node.right;
            node.right = node.left;
            node.left = temp;
            order(node.left);
            order(node.right);
        }
    }
}
