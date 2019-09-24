package Java.Threads;

public class Volatile {
    //场景1
    private  volatile boolean status = true;

    public void shutDown(){
        while (status){
            //do something
            System.out.println("状态正常,继续进行工作");
        }
    }

    /**
     * 修改状态,保证修改后,其他线程马上可见
     */
    public void setStatus(){
        status = false;
    }
    //---------------------------------------------------------------------------------------------



    //场景2
    private volatile int value;

    public int getValue(){
        return value;
    }

    public synchronized void setValue(int value){
        this.value = value;
    }



}
