package Algorithm.offer;

import java.util.Stack;

/**
 * 两个栈实现一个队列
 * 比较简单
 */
public class Main9 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(!stack2.empty()){
            return stack2.pop();
        }else{
            if(stack1.empty()){
                return 0;
            }else {
                while(!stack1.empty()){
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        }
    }
}
