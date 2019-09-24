package Algorithm.tree.binarytree.TestBST;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 二叉查找树
 * 这里使用范型实现,增加其可重用性
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    //树根
    public TreeNode<E> root;
    //定义大小
    public int size;
    //通过一个数组,构造二叉查找树
    public BST(E[] objects){
        for (int i = 0; i < objects.length; i++){
            insert(objects[i]);
        }
    }

    /**
     * 插入节点方法,用于构建二叉查找树
     * @param object
     * @return
     */
    public boolean insert(E object){
        //根节点为空的时候,把节点插入根节点
        if (root == null){
            root = new TreeNode<>(object);
        }else{
            //根节点不为空,先通过比较大小找到父亲的位置
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while(current != null){
                if (object.compareTo(current.element) < 0){
                    parent = current;
                    current = current.leftChild;
                }else if(object.compareTo(current.element) > 0){
                    parent = current;
                    current = current.rightChild;
                }else {
                    return false;
                }
            }
            //找到父亲位置后,判断与父亲的大小关系,插入左子节点或者右子节点
            if (object.compareTo(parent.element) < 0){
                parent.leftChild = new TreeNode<E>(object);
            }else{
                parent.rightChild = new TreeNode<>(object);
            }
        }
        //大小+1,返回答案
        size++;
        return true;
    }

    /**
     * 返回某元素的路径
     * @param object
     * @return
     */
    public ArrayList<TreeNode> path(E object){
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null){
            list.add(current);
            if (object.compareTo(current.element) < 0){
                current = current.leftChild;
            }else if(object.compareTo(current.element) > 0){
                current = current.rightChild;
            }else{
                break;
            }
        }
        return list;
    }

    /**
     * 删除某元素
     * 首先查找到该元素的位置,分类讨论如何拼接
     * 这里是通过判断左子树是否存在
     * 若存在,找到其左子树中没有右子树的节点
     * 然后替换要删除的节点位置,接着拼接子树
     * @param object
     * @return
     */
    public boolean delete(E object){
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        //首先要找到该元素是否存在树中
        while (current != null){
            if (object.compareTo(current.element) < 0){
                parent = current;
                current = current.leftChild;
            }else if(object.compareTo(current.element) > 0){
                parent = current;
                current = current.rightChild;
            }else {
                break;
            }
        }
        //如果不在树中,返回false
        if (current == null){
            return false;
        }
        //存在
        //分类讨论,
        //当该元素的左子树为空（这里找有子树也可以,下面跟着换就可以了）
        if (current.leftChild == null){
            //如果为根节点,其右子树变为根节点
            if (parent == null){
                root = current.rightChild;
            }else{
                //如果不是根节点,判断该元素是其父亲的左孩子还是右孩子,直接把其右孩子替代它原来的位置.
                if (object.compareTo(parent.element) < 0){
                    parent.leftChild = current.rightChild;
                }else{
                    parent.rightChild = current.rightChild;
                }
            }
        }else{
            //当前元素的左子树不为空
            //找到一个当前节点的孩子节点中的右子树为空的节点,进行拼接
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.leftChild;
            //如果右孩子不为空,继续找
            while (rightMost.rightChild != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.rightChild;
            }
            //换位置
            current.element = rightMost.element;
            //把找到的节点的左子树替换它的位置
            if (parentOfRightMost.rightChild == rightMost){
                parentOfRightMost.rightChild = rightMost.leftChild;
            }else{
                parentOfRightMost.leftChild = rightMost.leftChild;
            }
        }
        size--;
        return true;

    }

    /**
     * 判断该元素是否在二叉查找树内
     * @param object
     * @return
     */
    public boolean search(E object){
        TreeNode<E> current = root;
        while (current != null){
            if (object.compareTo(current.element) < 0){
                current = current.leftChild;
            }else if(object.compareTo(current.element) > 0){
                current = current.rightChild;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * 先序遍历
     * @param node
     */
    public void preOrder(TreeNode<E> node){
        if (node == null){
            return;
        }
        visit(node);
        preOrder(node.leftChild);
        preOrder(node.rightChild);

    }

    /**
     * 后序遍历
     * @param node
     */
    public void postOrder(TreeNode<E> node){
        if (node == null){
            return;
        }
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        visit(node);

    }

    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(TreeNode<E> node){
        if (node == null){
            return;
        }

        inOrder(node.leftChild);
        visit(node);
        inOrder(node.rightChild);

    }

    /**
     * 定义遍历方法
     * @param object
     */
    public void visit(TreeNode<E> object){
        System.out.print(object.element + " ");
    }




    public BST(){

    }

    //返回查尔搜索树中,元素个数
    public int getSize() {
        return size;
    }

    //定义节点信息
    class TreeNode<E extends Comparable<E>> {
        public E element;
        public TreeNode<E> leftChild;
        public TreeNode<E> rightChild;
        public TreeNode(E object){
            element = object;
        }
    }
    //测试代码
    public static void main(String args[]){
        //定义测试数据
        Integer[] list = {-23, -34, 1, 23, 0, 12, 2, -10, 53};
        //建立二叉排序树
        BST<Integer> bst = new BST<>(list);
        //先序遍历
        bst.inOrder(bst.root);
        System.out.println();
        //测试删除元素
        for (int i = 0; i < list.length; i++){
            bst.delete(list[i]);
            bst.inOrder(bst.root);
            System.out.println();
        }
    }

}
