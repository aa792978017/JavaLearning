package Algorithm.offer;

/**
 * 青蛙跳台
 */
public class flog {

    //递归的方式解决
    public int JumpFloor(int target) {
        int n = target;
        if (n == 1 || n == 2) {
            return n;
        } else {
            return JumpFloor(n - 1) + JumpFloor(n - 2);
        }
    }

    //非递归的方式,减少重复计算的次数,记录每次的结果
    public int JumpFloor1(int target) {

        if(target == 0){
            return 0;
        }else if(target == 1){
            return 1;
        }else if(target == 2){
            return 2;
        }else {
            int[] ans = new int[target+2];
            ans[1] = 1;
            ans[2] = 2;
            for(int i = 3; i <= target; i++ ){
                ans[i] = ans[i-1] + ans[i-2];
            }
            return ans[target];
        }

    }


}

