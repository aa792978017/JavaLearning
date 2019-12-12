package java.Algorithm.offer;

/**
 * 数字序列中的某一位的数字
 * 思想:不要一步一步求,分析规律,大范围减少数字,快速定位区间
 */
public class Main44 {
    public static int digitAtIndex(int index){
        if (index < 0){
            return -1;
        }
        int digits = 1;
        while (true){
            //返回该位数有多少个数字
            int number = countOfIntege(digits);
            //判断是否在该位数内
            if(number*digits>index){
                //如果在则进入求解
                return digitAtIndex(index,digits);
            }
            //如果不在则增加位数,求出在那个位数区间
            index -= number*digits;
            digits++;
        }

    }

    public static int digitAtIndex(int index, int digits) {
        //求出这个在位的完整数字是什么
        int number =  beginNumber(digits) + index/digits;
        //求出从右边往左数第几位
        int indexFromRight = digits - (index%digits);
        //循环除以10使得该位处于个位,
        for (int i = 1; i < indexFromRight; i++){
            number /= 10;
        }
        //取余数得答案
        return number%10;

    }

    public static int beginNumber(int digits) {
        return (int)Math.pow(10,digits-1);
    }


    public static int countOfIntege(int digits){
        if (digits == 1){
            return 10;
        }
        return (int) (9*Math.pow(10,digits-1));
    }

    public static void main(String[] args) {
        System.out.println(digitAtIndex(1001));
    }
}
