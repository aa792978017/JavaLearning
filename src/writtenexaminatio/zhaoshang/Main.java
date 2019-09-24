package writtenexaminatio.zhaoshang;

import java.util.Scanner;

public class Main {
    public static int total = 0;
    public static int[] fn;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        fn = new int[n+1];
        System.out.println(getMod(plan(n)));
    }
//
//    public static int ans(int lest){
//        if (lest <= 0){
//            return 0;
//        }
//        fn[1] = 1;
//        fn[2] = 2;
//        for (int i = 3; i < lest; i++){
//
//        }
//    }
    public static int plan(int n){
        if (n < 6){
            return 0;
        }
        else if(n==6){
            return 1;
        }else{
          int i = n;
          while (n >= 1 && i >= 6){
              plan(n-1, n - i);
              i--;
          }
        }
        return total;

    }
    public static void plan(int n, int cho){
        if (n >= 1 && cho >= 1){
            int partN = cho;
          while (n >= 1 && partN >= 1){
              plan(n-1,cho-partN);
              partN--;
          }
        }else {
            total++;

        }
    }


    public static int getMod(int plan){
        return plan % 666666666;
    }
}
