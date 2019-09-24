package DesignerPattern.CompositePattern;

import java.util.ArrayList;

public class Branch extends Corp {
    //下级领导和小兵
    public ArrayList<Corp> subordinateList = new ArrayList<>();

    public Branch(String name, String position, int salary) {
        super(name, position, salary);
    }
    //增加下属
    public void addSubordinate(Corp corp){
        this.subordinateList.add(corp);
    }
    //获取所有下属
    public ArrayList<Corp> getSubordinate(){
        return this.subordinateList;
    }
}
