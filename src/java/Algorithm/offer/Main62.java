package java.Algorithm.offer;

/**
 * 约瑟夫环问题
 * 思路:一般解法:形成环来解决,不够这种方式做了太多重复的求解
 *     特殊解法:根据数学关系找出递推公式:通过映射来找出每次剔除元素后新的映射顺序,以方便直接通过计算求出下一个要出去的数字
 *              | 0    n=1
 *     f(n,m) =
 *              | [f(n-1,m)+m]%n n>1
 *     实际算法自底向上求解即可
 */
public class Main62 {
    public int LastRemaining_Solution(int n, int m) {
        if( n < 1  || m < 1){
            return -1;
        }
        int last = 0;
        for(int i = 2; i <= n; i++){
            last = (last+m) % i;
        }
        return last;
    }
}