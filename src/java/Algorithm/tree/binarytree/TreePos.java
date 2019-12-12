package java.Algorithm.tree.binarytree;

import java.util.Stack;

/**
 * 二叉树非递归后序遍历
 */
public class TreePos {

  public static void order(Node root){
    Node current = root;
    Stack<Node> stack = new Stack<>();
    while (current != null) {
      // 保证根节点--右节点--左节点的顺序入栈
        // 当前节点无左右子节点，则输出值
        if (current.left == null && current.right == null) {
          System.out.print(current.value + " ");
          current = stack.empty()?null:stack.pop(); // 如果stack为null，则返回null，不为null返回栈顶节点
          continue;
        }
        // 当前节点有子节点
        stack.push(current);

        if (current.right != null) {
          stack.push(current.right);
          current.right = null; // 入栈后清除引用
        }
        if (current.left != null) {
          stack.push(current.left);
          current.left = null; // 入栈后清除引用
        }
        // 更换当前遍历节点
        current = stack.empty()?null:stack.pop(); // 如果stack为null，则返回null，不为null返回栈顶节点

    }
  }


  public static void main(String[] args) {
    // 4 5 2 6 3 1
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node4.left = node6;
    order(node1);
  }
}
class Node {
  int value;
  Node left;
  Node right;
  Node (int value){
    this.value = value;
  }
}
