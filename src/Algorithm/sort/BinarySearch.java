package Algorithm.sort;


/**
 * 二分搜索算法，通过比较顺序列表中间元素的值，迅速缩小规模查找元素，此处使用归并排序进行对乱序数组排序，节省时间·
 */
public class BinarySearch {
    /**
     * 二分搜索算法，传入一个排好序的数组和需要查找的元素，若存在，返回它的其中一个索引
     * @param array
     * @param element
     * @return
     */
    public static int BinarySearch(int[] array,int element,int left, int right){
        int mid = (right + left) >> 1;
        if (array[mid] == element) {
            return mid;
        }else{
            if (right - left > 1){
                if (array[mid] > element){
                   return BinarySearch(array,element,left,mid - 1);
                }else {
                  return   BinarySearch(array, element, mid + 1, right);
                }
            }else
                return -1;
        }
    }

    public static void main(String args[]){
//        Scanner input = new Scanner(System.in);
//        System.out.println("请分别输入要生成的随机数组的长度和元素最大值：");
//        int sortNum = input.nextInt();              //设定要随机生成数组的长度
//        int MaxNum = input.nextInt();                 //设定随机数组元素的最大值
//        System.out.println("请输入你要搜索的元素值：");
//        int element = input.nextInt();
//        int[] arr = Sort.initArray(sortNum, MaxNum); //调用生成随机数组的函数
//        MergeSort.mergeSort(arr);           //这里调用归并排序算法，对随机生成的数组进行排序
//        System.out.println("元素个数：" + sortNum);
//        Date beginDate = new Date();
//        System.out.println("搜索的元素值：" + element);
//        System.out.println("二分搜索开始时间：" + beginDate);
//        System.out.println("元素下标为：" + BinarySearch(arr, element,0,arr.length - 1));
//        Date endDate = new Date();
//        System.out.println("二分搜索结束时间：" + endDate);
//        long time = endDate.getTime() - beginDate.getTime();
//        System.out.println("算法所花时间：" + time +"毫秒");
        int[] arr = {1,12,15,23,30,50,81,99};
        int x =  BinarySearch1(arr,1);
        System.out.println(x);
    }

    /**
     * 非递归实现
     * @param array
     * @param element
     * @return
     */
    public static int BinarySearch1(int[] array, int element){
        if (array == null || array.length == 0){
            return -1;
        }
        int right = array.length - 1;
        int left = 0;
        int mid = (right + left) / 2;
        while (right >= left){
            if (array[mid] == element){
                return mid;
            }else if (array[mid] < element){
                left = mid;
                mid = (right + mid) / 2;
            }else if (array[mid] > element){
                right = mid - 1;
                mid = (right - left) / 2;
            }
        }
        return -1;
    }

}
