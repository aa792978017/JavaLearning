package Java.enumTest;

/**
 * 定义尺码枚举类型
 */
public enum Size {
    XSMALL("XS","小小码"),
    SMALL("S","小码"),
    MEDIUM("M","中码"),
    LARGE("L","大码"),
    XLARGE("XL","加大码");

    private String abbr; // 对应XS等代号
    private String title; //对应小码等中文代号

    //构造方法,给加上枚举类加上英文缩写代号和中文代号
    private Size(String abbr, String title) {
        this.abbr = abbr;
        this.title = title;
    }

    /**
     * 通过XS等代号找对应的枚举码数
     *
     * @param abbr
     * @return
     */
    public static Size fromAbbr(String abbr){
        for (Size size : Size.values()){     //Size.values() 返回一个Size的所有枚举类型数组
            if (size.getAbbr().equals(abbr)){
                return size;                 //遍历找到对于的枚举类型,加入abbr为S,遍历到SMALL就会返回
            }
        }
        return null;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getTitle() {
        return title;
    }
}

