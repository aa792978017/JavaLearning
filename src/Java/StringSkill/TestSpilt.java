package Java.StringSkill;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 三种字符串分割的方式
 *
 */
public class TestSpilt {
    /**
     * 通过charAt()来自定义一个startWtih和endWith功能函数会比原生的快很多
     */
    /**
     * 判断orgStr是否以prefix开头
     * @param orgStr
     * @param prefix
     * @return
     */
    public static boolean startWith(String orgStr, String prefix){
        for (int i = 0; i < prefix.length(); i++){
            if (prefix.charAt(i) != orgStr.charAt(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断orgStr是否以suffix结尾
     * @param orgStr
     * @param suffix
     * @return
     */
    public static boolean endWith(String orgStr, String suffix){
        int orgLen = orgStr.length();
        int suffixLen = suffix.length();
        for (int i = 0; i < suffix.length(); i++){
            if (suffix.charAt(orgLen - i) != orgStr.charAt(suffixLen - i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 这是最快的方式
     * 通过charAt的快速确定+subString的空间换时间的方式
     * @param orgStr
     * @param delim
     * @return
     */
    public static String[] getSplit1(String orgStr, char delim){

        String tmp = orgStr;
        ArrayList<String> list = new ArrayList<String>();
        while(true){
            int j = tmp.charAt(delim);
            if (j < 0) break;
            else{
                list.add(new String(tmp.substring(0,j)));
                tmp = tmp.substring(j++);
            }

        }
        return (String[])list.toArray();

    }

    /**
     * jdk中专门用来处理字符串分割的子串工具，
     * 如果能用这个就不要用split（）了
     * @param orgStr
     * @param delim
     * @return
     */
    public static String[] getSplit2(String orgStr, char delim){
        StringTokenizer st = new StringTokenizer(orgStr, String.valueOf(delim));
        ArrayList<String> list = new ArrayList<String>();
        while (st.hasMoreTokens()){
            list.add(st.nextToken());
        }
        return (String[])list.toArray();
    }


}
