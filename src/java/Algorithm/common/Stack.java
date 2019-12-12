//package java.Algorithm.common;
//
//import java.util.LinkedList;
//
///**
// * 用两个队列实现一个栈
// * 思想：保持一个队列为空,push的时候只往一个队列里面放
// * pop的时候就把所有的元素往令一个队列里面放，使出栈的顺序是跟进来的顺序相反
// */
//public class Stack {
//
//    LinkedList<Integer> queue1 = new LinkedList<>();
//    LinkedList<Integer> queue2 = new LinkedList<>();
//
//    public void push(int node){
//        if (queue1.isEmpty()){
//            queue2.add(node);
//        }
//        if (queue2.isEmpty()){
//            queue1.add(node);
//        }
//    }
//
//    public Integer pop(){
//        if (queue2.size() == 0 && queue1.size() == 0){
//            try{
//                throw new Exception("stack is empty");
//            } catch(Exception e) {
//
//            }
//        }
//        if (queue1.isEmpty()){
//            while(queue2.size() > 0){
//                queue1.add(queue2.poll());
//            }
//            return queue1.pop();
//        }
//        if (queue2.isEmpty()){
//            while(queue1.size() > 0){
//                queue2.add(queue1.poll());
//            }
//            return queue2.pop();
//        }
//        return null;
//
//    }
//
//
//}
