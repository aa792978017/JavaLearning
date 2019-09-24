package jvm.jvmtest;

/**
 * -Xss20M
 * 设置栈大小，
 * 这里没有什么效果
 */
public class JavaVMStackOOM {
    private void dontStop(){
        StringBuilder sb = new StringBuilder();
        sb.toString();
        while(true){

        }
    }

    public void stackleakByThread(){
        while (true){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    dontStop();
                }
            };
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        javaVMStackOOM.stackleakByThread();
    }
}
