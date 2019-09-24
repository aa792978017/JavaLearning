package Algorithm.offer;

/**
 * 二进制数中的1的个数
 * 考察对二进制的位运算
 */
public class Main15 {
    public int NumberOf1(int n) {
        int c = 0;
        //如果 n！=0
        while(n!=0){
            //n-1 & n 会把其最右边的1去掉
            n = (n-1)&n;
            c++;
        }
        return c;
    }
}
