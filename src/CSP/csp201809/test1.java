package CSP.csp201809;

import java.util.Scanner;

public class test1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] vegetableArray = new int[n];
        for (int i = 0; i < vegetableArray.length; i++) {
            vegetableArray[i] = input.nextInt();
        }
        int[] priceNext = new int[n];
        for (int i = 0; i < priceNext.length; i++ ) {
            if (i == 0) {
                priceNext[i] = (vegetableArray[0] + vegetableArray[1]) / 2;
            }else if(i == priceNext.length - 1) {
                priceNext[priceNext.length - 1] = (vegetableArray[n - 1] + vegetableArray[n - 2]) / 2;
            }else {
                priceNext[i] = (vegetableArray[i - 1] + vegetableArray[i + 1] + vegetableArray[i]) / 3;
            }
            System.out.print(priceNext[i] + " ");
        }

    }
}
