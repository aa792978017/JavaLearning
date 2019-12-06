package Java.JUC;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 一个简单的数据库连接池
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    /**
     * 初始化连接池,自定义初始化连接数
     * @param initialSize 初始化连接数
     */
    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(null);
            }
        }
    }

}
