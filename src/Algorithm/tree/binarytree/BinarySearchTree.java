package Algorithm.tree.binarytree;

/**
 * 二叉查找树
 * 构建思想：左子树值小于子树值，右子树值大于子树值，（树里面没有两个相同的元素），使用递归的方式
 * 遍历思想：递归
 */
public class BinarySearchTree {
    private BinaryTreeNode root;

    public void deleteTreeNode(int data){
        if (root.getData() == data){
            root = null;
            return;
        }
        BinaryTreeNode parentNode = searchParent(data);
        if (parentNode == null){
            return;
        }
        BinaryTreeNode node = search(root, data);
        if (node.getRightChild() == null && node.getLeftChild() == null){
            if (parentNode.getLeftChild() != null && parentNode.getLeftChild().getData() == data){
                parentNode.setLeftChild(null);
            }else{
                parentNode.setRightChild(null);
            }
        }else if (node.getLeftChild() != null && node.getRightChild() == null){
//            左子树不为空
            if (parentNode.getLeftChild() != null && parentNode.getLeftChild().getData() == data){
                parentNode.setLeftChild(node.getLeftChild());
            }else{
                parentNode.setRightChild(node.getLeftChild());
            }
        }else if(node.getRightChild() != null && node.getLeftChild() == null){
            //右子树不为空
            if (parentNode.getLeftChild() != null && parentNode.getLeftChild().getData() == data){
                parentNode.setLeftChild(node.getRightChild());
            }else{
                parentNode.setRightChild(node.getRightChild());
            }
        }else{
            //左右子树都不为空
            BinaryTreeNode deleteNode = node;
            //要删除的右子节点
            BinaryTreeNode temp = node.getRightChild();
            if (temp.getLeftChild() == null){
                temp.setLeftChild(deleteNode.getLeftChild());
            }else{
                while (temp.getLeftChild() != null){
                    node = temp;
                    temp = temp.getLeftChild();
                }
                node.setLeftChild(temp.getRightChild());
                temp.setLeftChild(deleteNode.getLeftChild());
                temp.setRightChild(deleteNode.getRightChild());
                if (parentNode.getLeftChild() != null && parentNode.getLeftChild().getData() == data){
                    parentNode.setLeftChild(temp);
                }else {
                    parentNode.setRightChild(temp);
                }
            }
        }
    }

    public BinaryTreeNode search(BinaryTreeNode node,int data){
        if (node == null){
            return null;
        }else if(node.getData() == data){
            return node;
        }else if (node.getData() > data){
            return search(node.getLeftChild(), data);
        }else{
            return search(node.getRightChild(), data);
        }
    }

    /**
     * 递归获取父亲节点
     * @param data
     * @return
     */
    public BinaryTreeNode searchParent(int data){
        return searchParent(null, root, data);
    }


    public BinaryTreeNode searchParent(BinaryTreeNode parentNode ,BinaryTreeNode node, int data){
        if (node == null){
            return null;
        }else if(node.getData() == data){
            return parentNode;
        }else if (node.getData() > data){
            return searchParent(node, node.getLeftChild(), data);
        }else {
            return searchParent(node, node.getRightChild(), data);
        }
    }

    /**
     * 插入节点
     * @param data
     */
    public void insert(int data){
        if (root == null){
            root = new BinaryTreeNode();
            root.setData(data);
        }else {
            searchAndInsert(null, root ,data);
        }
    }

    /**
     * 找到插入的位置，并且插入，如果值已经存在，则返回
     * @param parentNode
     * @param node
     * @param data
     * @return
     */
    public BinaryTreeNode searchAndInsert(BinaryTreeNode parentNode, BinaryTreeNode node, int data){
        if (node == null){
            node = new BinaryTreeNode();
            node.setData(data);
            if (data > parentNode.getData()){
                parentNode.setRightChild(node);
            }else {
                parentNode.setLeftChild(node);
            }
            return node;
        }else if (node.getData() == data){
            return node;
        }else if (data > node.getData()){
           return searchAndInsert(node, node.getRightChild(), data);
        }else{
            return searchAndInsert(node, node.getLeftChild(), data);
        }

    }


    /**
     * 先序遍历
     */
    public void preOrder(){
        if (root == null){
            return;
        }else{
            preOrder(root);
        }
    }
    public void preOrder(BinaryTreeNode node){
        if(node != null){
            visit(node);
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());

        }

    }

    /**
     * 中序遍历
     */
    public void inOrder(){
        if (root == null)
            return;
        else
            inOrder(root);
    }
    public void inOrder(BinaryTreeNode node){
        if(node != null){
            inOrder(node.getLeftChild());
            visit(node);
            inOrder(node.getRightChild());
        }


    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if (root == null)
            return;
        else
            postOrder(root);
    }
    public void postOrder(BinaryTreeNode node){
        if (node != null){
            postOrder(node.getLeftChild());
            postOrder(node.getRightChild());
            visit(node);
        }

    }

    /**
     * 遍历
     * @param node
     */
    public void visit(BinaryTreeNode node){
        if (node != null)
            System.out.println(node.getData());
    }


    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        int[] arr = {11,23,5,25,7, 95,13,43};
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < arr.length; i++){
            binarySearchTree.insert(arr[i]);
        }
//        binarySearchTree.inOrder();
        binarySearchTree.deleteTreeNode(95);
        binarySearchTree.preOrder();

    }


}
