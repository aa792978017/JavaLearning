package Algorithm.tree.binarytree;

public class BinaryTree {
    private BinaryTreeNode root;


    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    /**
     * 创建二叉树
     */
    public void createBinaryTree(){

    }

    /**
     * 插入节点
     * @param node
     */
    public void insertBinaryTreeNode(BinaryTreeNode node){
        if(root == null){
            this.root = node;
            return;
        }
        BinaryTreeNode currentNode = root;
        BinaryTreeNode parentNode = root;
        while(true){

            if (currentNode.getLeftChild() == null){
                currentNode.setLeftChild(node);
                break;
            }else if(currentNode.getRightChild() == null){
                currentNode.setRightChild(node);
                break;
            }else {
                if (parentNode.getLeftChild() == currentNode||root == currentNode){
                    currentNode = parentNode.getRightChild();
                }else{
                    currentNode = parentNode.getLeftChild().getLeftChild();
                    parentNode  = parentNode.getLeftChild();
                }
            }
        }

    }
}
