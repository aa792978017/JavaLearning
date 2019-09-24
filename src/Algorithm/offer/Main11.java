package Algorithm.offer;

/**
 * 旋转数组的最小数字
 * 因为数组元素是升序排列的，根据其元素选择后的规律，可用二分法迅速缩小范围
 * 但是有特殊情况，在三个指针元素都相等的时候只能通过遍历查找
 */
public class Main11 {
    public int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int index1 = 0;
        int index2 = array.length-1;
        int indexMid = index1;
        while(array[index1] >= array[index2]){
            if(index2 - index1 == 1){
                indexMid = index2;
                break;
            }
            indexMid = (index1 + index2)/2;
            if(array[index1] == array[index2] && array[indexMid] == array[index1]){
                return MinInOrder(array,index1,index2);
            }
            if(array[index1] <= array[indexMid]){
                index1 = indexMid;
            }else if(array[index2] >= array[indexMid]){
                index2 = indexMid;
            }

        }
        return array[indexMid];


    }
    public int MinInOrder(int[] arr, int index1, int index2){
        int min = arr[index1];
        for(int i = index1+1;i<= index2; i++){
            if(min > arr[i]){
                min = arr[i];
            }
        }
        return min;
    }
}