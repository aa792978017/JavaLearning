package writtenexaminatio.duxiaoman;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        int n = sc.nextInt();
        for (int i=0;i < n;i++){
            long tmp = sc.nextLong();
            long a = tmp;
            int count=0,count2=0;
            while (a>0){
                long b = a%10;
                if (b!=0 && tmp%b == 0){
                    count++;
                }
                count2++;
                a /= 10;
            }
            if (count == count2){
                System.out.println("G");
            }else if (count == 0){
                System.out.println("S");
            }else{
                System.out.println("H");
            }
        }
    }
}