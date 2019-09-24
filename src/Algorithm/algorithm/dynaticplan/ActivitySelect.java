package Algorithm.algorithm.dynaticplan;

import java.util.Date;
import java.util.Random;

public class ActivitySelect {
    public static int greedySelector(int[] s,int []f, boolean []a) {
        int n = s.length - 1;
        a[1] = true;
        int j = 1;
        int count = 1;
        for (int i = 2; i <= n; i++) {
            if (s[i] >= f[j]) {
                a[i] =true;
                j = i;
                count++;
            }else {
                a[i] = false;
            }

        }
        return count;
    }

    public static void main(String[] args){
       // int[] s = {0, 3, 4, 5, 8, 10};
        //int[] f = {3, 5, 7, 9 ,13, 15};
        int activityNum = 1000000;   //定义活动数量
        int[] s = makeBeginTime(activityNum);   //生成排好序的活动开始时间和结束时间数组
        int[] f =makeEndTime(s);
        boolean[] a = new boolean[activityNum];
        System.out.println("活动数量为：" + activityNum);
        Date beginTime = new Date();
        greedySelector(s, f, a);
        Date endTime = new Date();
        System.out.println("算法所花时间：" + (int)(endTime.getTime() - beginTime.getTime())+"毫秒");
        System.out.println("活动选择情况为： ");
        for (boolean isget : a){    //输出活动选择情况,true为选择，false为不选择
            System.out.print(isget +" ");
        }

    }

    /**
     * 获取活动开始时间数组
     * 而且是以递增排好序的
     * @param n
     * @return
     */
    public static int[] makeBeginTime(int n) {
        int beginTime = 0;
        Random random = new Random();
        int[] activityBeginTime = new int[n];
        for (int i = 0;i < n; i++){
            activityBeginTime[i] = beginTime;
            beginTime += random.nextInt(8); //每个活动开始时间间隔20以内
        }
        return activityBeginTime;
    }

    /**
     * 获取活动停止时间数组
     * 以升序排好序,传入开始时间,保证开始时间小于结束时间
     * @param activieyBeginTime
     * @return
     */
    public static int[] makeEndTime(int[] activieyBeginTime){
        int[] endActivityTime = new int[activieyBeginTime.length];
        Random random = new Random();
        for (int i = 0; i < endActivityTime.length; i++){
            endActivityTime[i] = activieyBeginTime[i] + random.nextInt(20); //活动结束时间为20以内
        }
        return endActivityTime;
    }




}
