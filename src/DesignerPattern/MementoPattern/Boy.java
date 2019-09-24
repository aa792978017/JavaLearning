package DesignerPattern.MementoPattern;

public class Boy {

    private String state = "";

    public void changeState(){
        this.state = "心情可能不好...";
    }

    //创建备份
    public Memento createMemento(){
        return new Memento(this.state);
    }
    //恢复备份
    public void restoreMemento(Memento memento){
        this.setState(memento.getState());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
