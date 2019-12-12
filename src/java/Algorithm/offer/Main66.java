package java.Algorithm.offer;

/**
 * 构建乘积数组(不能用除法)
 * 思想:简单思想:通过连乘计算,算法复杂度为O(n方)
 *     高效算法:把数组B[i]的构建分成两部分,C[i] * D[i],保存部分值,有点像动态规划
 *              C[i] = C[i-1] * A[i-1]   自上而下求解
 *              D[i] = D[i+1] * A[i+1]   自下而上求解
 */
public class Main66 {
    public int[] multiply(int[] A) {
        if(A == null || A.length <= 0){
            return new int[0];
        }
        int len = A.length;
        int[] bArr = new int[len];
        int[] cArr = new int[len];
        int[] dArr = new int[len];
        cArr[0] = 1;
        dArr[len-1] = 1;
        for(int i = 1; i < len; i++){
            cArr[i] = cArr[i-1]*A[i-1];
        }
        for(int i = len-2; i>=0; i--){
            dArr[i] = dArr[i+1] * A[i+1];
        }
        for(int i = 0; i < len; i++){
            bArr[i] = cArr[i] * dArr[i];
        }
        return bArr;

    }
}