package Algorithm.offer;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 栈的压入序列，判断压出序列是否正确
 */
public class Main31 {
    Queue<Integer> q1 = new LinkedBlockingQueue<>();


    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> s = new Stack<>();

        int i = 0;
        int j = 0;
        while(i < pushA.length){
            if(s.empty() || s.peek()!=popA[j]){
                s.push(pushA[i]);
                i++;
            }else{
                s.pop();
                j++;
            }
        }
        while(j<popA.length && popA[j]==s.peek()){
            if(j==popA.length-1){
                return true;
            }
            j++;
            s.pop();
        }
        return false;
    }
}
