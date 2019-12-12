package java.CSP.csp201712;

import java.util.Scanner;

public class test1 {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = input.nextInt();
        int min = -1;
        for (int i = 0; i < array.length - 1; i++){
            for (int j = i + 1; j < array.length; j++) {
                int x = array[i] - array[j];
                if (min == -1) {
                    min = Math.abs(x);
                }
                if (Math.abs(x) < min)
                    min = Math.abs(x);
            }
        }
        System.out.print(min);



    }
}
