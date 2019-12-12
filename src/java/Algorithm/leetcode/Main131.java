package java.Algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文字符串
 * 回溯法，
 */
public class Main131 {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if(s==null||s.length()==0)
            return res;
        dfs(s,new ArrayList<String>(),0);
        return res;
    }

    public void dfs(String s,List<String> remain,int left){
        if(left==s.length()){  //判断终止条件
            res.add(new ArrayList<String>(remain));  //添加到结果中
            return;
        }
        for(int right=left;right<s.length();right++){  //从left开始，依次判断left->right是不是回文串
            if(isPalindroom(s,left,right)){  //判断是否是回文串
                remain.add(s.substring(left,right+1));   //添加到当前回文串到list中
                dfs(s,remain,right+1);  //从right+1开始继续递归，寻找回文串
                remain.remove(remain.size()-1);  //回溯，从而寻找更长的回文串
            }
        }
    }
    /**
     * 判断是否是回文串
     */
    public boolean isPalindroom(String s,int left,int right){
        while(left<right&&s.charAt(left)==s.charAt(right)){
            left++;
            right--;
        }
        return left>=right;
    }

    public static void main(String[] args) {
        Main131 main131 = new Main131();
        main131.partition1("abbcc");
        System.out.println(main131.res);
    }

    public List<List<String>> partition1(String s){
        if (s == null || s.length() == 0){
            return res;
        }
        //使用回溯法解决
        dfs1(s,new ArrayList<String>(),0);
        return res;
    }

    /**
     * 使用回溯法去处理，深度优先
     * @param s
     * @param remain
     * @param left
     */
    public void dfs1(String s, ArrayList<String> remain, int left){
        if(left == s.length()){
            res.add(new ArrayList<String>(remain));
            return;
        }
        for (int right = left; right < s.length(); right++){
            if (isPalindroom1(s,left, right)){
                remain.add(s.substring(left, right));
                dfs1(s,remain, right+1);
                remain.remove(remain.size()-1);
            }
        }
    }

    /**
     * 判断是否为回文串
     * @param s
     * @param left
     * @param right
     * @return
     */
    public boolean isPalindroom1(String s, int left, int right){
        while (left < right && (s.charAt(left) == s.charAt(right))){
            left++;
            right--;
        }
        return left >= right;
    }



}
