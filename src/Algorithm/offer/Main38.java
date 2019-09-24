package Algorithm.offer;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 字符串排列
 * 思想: 把字符串分为两部分,第一个部分为一个字符串,第二部分为剩下的字符串
 *         第一步:把第一个字符和后面所有的字符交换
 *         第二步:固定第一个字符,求后面所有字符的排列,这时候仍把后面的所有字符分为两部分
 *         然后递归求解
 */
public class Main38 {
    ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> Permutation(String str) {
        if(str == null || str.length() == 0){
            return list;
        }
        char[] chars = str.toCharArray();
        TreeSet<String> set = new TreeSet<>();
        Permutation(chars,0, set);
        list.addAll(set);
        return list;

    }
    public void Permutation(char[] chars, int begin, TreeSet set){
        int l = chars.length;
        if(begin >= l){
            return;
        }
        if(begin == l-1){
            set.add(new String(chars));
        }else{
            for(int i = begin; i < l; i++){
                swap(chars,begin,i);
                Permutation(chars,begin+1,set);
                swap(chars,begin,i);
            }
        }
    }

    public void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}