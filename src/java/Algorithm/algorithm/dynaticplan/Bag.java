package java.Algorithm.algorithm.dynaticplan;

import java.util.Date;
import java.util.Random;

/**
 * 背包问题：可以取物品的一部分
 * 思路：贪心策略
 *
 */
public class Bag {
    static class Element implements Comparable<Element>{
        float w;
        float v;
        int index;
        float absV;

        Element(float w, float v, int index) {
            this.index = index;
            this.v = v;
            this.w = w;
            this.absV = v/w;
        }

        @Override
        public int compareTo(Element o) {
            if (this.absV >= o.absV)
                return 1;
            else {
                return 0;
            }
        }
    }

    /**
     * 冒泡排序对其按每份重量的价值降序排列
     * @param objects
     * @return
     */
    public static Element[] buble(Element[] objects) {
        for (int i = 0; i < objects.length; i++) {
            Element max = objects[i];
            Element temp = objects[i];
            int maxIndex = i;
            for (  int j = i + 1;j < objects.length; j++) {
                if ( max.compareTo(objects[j]) == 0) {
                    max = objects[j];
                    maxIndex = j;
                }
            }
            objects[i] = max;
            objects[maxIndex] = temp;
        }
        return objects;
    }

    /**
     * 使用贪心策略每次取最大的平均价值最高的份，最后判断剩下的重量是否为0不为零的时候
     * 继续取满返回最优解
     * @param c
     * @param w
     * @param v
     * @param x
     * @return
     */
    public static float knapsack(float c,float []w, float []v, float []x) {
        int n = v.length;
        Element[] d = new Element[n];
        for (int i = 0; i < d.length; i++){
            d[i] = new Element(w[i], v[i], i);
        }
         d = buble(d);
        float sum = 0;
        for (int i = 0; i < x.length; i++)
            x[i] = 0;
        int i;
        for ( i = 0; i < n; i++) {
            if (d[i].w > c)
                break;
            x[d[i].index] = 1;
            sum += d[i].v;
            c -= d[i].w;
        }
        if (c > 0) {
            x[d[i].index] = c / d[i].w;
            sum += x[d[i].index] * d[i].v;
        }

        return sum;
    }
    /**
     * 输入物品数量,和最大值,生成物品重量数组和价值数组
     * @param n
     */
    public static float[] makeData(int n, int maxValue) {
        float[] arr = new float[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = (float) random.nextInt(maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        float c = 80000;
        int n = 100000;
        float []w = makeData(n,50);
        float []v = makeData(n,30);
        float []x = new float[w.length];
        Date beginTime = new Date();
        float max = knapsack(c, w, v, x);
        Date endTime = new Date();
        System.out.println("物品数量为： " + n + "  背包容量为：" + c);
        System.out.println("该算法所用时间为： " + (int)(endTime.getTime() - beginTime.getTime()) + "毫秒");
        System.out.println("最优值为： " + max);
        System.out.println("选择的物品序列为： " );
        for (int i = 0; i < n; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println(max);
    }
}
