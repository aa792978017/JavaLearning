package java.Algorithm.offer;

/**
 * 第一个只出现第一次的字符的下标
 * 思想:使用hash方式,hash函数为ASCII值
 */
public class Main50{
    public  int FirstNotRepeatingChar(String str) {
        if(str == null || str.length() <= 0){
            return -1;
        }else if (str.length() == 1){
            return 0;

        }

        int[] counts = new int[256];
        int[] indexs = new int[256];
        int l = str.length();
        for (int i = 0; i < l; i++){
            int x = str.charAt(i);
            counts[x]++;
            indexs[x]=i;

        }
        int index = 257;
        for (int i = 0; i < 256; i++){
            if (1==counts[i] && indexs[i] < index){
                index = indexs[i];
            }
        }

        return index;
    }


}