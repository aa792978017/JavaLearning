package java.Algorithm.offer;

/**
 * 最长不含重复字符的子字符串
 * 思想:动态规划,每次记录上一次出现字符的位置,这样计算是否包含该字符的时候不需要遍历一次,提高效率
 *
 */
public class Main48 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }else if(s.length() == 1){
            return 1;
        }
        int curL = 0;
        int maxL = 0;
        int len = s.length();
        int[] position = new int[255];
        //初始化字符出现位置,-1为没有出现过
        for(int i = 0; i < 255; i++){
            position[i] = -1;
        }
        //开始判断每个字符
        for(int i = 0; i < len ; i++){
            //该字符上次出现的位置
            int preIndex = position[s.charAt(i)];
            //如果字符未出现,或者出现了,但是距离上次出现的位置大于目前的长度,则加入字符

            if( preIndex== -1 || i - preIndex > curL){
                curL++;
            }else{
                //如果字符已经出现了,而且在目前扩展的长度内,则跟新长度
                if(curL > maxL){
                    maxL = curL;
                }
                curL = i - preIndex;
            }
            position[s.charAt(i)] = i;

        }
        //更新长度,有可能所有字符都是不重复的
        if(curL > maxL){
            maxL = curL;
        }
        return maxL;

    }

}
