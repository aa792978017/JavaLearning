package Algorithm.offer;
import java.util.Arrays;

/**
 * 扑克牌中的顺子
 * 考点:抽象建模,把扑克牌的顺子抽象成数组
 */
public class Main61 {
    public static boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length <= 0){
            return false;
        }
        //排序
        Arrays.sort(numbers);
        //计算零的个数
        int zero = 0;
        int i = 0;
        for(;i < 5 && numbers[i] == 0;i++){
            zero++;
        }
        for(;i < 4 && zero >= 0; i++){
            //有对子,错误
            if(numbers[i] == numbers[i+1]){
                return false;
            }
            if(numbers[i] + 1 + zero >= numbers[i+1]){
                zero -= numbers[i+1] - numbers[i] - 1;
            }else{
                return false;
            }


        }
        return true;

    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,6,4};
        System.out.println(isContinuous(arr));
    }
}