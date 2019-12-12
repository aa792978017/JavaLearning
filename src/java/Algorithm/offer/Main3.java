package java.Algorithm.offer;
public class Main3 {
    public static int Fibonacci(int n) {
        if(n == 0){
            return 0;
        }else if(n == 1){
            return 1;
        }else {
            int[] fm = new int[n+5];
            fm[0] = 0;
            fm[1] = 1;
            fm[2] = 1;
            fm[3] = 2;
            for(int i = 4; i <= n; i++){
                fm[i] = fm[i-1]+fm[i-2];
            }
            return fm[n];
        }

    }
    public static int Fibonacci1(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }else
        {
            return Fibonacci(n-1) + Fibonacci(n-2);
        }
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci(11));
    }

}
