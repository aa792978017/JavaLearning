package DesignerPattern.PrototypePattern;

/**
 * 原型模式
 */
public class EnemyPlane implements Cloneable{
    private int x;
    private int y = 0;
    private String type = "A";
    private Bullet bullet  = new Bullet();

    public void fly(){
        System.out.println("位置：（"+ x + "," + y +"），机型 ："+ type);
    }

    public EnemyPlane(int x){
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public void setX(int x ){
        this.x = x;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    protected EnemyPlane clone() throws CloneNotSupportedException {
        //进行浅拷贝
        EnemyPlane enemyPlane = (EnemyPlane)super.clone();
        enemyPlane.setType(this.type);
        //进行深拷贝
        enemyPlane.setBullet((Bullet) this.bullet.clone());
        return enemyPlane;
    }

    @Override
    public String toString() {
        return "EnemyPlane{" +
                "x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                ", bullet=" + bullet +
                '}';
    }
}
