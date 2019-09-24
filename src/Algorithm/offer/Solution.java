package Algorithm.offer;

public class Solution {
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null || in == null){
            return null;
        }else if(pre.length == 1){
            int i = pre[0];
            return new TreeNode(i);
        }else{
            return getAns(pre,0, pre.length-1, in, 0, in.length-1);
        }
    }

    public static TreeNode getAns(int[] pre,int preB, int preE, int in[], int inB, int inE){
        if(preB > preE){
            return null;
        }else {
            int index = inB;
            while (pre[preB] != in[index] && index<=inE){
                index++;
            }
            if (index > inE){
                throw new RuntimeException(":");
            }
            TreeNode root = new TreeNode(pre[preB]);
            root.left = getAns(pre,preB+1,preB + index-inB,in,inB,index-1);
            root.right = getAns(pre,preB+index-inB+1,preE,in,index+1,inE);
            return root;
        }
    }

    public static void main(String[] args) {
//        int[] pre = {1,2,4,7,3,5,6,8};
//        int[] in = {4,7,2,1,5,3,8,6};
        int[] pre = {1,2,3,4,5,6,7};
        int[] in = {3,2,4,1,6,5,7};
        reConstructBinaryTree(pre,in);
    }
}
