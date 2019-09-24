package Algorithm.BigProblem;

import sun.nio.ch.sctp.SctpNet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        String m = in.next();
        String ans = getAns(n,m);
        System.out.println(ans);
    }
    public static String getAns(String n, String m){
        StringBuilder s1 = new StringBuilder(n);
        StringBuilder s2 = new StringBuilder(m);
        s1.reverse();
        s2.reverse();
        StringBuilder ans = new StringBuilder();
        int[] arr = new int[s1.length() + s2.length()];
        //计算每一位乘后的中间结果
        for (int i = 0; i < s1.length(); i++){
            for (int j = 0; j < s2.length(); j++){
                arr[i + j] += (s1.charAt(i) - '0') * (s2.charAt(j) - '0');
            }
        }
        //进行累加成每一位的真实值
        for (int i = 0; i < arr.length - 1; i++){
            arr[i+1] += arr[i] / 10;
            arr[i] = arr[i] % 10;
        }
        for (int i = 0; i < arr.length-1;i++){
            ans.append(arr[i]);
        }
        if (arr[arr.length-1] != 0){
            ans.append(arr[arr.length-1]);
        }
        return ans.reverse().toString();
    }

}
