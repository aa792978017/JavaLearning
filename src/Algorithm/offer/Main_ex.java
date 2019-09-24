package Algorithm.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 滑动窗口最大值
 * https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13&tqId=11217&tPage=4&rp=2&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking
 */
public class Main_ex {
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> list = new ArrayList<>();
        if(num == null || size == 0 ||size > num.length){
            return list;
        }
        ArrayList<Integer> temp;
        for(int i = 0; i <= num.length - size; i++){
            temp = new ArrayList<>(size);
            for(int j = i; j < i+size; j++){
                temp.add(num[j]);
            }
            Collections.sort(temp);
            list.add(temp.get(temp.size() - 1));
        }
        return list;

    }


    /**
     * 题目：滑动窗口的最大值
     * 思路：滑动窗口应当是队列，但为了得到滑动窗口的最大值，队列序可以从两端删除元素，因此使用双端队列。
     *     原则：
     *     对新来的元素k，将其与双端队列中的元素相比较
     *     1）前面比k小的，直接移出队列（因为不再可能成为后面滑动窗口的最大值了!）,
     *     2）前面比k大的X，比较两者下标，判断X是否已不在窗口之内，不在了，直接移出队列
     *     队列的第一个元素是滑动窗口中的最大值
     */
    public ArrayList<Integer> maxInWindows1(int [] num, int size)
    {
        ArrayList<Integer> ret = new ArrayList<>();
        if (num == null) {
            return ret;
        }
        if (num.length < size || size < 1) {
            return ret;
        }

        LinkedList<Integer> indexDeque = new LinkedList<>();
        for (int i = 0; i < size - 1; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
        }

        for (int i = size - 1; i < num.length; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
            if (i - indexDeque.getFirst() + 1 > size) {
                indexDeque.removeFirst();
            }
            ret.add(num[indexDeque.getFirst()]);
        }
        return ret;
    }
}