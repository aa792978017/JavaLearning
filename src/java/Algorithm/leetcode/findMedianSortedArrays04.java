package java.Algorithm.leetcode;

public class findMedianSortedArrays04 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int i = 0, j = 0;
        int n1 = nums1.length, n2 = nums2.length;
        int[] arr = new int[n1+n2];
        int arrIndex = 0;
        double ans = 0;
        while (i < n1 || j < n2){
            if (i < n1 && j < n2){
                if (nums1[i] < nums2[j]){
                    arr[arrIndex] = nums1[i++];

                }else {
                    arr[arrIndex] = nums2[j++];
                }
                arrIndex++;
            }else{
                while ( i < n1){
                    arr[arrIndex++] = nums1[i++];
                }
                while (j < n2){
                    arr[arrIndex++] = nums2[j++];
                }
            }

        }
        int middle = (n1+n2)>>1;
        if ((n1+n2)%2 == 1){
            ans = arr[middle];
        }else {
            ans = (double)(arr[middle-1] + arr[middle])/2;

        }
        return ans;

    }

    public static void main(String[] args) {
        int[] num1 = {1,2};
        int[] num2 = {3,4};
        System.out.println(findMedianSortedArrays(num1,num2));
    }
}
