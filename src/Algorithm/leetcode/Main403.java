package Algorithm.leetcode;

/**
 * 青蛙跳台
 * 每次只能跳一步或两步，一共有多少种跳法
 */
public class Main403 {
    public static int num = 0;
    public static void jumpFunction(int n, int step){
        if (step == n){
            num++;
        }else if (step <= n-2){
            jumpFunction(n,step+1);
            jumpFunction(n,step+2);
        }else if (step <= n-1){
            jumpFunction(n,step+1);
        }

    }
    public static int jump(int n){
        if (n == 0){
            return 0;
        }else if (n == 1){
            return 1;
        }else {
            int total = 0;
            jumpFunction(n,0);
            return num;
        }
    }

    public static void main(String[] args) {
        System.out.println(jump(4));
    }
}
