package java.Algorithm.offer;

/**
 * 数值的整数次方
 * 一般解法的时候要注意到正负指数，0的0此方等情况
 * 特殊解法：斐波那切模型，递归解决
 */
public class Main16 {
    public static double Power(double base, int exponent) {
        //先指数取正，如果是负数就最后再取反
        int n=Math.abs(exponent);
        //任何数的0次方为1
        if(exponent==0)
            return 1;
        //任何数的1次方为本身
        if(exponent==1)
            return base;
        //递归求解
        double  result=Power(base,n>>1);
        //最后指数为0或1,跳出来相乘
        result*=result;
        //如果为n为奇数则多乘一次自身
        if((n&1)==1)
            result*=base;
        //若为有负指数，则最后取反
        if(exponent<0)
            result=1/result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Power(2,-1));
    }


}
