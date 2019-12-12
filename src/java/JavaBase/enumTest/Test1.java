package JavaBase.enumTest;


public class Test1 {
    public static void main(String[] args) {
        Box<String,String> box = new Box<>("StringBox", "字符串");
        System.out.println(box.getName() + " " + box.getType());

        //获取枚举变量
        Size smallSize = Size.SMALL;
        System.out.println(smallSize);

        //这里的name()是枚举类的默认父类Enum的方法,值为枚举元素的名称
        System.out.println(smallSize.name());

        //这里的ordinal()是枚举类的默认夫类Enum的方法,获取枚举类的默认值（按枚举的排列顺序从0开始）
        System.out.println(smallSize.ordinal());

        //遍历枚举类Size
        for (Size size : Size.values()){
            System.out.print(size + " : ");
            //获取具体枚举变量里面的信息
            System.out.print("英文缩写：" + size.getAbbr());
            //获取具体枚举变量里面的信息
            System.out.println(" 中文缩写：" + size.getTitle());
        }

    }
}
