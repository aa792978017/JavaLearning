package Algorithm.sort;

public class HeapSort {
    /**
     * 传入需要排序的数组.返回排序完成的数组
     * @param list
     * @param <E>
     */
    public static < E extends Comparable<E>> void heapSort(E[] list){
        Heap<E> heap = new Heap<>();
        //建立堆
        for (int i = 0; i < list.length; i++){
            heap.add(list[i]);
        }
        //把排序结果存到原来的数组中
        for (int i = 0; i < list.length; i++){
            list[i] = heap.remove();
        }
    }

    public static void main(String[] args) {
        //测试数组
        Integer[] list = {-23, -34, 1, 23, 0, 12, 53, 2, 1, 23, -10};
        //使用堆排序
        heapSort(list);
        //输出排序结果
        for (int i = 0; i < list.length; i++){
            System.out.print(list[i] + "  ");
        }
    }
}
