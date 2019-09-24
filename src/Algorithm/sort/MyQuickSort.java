package Algorithm.sort;

public class MyQuickSort {

    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int begin, int last){
        if (begin < last){
            int part = partition(arr, begin, last);
            quickSort(arr,begin,part - 1);
            quickSort(arr,part+1,last);
        }
    }
    public static int partition(int[] arr, int begin, int last){
        int piovit = arr[begin];
        int low = begin+1;
        int high = last;
        while (low < high ){
            while (low < high && arr[low] <= piovit){
                low++;
            }
            while (low < high && arr[high] >= piovit){
                high--;
            }
            if (low < high){
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }else{
                break;
            }
        }
        while (high > begin){
            if (arr[high] < piovit){
                arr[begin] = arr[high];
                arr[high] = piovit;
            }
            high--;
        }
        if (arr[high] > arr[begin]){
            return high;
        }else{
            return begin;
        }


//        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,0};
        quickSort(arr);
        System.out.println(arr);

    }
}
