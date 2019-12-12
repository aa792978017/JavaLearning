package java.Algorithm.sort;

/**
 * 插入排序
 */
public class InsertSort {

    /**
     * 常规的插入排序
     * 时间复杂度为O(n方)
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr){
        int len = arr.length;
        int[] insertArr = new int[len];
        for(int i = 0; i < len; i++){
            int current = arr[i];
            int k;
            for (k = i - 1; k>=0 && insertArr[k] > current; k--){
                insertArr[k+1] = insertArr[k];
            }
            insertArr[k+1] = current;
        }
        return insertArr;
    }

    /**
     * 用二分排序优化插入排序,快速定位要插入的位置,减少比较次数
     * 在数组元素数据很大的时候效率提升比较明显,数据量少的时候速度提升不明显
     * @param arr
     * @return
     */
    public static int[] binaryInsertSort(int[] arr){
        int len = arr.length;
        int[] insertArr = new int[len];
        for(int i = 0; i < len; i++){
            int current = arr[i];
            int k = binarySearch(insertArr, current, 0, i-1);
            for (int j = i; j > k; j--){
                insertArr[j] = insertArr[j-1];
            }
            insertArr[k] = current;
        }
        return insertArr;
    }

    /**
     * 二分搜索改版,查找要插入的位置
     * @param arr
     * @param element
     * @param begin
     * @param end
     * @return
     */
    public static int binarySearch(int[] arr, int element, int begin, int end){
        if (begin == 0 && end <= 0){
            return 0;
        }
        int mid = (begin + end) /2;
        while(begin < end){

            if (arr[mid] == element){
                return mid+1;
            }else if (arr[mid] > element){
                if (begin == end -1){
                    return begin;
                }
                end = mid;
                mid = (begin+end)/2;
            }else{
                if (begin == end -1 && arr[end] < element){
                    return end+1;
                }else if (begin == end -1 && arr[end] > element){
                    return end;
                }

                begin = mid;
                mid = (end + begin)/2;
            }
        }
        return mid+1;
    }

    public static void main(String[] args) {
        int[] arr = {34,13,56,2,41,61,2,13,42,1,12};
        int[] arr1 = binaryInsertSort(arr);
        System.out.println(arr1);
    }
}
