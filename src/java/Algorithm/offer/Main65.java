package java.Algorithm.offer;

/**
 * 不用 - + * / 做加法
 * 思想:考虑位运算,加法的三步走仍旧对位运算适用:
 *
 */
public class Main65 {
    public int Add(int num1,int num2) {
        int sum , carry;
        do{
            sum = num1 ^ num2;      //第一步:各位相加但是不进位
            carry = (num1 & num2) << 1; //第二步:求出进位,左移
            num1 = sum;                 //第三步,如果进位不为0,循环
            num2 = carry;
        }while(num2!=0);
        return num1;
    }
}