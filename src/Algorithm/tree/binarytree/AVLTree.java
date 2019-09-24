package Algorithm.tree.binarytree;



import java.util.ArrayList;

/**
 *  AVL树（平衡二叉树）
 */
public class AVLTree<E extends Comparable<E>> {
    private AVLTreeNode root;
    private int size = 0;
    public AVLTree(){}

    public AVLTree(E[] objects){
        for(int i = 0; i < objects.length; i++){
            boolean success = insert(objects[i]);
            //插入成功后,进入平衡阶段
            if (success){
                balancePath(objects[i]);
            }
        }
    }

    /**
     * 对新插入节点的路径上的节点进行平衡
     * @param e
     */
    private void balancePath(E e) {
        //获取到该元素的路径
        ArrayList<AVLTreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--){
            AVLTreeNode<E> A = path.get(i);
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (A == root) ? null:path.get(i-1);
            switch (balanceFactor(A)){
                case -2:
                    if (balanceFactor(A.getLeftChild()) <= 0){
                        balanceLL(A, parentOfA);
                    }else{
                        balanceLR(A, parentOfA);
                    }
                    break;
                case +2:
                    if (balanceFactor(A.getRightChild()) >= 0){
                        balanceRR(A, parentOfA);
                    }else{
                        balanceRL(A, parentOfA);
                    }
                    break;
            }
        }

    }

    /**
     * 进行LL旋转
     * @param a
     * @param parentOfA
     */
    private void balanceLL(AVLTreeNode<E> a, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> b = a.getLeftChild();
        if (a == root){
            root = b;
        }else {
            if (parentOfA.getLeftChild() == a){
                parentOfA.setLeftChild(b);
            }else {
                parentOfA.setRightChild(b);
            }
        }
        a.setLeftChild(b.getRightChild());
        b.setRightChild(a);
        //旋转完成,更新高度
        updateHeight(a);
        updateHeight(b);
    }

    /**
     * 先对B左旋,再对A右旋
     * @param a
     * @param parentOfA
     */
    private void balanceLR(AVLTreeNode<E> a, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> b = a.getLeftChild();
        AVLTreeNode<E> c = b.getRightChild();
        if (root == a){
            root = c;
        }else {
            if (parentOfA.getLeftChild() == a){
                parentOfA.setLeftChild(c);
            }else {
                parentOfA.setRightChild(c);
            }
            //旋转过程
            a.setLeftChild(c.getRightChild());
            b.setRightChild(c.getLeftChild());
            c.setRightChild(a);
            c.setLeftChild(b);
            //更新高度
            updateHeight(a);
            updateHeight(b);
            updateHeight(c);
        }

    }

    /**
     * 进行RR旋转
     * @param a
     * @param parentOfA
     */
    private void balanceRR(AVLTreeNode<E> a, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> b = a.getRightChild();
        if (a == root){
            root = b;
        }else {
            if (parentOfA.getRightChild() == a){
                parentOfA.setRightChild(b);
            }else {
                parentOfA.setLeftChild(b);
            }
        }
        a.setRightChild(b.getLeftChild());
        b.setLeftChild(a);
        updateHeight(a);
        updateHeight(b);
    }
    private void balanceRL(AVLTreeNode<E> a, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> b = a.getRightChild();
        AVLTreeNode<E> c = b.getLeftChild();
        if (a == root){
            root = c;
        } else {
            //跟新父亲节点和子节点的连接
            if (parentOfA.getLeftChild() == a){
                parentOfA.setLeftChild(c);
            }else {
                parentOfA.setRightChild(c);
            }
            a.setRightChild(c.getLeftChild());
            b.setLeftChild(c.getRightChild());
            c.setLeftChild(a);
            c.setRightChild(b);
            updateHeight(a);
            updateHeight(b);
            updateHeight(c);

        }

    }

    // 求该节点的平衡因子
    private int balanceFactor(AVLTreeNode<E> node) {
        if (node.getRightChild() == null){
            return -node.getHeight();
        }else if (node.getLeftChild() == null){
            return +node.getHeight();
        }else {
            return node.getRightChild().getHeight() - node.getLeftChild().getHeight();
        }
    }

    /**
     * 查找到该元素的路径
     * @param e
     * @return
     */
    private ArrayList<AVLTreeNode<E>> path(E e) {
        if (e != null && root!= null){
            ArrayList<AVLTreeNode<E>> pathlist = new ArrayList<>();
            AVLTreeNode current = root;
            while (current.getData() != e){
                pathlist.add(current);
                if (current.getData().compareTo(e) > 0){
                    current = current.getLeftChild();
                }else {
                    current = current.getRightChild();
                }
            }
            pathlist.add(current);
            return pathlist;

        }else{
            return null;
        }
    }

    /**
     * 更新节点的高度
     * @param node
     */
    public void updateHeight(AVLTreeNode<E> node){
        //如果是叶子节点,更新高度为0
        if (node.getLeftChild() == null && node.getRightChild() == null){
            node.setHeight(0);
        }else if(node.getLeftChild() == null){
            //左节点为空,说明右节点不为空,节点高度等于右节点的高度+1
            node.setHeight(1 + node.getRightChild().getHeight());
        }else if(node.getRightChild() == null){
            //右节点为空,说明左节点不为空,节点高度等于左节点高度+1
            node.setHeight(1 + node.getLeftChild().getHeight());
        }else{
            //左右节点都不为空,则取它的左右子节点最大的那个值
            node.setHeight(Math.max(node.getLeftChild().getHeight(),
                    node.getRightChild().getHeight()));
        }
    }


    private boolean insert(E e) {
        if(root == null){
            root = new AVLTreeNode(e);
        }else{
            //优先找到父亲节点
            AVLTreeNode current = root;
            AVLTreeNode parent = null;
            while (current != null){
                if (current.getData().compareTo(e) < 0){
                    parent = current;
                    current = current.leftChild;
                }else if(current.getData().compareTo(e) > 0){
                    parent = current;
                    current = current.rightChild;
                }else{
                    return false;
                }
            }
            if(parent.getData().compareTo(e) < 0){
                parent.setRightChild(new AVLTreeNode(e));
            }else{
                parent.setLeftChild(new AVLTreeNode(e));
            }
        }
        size++;
        return true;
    }
    public void delete(E e){
        if (root == null){
            return;
        }
        AVLTreeNode<E> parent = null;
        AVLTreeNode<E> current = root;
        while (current != null){
            if (current.getData().compareTo(e) > 0){
                parent = current;
                current = current.getLeftChild();
            }else if (current.getData().compareTo(e) < 0){
                parent = current;
                current = current.getRightChild();
            }else {
                break;
            }
        }
        if (current == null){
            return;
        }
        if (current.getLeftChild() == null){
            if (parent == null){
                root = current.getRightChild();
            } else {
                if (parent.getLeftChild() == current){
                    parent.setLeftChild(current.getRightChild());
                } else {
                   parent.setRightChild(current.getRightChild());
                }
                balancePath(parent.data);
            }
        }else {
          AVLTreeNode<E> parentOfRightMost = current;
          AVLTreeNode<E> rightMost = current.leftChild;
          while (rightMost.getRightChild() != null){
              parentOfRightMost = rightMost;
              rightMost = rightMost.getRightChild();
          }
          current.setData(rightMost.getData());
          if (current.getLeftChild() == rightMost){
              parentOfRightMost.setLeftChild(rightMost.getLeftChild());
          }else {
              parentOfRightMost.setRightChild(rightMost.getLeftChild());
          }
          balancePath(parentOfRightMost.getData());

        }
        size--;

    }


    /**
     * 定义avl树节点
     * @param <E>
     */
    protected static class AVLTreeNode<E extends Comparable<E>>{
        private E data;
        private AVLTreeNode leftChild;
        private AVLTreeNode rightChild;
        private int height = 0;

        public AVLTreeNode(E e){
            data = e;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public AVLTreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(AVLTreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public AVLTreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(AVLTreeNode rightChild) {
            this.rightChild = rightChild;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

}
