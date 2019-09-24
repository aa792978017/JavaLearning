package Algorithm.offer;

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
 * 二叉搜索树与双向链表
 * 思想：递归
 * 难点：涉及大量指针的调整，要把问题指针调整顺序分析清除，然后递归求解
 */
public class Main36 {
    public static TreeNode Convert(TreeNode pRootOfTree) {
        TreeNode lastNode = null;
        lastNode = ConvertNode(pRootOfTree,lastNode);
        if(lastNode == null){
            return lastNode;
        }
        while(pRootOfTree!=null && lastNode.left != null){
            lastNode = lastNode.left;
        }
        return lastNode;
    }

    public static TreeNode ConvertNode(TreeNode root, TreeNode lastNode){
        if(root == null){
            return lastNode;
        }

        if(root.left != null){
            lastNode = ConvertNode(root.left,lastNode);
        }
        root.left = lastNode;
        if(lastNode != null){
            lastNode.right = root;
        }
        lastNode = root;
        if(root.right != null){
            lastNode = ConvertNode(root.right, lastNode);
        }
        return lastNode;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(14);
        TreeNode node3 = new TreeNode(4);
        TreeNode root4 = new TreeNode(8);
        TreeNode root5 = new TreeNode(12);
        TreeNode root6 = new TreeNode(16);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = root4;
        node2.left = root5;
        node2.right = root6;
        Convert(root);

    }

}