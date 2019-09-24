package writtenexaminatio.duxiaoman;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++){
            long num = in.nextLong();
            long temp = num;
            int count = 0, count1 = 0;
            while (temp > 0){
                long lest = temp % 10;

                if (lest == 0 || (num%lest) == 0){
                    count++;
                }
                count1++;
                temp /= 10;
            }
            if (count == count1){
                System.out.println("G");
            }else if (count == 0){
                System.out.println("S");
            }else{
                System.out.println("H");
            }


        }
    }
}
