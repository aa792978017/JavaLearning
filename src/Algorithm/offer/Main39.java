package Algorithm.offer;

/**
 * 寻找找过数组长度一半的元素
 * 第一遍遍历，寻找出出现次数为正的元素，记录每次出现元素的个数，如果下一个不等，则-1,count=0则重置
 * 结束后第二次遍历该是否出现次数大于数组长度的一半
 */
public class Main39 {
    public boolean check(int[] arr, int result){
        int l = arr.length;
        int count = 0;
        for(int i = 0; i < l; i++){
            if(arr[i] == result){
                count++;
            }
        }
        if(count * 2 > l){
            return true;
        }else{
            return false;
        }
    }
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int element = array[0];
        int count = 1;
        int index = 0;
        for(int i = 1; i < array.length;i++){
            if(count == 0){
                count = 1;
                index = i;
                element = array[i];
            }
            if(element == array[i]){
                count++;
            }else{
                count--;
            }
        }

        if(check(array,element)){
            return element;
        }
        return 0;
    }
}
