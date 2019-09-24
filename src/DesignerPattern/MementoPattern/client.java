package DesignerPattern.MementoPattern;

public class client {
    public static void main(String[] args) {
        //声明主角
        Boy boy = new Boy();
        //声明备忘录管理者
        Caretaker caretaker = new Caretaker();
        boy.setState("心情很棒！");
        System.out.println("====男孩现在的状态====");
        System.out.println(boy.getState());
        //记录下目前的状态
        caretaker.setMemento(boy.createMemento());
        //男孩取追女孩，状态改变
        boy.changeState();
        System.out.println("====男孩追女孩后的状态====");
        System.out.println(boy.getState());
        //追女孩失败，恢复原状
        boy.restoreMemento(caretaker.getMemento());
        System.out.println("====男孩恢复后的状态");
        System.out.println(boy.getState());


    }
}
