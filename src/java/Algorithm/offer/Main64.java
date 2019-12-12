package java.Algorithm.offer;

/**
 * 求1+2+...+n,不用乘除,for,case,if,while,swtich
 * 思路:灵活运用位运算或者短路定理;构造函数不断new对象
 */
public class Main64 {
    public int Sum_Solution(int n) {
        int ans = n;
        boolean flag = (ans>0) && ((ans += Sum_Solution(n-1)) > 0);
        return ans;
    }
}