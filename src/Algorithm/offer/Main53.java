package Algorithm.offer;

/**
 * 在排序数组中查找数字
 * 思想:二分搜索改良:找出第一个k的下标和最后一个k的下标
 */
public class Main53 {
    public int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0){
            return 0;
        }
        int first = GetFirstK(array,k,0,array.length-1);
        int last = GetLastK(array,k,0,array.length-1);
        if(first > -1 && last > -1){
            return last - first + 1;
        }
        return 0;
    }

    public int GetFirstK(int[] array, int k, int begin, int end){
        if(begin > end){
            return -1;
        }
        int mid = (end + begin) / 2;
        int val = array[mid];
        if(val == k){
            if(mid > 0 && array[mid-1] == k){
                end = mid-1;
            }else{
                return mid;
            }
        }else if(val > k){
            end = mid - 1;
        }else {
            begin = mid + 1;
        }
        return GetFirstK(array,k,begin,end);

    }
    public int GetLastK(int[] array, int k, int begin, int end){
        if(begin > end){
            return -1;
        }
        int mid = (end + begin) >> 1;
        int val = array[mid];
        if(val == k){
            if(mid < array.length-1 && array[mid+1] == k){
                begin = mid+1;
            }else{
                return mid;
            }
        }else if(val > k){
            end = mid - 1;
        }else {
            begin = mid + 1;
        }
        return GetLastK(array,k,begin,end);

    }
}