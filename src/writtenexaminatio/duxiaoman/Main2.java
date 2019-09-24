package writtenexaminatio.duxiaoman;

import java.util.Scanner;
import java.util.Stack;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr  = new int[n];
        for (int i = 0; i <n;i++){
            arr[i] = in.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        long res = 0, count = 0;
        for (int i =1; i < n;i++){
            if (arr[stack.peek()] > arr[i]){
                if (stack.size() < 2){
                    stack.clear();
                    stack.push(i);
                }else{
                    int res1 = arr[stack.peek()];
                    while (stack.size() > 1){
                        stack.pop();
                    }
                    int res2 = arr[stack.pop()];
                    stack.push(i);
                    if (res1 != res2){
                        res += (res1 - res2);
                        count++;
                    }
                }
            }else{
                stack.push(i);
            }
        }
        if (stack.size() < 2){
            stack.clear();
        }else {
            int res1 = arr[stack.peek()];
            while (stack.size() > 1){
                stack.pop();
            }
            int res2 = arr[stack.pop()];
            if (res1 != res2){
                res += (res1-res2);
                count++;
            }
        }
        System.out.println(res+" "+count*2);
    }
}
