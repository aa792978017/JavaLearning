package Algorithm.sort.HeapSortTest;

public class MyHeapSort {

    public static <E extends Comparable<E>> void heapSort(E[] list){
        MyHeap1<E>  heap = new MyHeap1<>(list);
        for (int i = 0; i < list.length; i++){
            list[i] = heap.remove();
        }
    }

    public static void main(String[] args) {
        Integer[] list = {-23, -34, 1, 23, 0, 12, 53, 2, 1, 23, -10};
        heapSort(list);
        for (int i = 0; i < list.length; i++){
            System.out.print(list[i] + "  ");
        }
    }
}
