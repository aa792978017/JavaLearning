package java.CSP.csp201712;

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i < array.length; i++)
            array[i] = 1;
        array[0] = 0;
        int first = 1;
        int end = n;
        int currentNum = 0;
        int childSum = end;
        int i = 1;
            for (;childSum != 1; i++) {
                if (array[i] == 0){
                    if (i == array.length -1)
                        i = 0;
                    continue;
                }
                currentNum++;
                if (currentNum >= 10){
                    if (k == currentNum % 10 || currentNum % k == 0){
                        array[i] = 0;
                        childSum--;
                    }
                } else {
                    if ( currentNum % k == 0 || currentNum == k) {
                        array[i] = 0;
                        childSum--;
                    }
                }
                if (i == array.length -1)
                    i = 0;
            }
            for (int j = 1; j < array.length; j++)
                if (array[j] == 1){ System.out.print(j); break;}



    }
}
