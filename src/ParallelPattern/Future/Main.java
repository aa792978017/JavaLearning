package ParallelPattern.Future;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        //这里会立即返回，因为得到的是FutureData，而不是RealData
        Data data = client.request("name");
        System.out.println("请求完毕");
        try{
            //这里用一个sleep代替其他业务处理
            System.out.println("处理其他业务.....");
            Thread.sleep(2000);
        }catch (Exception e){

        }
        System.out.println("数据 = " + data.getResult());
    }
}
