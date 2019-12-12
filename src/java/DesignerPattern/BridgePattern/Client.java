package java.DesignerPattern.BridgePattern;

public class Client {
    public static void main(String[] args) {
        House house = new House();
        Corp houseCorp = new HouseCorp(house);
        houseCorp.makeMoney();
        System.out.println("\n");
        System.out.println("山寨公司是这样赚钱的....");
        //这时候IPod火，造山寨IPod
        Corp shangZhaiCorp = new ShangZhaiCorp(new IPod());
        shangZhaiCorp.makeMoney();
        //这时候房子火，盖豆腐渣房子
        shangZhaiCorp = new ShangZhaiCorp(new House());
        shangZhaiCorp.makeMoney();
    }
}
