package java.Algorithm.offer;

/**
 * 二叉搜索树的第k小节点
 * 思想:中序遍历的第k个节点就是了
 */
public class Main54{
    int tar = 0;
    TreeNode ans = null;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        tar = k;
        if(pRoot == null || k <= 0){
            return null;
        }
        KthNodeGet(pRoot);
        return ans;
    }

    void KthNodeGet(TreeNode node){
        if(node.left!=null && ans == null){
            KthNodeGet(node.left);
        }
        tar--;
        if(tar == 0 && ans == null){
            ans = node;
            return;
        }
        if(node.right!=null && ans == null){
            KthNodeGet(node.right);
        }


    }


}