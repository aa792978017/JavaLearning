package Algorithm.common;

import java.util.Stack;

/**
 * 用两个栈实现一个队列
 * 思想：第一个栈用于压入元素,输出的时候先把所有元素出栈到第二个栈,然后再返回栈二的第一个元素
 */
public class Queue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    // 出队列
    public int pop() {
        //先判断用于输出的栈二是否为空
        //若为空,把栈一所有元素压入栈2 此时元素顺序变为正常了,然后正常出栈就可以了
        if (stack2.empty()) {
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }

        if (stack2.empty()){
            try{
                throw new Exception("queue is empty");
            } catch(Exception e){

            }
        }

        int head = stack2.pop();
        return head;
    }
    //入队列
    public void push(int node){
        stack1.push(node);
    }

}
