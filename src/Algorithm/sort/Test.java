package Algorithm.sort;

public class Test {
    public static void main(String[] args) {
        int[] arr = {4,23,56,12,37,87};
        MyHeap myHeap = new MyHeap(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.print(myHeap.remove() + " ");
        }
        mergeSort(arr);
        System.out.println(arr);

    }



    public static void mergeSort(int[] arr){
        if (arr.length > 1){
          int[] leftArr = new int[arr.length/2];
          int[] rightArr = new int[arr.length - leftArr.length];
          System.arraycopy(arr,0,leftArr,0,leftArr.length);
          System.arraycopy(arr,leftArr.length,rightArr,0,rightArr.length);
          merge(arr,leftArr,rightArr);
        }

    }
    public static void merge(int[] arr, int[] leftArr ,int[] rightArr){
        int c = 0;
        int l = 0;
        int r = 0;
        while (l < leftArr.length && r < rightArr.length){
            if (leftArr[l] < rightArr[r]){
                arr[c++] = leftArr[l++];
            }else{
                arr[c++] = rightArr[r++];
            }
        }
        while (l < leftArr.length){
            arr[c++] = leftArr[l++];
        }
        while (r < rightArr.length){
            arr[c++] = rightArr[r++];
        }

    }

    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);

    }

    public static void quickSort(int[] arr, int first, int end){
        if(first < end){
            int index = partiton(arr,first,end);
            quickSort(arr, first, index - 1);
            quickSort(arr, index+1, end);
        }

    }

    private static int partiton(int[] arr, int first, int end) {
        int high = end-1;
        int low = first;
        int e = arr[end];
        while (low < high){
            while (low < high && arr[high] >= e){
                high--;
            }
            while (low < high && arr[low] <= e){
                low++;
            }
            if (low < high){
                int temp = arr[high];
                arr[high] = arr[low];
                arr[low] = temp;
            }

        }
        while (low < end && arr[low] < e){
            low++;
        }
        if (low < end){
            arr[end] = arr[low];
            arr[low] = e;
            return low;
        }else{
            return end;
        }

    }


}
