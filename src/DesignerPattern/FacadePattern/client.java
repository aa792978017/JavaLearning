package DesignerPattern.FacadePattern;

public class client {
    public static void main(String[] args) {
        ModenPostOffice modenPostOffice = new ModenPostOffice();
        modenPostOffice.sendLetter("miss you ","xx省xx市");
    }
}
