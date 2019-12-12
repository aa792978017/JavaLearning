package java.DesignerPattern.IteratorPattern;
//抽象容器
public interface IProject {
    //添加项目
    public void add(String name, int num ,int cost);
    //获取项目信息
    public String getProjectInfo();
    //获得一个可以被遍历的对象
    public IProjectIterator iterator();
}
