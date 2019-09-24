package DesignerPattern.PrototypePattern;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
//        EnemyPlane enemyPlane = new EnemyPlane(100);
//        enemyPlane.setType("A");
////        enemyPlane.getBullet().setBulletName("ak");
//        EnemyPlane copyPlane = enemyPlane.clone();
//        System.out.println(enemyPlane);
//        System.out.println(copyPlane);
        EnemyPlane enemyPlane = EnemPlaneFactory.getInstance(100);
        EnemyPlane enemyPlane1 = EnemPlaneFactory.getInstance(100);
        System.out.println(enemyPlane);
        System.out.println(enemyPlane1);
    }
}
