package writtenexaminatio.zhaoshang;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        BigDecimal w = new BigDecimal(in.nextInt());
        int boymin = Integer.MAX_VALUE;
        int girl = Integer.MAX_VALUE;
        int b,g = 0;
        for (int i = 0; i < n; i++){
             b = in.nextInt();
             if (b < boymin){
                 boymin= b;
             }
        }
        for (int i = 0; i < n; i++){
            g = in.nextInt();
            if (g < girl){
                girl = g;
            }
        }
        int v = g;
        BigDecimal bd  = new BigDecimal(5 * v * n);;
        while ( v >= 0){
            bd = new BigDecimal(5 * v * n);
            if(bd.compareTo(w) >=1){
                v--;
            }else{
                break;
            }
        }
        System.out.println(bd.toBigInteger());
    }




}
