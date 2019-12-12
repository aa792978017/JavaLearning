package java.Algorithm.offer;

import java.util.Stack;

/**
 *包含min函数的栈
 * 使用辅助空间的时候可以多使用一些
 * 保存多个最小值
 */
public class Main30 {

    Stack<Integer> data = new Stack<Integer>(); //存放元素栈
    Stack<Integer> min = new Stack<Integer>(); //辅助栈，data栈中每次进入一个元素，min辅助栈就存放当前data栈中的最小元素

    //data栈和min栈进入元素
    public void push(int item){
        data.push(item);
        if(min.size() == 0 || item < min.peek()){
            min.push(item);
        }else{
            min.push(min.peek());
        }
    }

    //data栈和min栈弹出元素
    public void pop(){
        if(data.size() > 0 && min.size() > 0){
            data.pop();
            min.pop();
        }
    }

    //data栈的栈顶元素
    public int top(){
        if(data.size() > 0){
            return data.peek();
        }
        return 0;
    }
    //min栈的栈顶元素，栈顶元素为data栈中现有元素的最小元素
    public int min(){
        if(data.size() > 0 && min.size() > 0){
            return min.peek();
        }
        return 0;
    }
}
