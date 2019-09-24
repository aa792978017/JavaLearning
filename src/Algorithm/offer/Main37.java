package Algorithm.offer;


/**
 * 序列化二叉树
 * 思想:把树分为三部分,根,左右子树,然后分别序列化足有子树递归求解
 * 大问题分解为小问题
 */
public class Main37 {


    int index = -1;
    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null){
            return sb.append("$,").toString();
        }
        sb.append(root.val+",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();

    }
    TreeNode Deserialize(String str) {
        index++;
        String[] strs = str.split(",");
        TreeNode node = null;
        if (index >= str.length()){
            return null;
        }
        if(!strs[index].equals("#")){
            node = new TreeNode(Integer.valueOf(strs[index]));
            node.left =  Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }


}
