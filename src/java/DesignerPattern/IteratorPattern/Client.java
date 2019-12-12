package java.DesignerPattern.IteratorPattern;

public class Client {

    public static void main(String[] args) {
        IProject p = new Project();
        p.add("xx",10,100);
        p.add("yy",20,2345);
        p.add("zz", 32, 3567);
        IProjectIterator projectIterator = p.iterator();
        while (projectIterator.hasNext()){
            IProject project = (IProject) projectIterator.next();
            System.out.println(project.getProjectInfo());
        }
    }
}
