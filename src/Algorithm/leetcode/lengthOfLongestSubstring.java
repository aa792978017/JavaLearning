package Algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class lengthOfLongestSubstring {
    //使用滑动窗口解决
    public  static  int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<Character>();
        int i = 0, j = 0,ans = 0;
        while( j < n && i < n){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans,set.size());
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public  static  int lengthOfLongestSubstring(String s) {
        if (s.equals("")){
            return 0;
        }else if(s.equals(" ")){
            return 1;
        }
        Set<Character> maxCharSet = new HashSet<Character>();
        int maxIen = 0;
        int maxIndex = 0;

        for (int i = maxIndex; i < s.length();i++){
            char c = s.charAt(i);
            if (!maxCharSet.contains(c)){
                maxCharSet.add(c);
            }else{
                if (maxCharSet.size() > maxIen){
                    maxIen = maxCharSet.size();
                }
                maxIndex = i - maxCharSet.size();
                i = maxIndex;
                maxCharSet.clear();
            }
        }
        if(!maxCharSet.isEmpty() && maxCharSet.size() > maxIen){
            maxIen = maxCharSet.size();
        }
            return maxIen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

}

