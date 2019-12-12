//package java.CSP.csp201809;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class test2 {
//
//    static class TimeNode {
//        public int begin;
//        public int end;
//
//        TimeNode(){
//            this.begin = 0;
//            this.end = 0;
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        ArrayList<TimeNode> wTime = new ArrayList<>();
//        ArrayList<TimeNode> hTime = new ArrayList<>();
//
//        for (int i = 0; i < n; i++){
//            TimeNode timeNode = new TimeNode();
//            timeNode.begin = input.nextInt();
//            timeNode.end = input.nextInt();
//            hTime.add(timeNode);
//        }
//
//        for (int i = 0; i < n; i++){
//
//            TimeNode timeNode1 = new TimeNode();
//            timeNode1.begin = input.nextInt();
//            timeNode1.end = input.nextInt();
//            wTime.add(timeNode1);
//        }
//
//        int chatTime = 0;
//        for (TimeNode HNode:hTime){
//            for (TimeNode WNode: wTime){
//                if (HNode.begin >= WNode.begin && HNode.begin <= WNode.end && WNode.end <= HNode.end)
//                    chatTime += WNode.end - HNode.begin;
//                else if (HNode.begin <= WNode.begin && WNode.end <= HNode.end)
//                    chatTime += WNode.end - WNode.begin;
//                else if (HNode.begin <= WNode.begin && WNode.begin <= HNode.end && WNode.end >= HNode.end)
//                    chatTime += HNode.end - WNode.begin;
//                else if (WNode.begin < HNode.begin && WNode.end > HNode.end)
//                    chatTime += HNode.end - HNode.begin;
//            }
//        }
//
//        System.out.print(chatTime);
//    }
//}
