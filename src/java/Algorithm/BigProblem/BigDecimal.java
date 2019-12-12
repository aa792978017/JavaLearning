package java.Algorithm.BigProblem;

import java.util.Scanner;

/**
 * 分治法
 */
public class BigDecimal {
    // 以字符串的方式输入两个超大的整数，将两个数值相乘的结果计算出来
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        String result = multiply(a, b);
        System.out.println(result);
    }


    // 计算两个整数相乘
    // 实现该方法，不需要对参数做数值校验
    private static String multiply(String a, String b) {
        //用来存储结果
        StringBuilder ans = new StringBuilder();
        //反转两个大整数,以便从个位开始相乘
        StringBuilder a1 = new StringBuilder(a);
        StringBuilder b1 = new StringBuilder(b);
        a1.reverse();
        b1.reverse();
        //用来存储中间计算的结果
        int[] arr = new int[a1.length() + b1.length()];
        //开始相乘,从两个大整数的个位开始,也就是反转后的第一位
        for(int i = 0; i < a1.length(); i++){
            //b的每一位与a的第i为乘积,每循环乘b每一位一次,a移动一位
            for(int j =0; j < b1.length();j++){
                //这里是关键!!!,这里构成了错位相加,进而保存了结果中每一位的累加和,这里数字的字符与‘0’字符相减等于数字本身
                arr[i+j] += (a1.charAt(i) - '0') * (b1.charAt(j) - '0');

            }
        }
        //给每一位大于十的进位,保留余数
        for(int i = 0; i < arr.length - 1; i++){
            arr[i+1] += arr[i] / 10;
            arr[i] %= 10;
        }
        //把结果放到答案集里面去
        for(int i = 0; i < arr.length - 1; i++){
            ans.append(arr[i]);
        }

        return ans.reverse().toString();
    }
}
