package java.Algorithm.sort;

/**
 * 重写归并排序
 */
public class MyMergeSort {
    public static void main(String[] args) {
        int[] arr = {13,4,56,12,678,34,43,23};
        MyMergeSort myMergeSort = new MyMergeSort();
        myMergeSort.mergeSort(arr);
        System.out.println(arr);
    }
    public void mergeSort(int[] arr){
        if (arr.length > 1){
            int[] leftArray = new int[arr.length / 2];
            int[] rightArray = new int[arr.length - leftArray.length];
            System.arraycopy(arr,0,leftArray,0,leftArray.length);
            System.arraycopy(arr,leftArray.length,rightArray,0,rightArray.length);
            mergeSort(leftArray);
            mergeSort(rightArray);
            merge(arr,leftArray,rightArray);
        }
    }

    public void merge(int[] arr, int[] leftArray, int[] rightArray){
        int curA = 0,curl = 0,curr = 0;
        while (curl < leftArray.length && curr < rightArray.length){
            if (leftArray[curl] < rightArray[curr]){
                arr[curA++] = leftArray[curl++];
            }else{
                arr[curA++] = rightArray[curr++];
            }
        }
        while (curl < leftArray.length){
            arr[curA++] = leftArray[curl++];
        }
        while (curr < rightArray.length){
            arr[curA++] = rightArray[curr++];
        }
    }
}
