package java.Algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class twosum {
    /**
     * 我自己的实现，双指针
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] ans = null;
        int indexI = 0;
        while(true){
            for(int i = 0; i < nums.length; i++){
                if((nums[indexI]+nums[i]) == target){
                    ans = new int[2];
                    ans[0] = indexI;
                    ans[1] = i;
                    break;
                }
            }
            if (ans != null){
                break;
            }
            indexI++;

        }
        return ans;
    }

    /**
     * hashmap
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (map.containsKey(other) && map.get(other) != i) {
                return new int[]{i, map.get(other)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}
