package JUnitTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("学习JUnit")
public class TestJUnit {
    //定义待测试类的实例
    private TestClass testClass ;

    /**
     * 定义在整个测试类开始执行前的操作
     * 通过包括全局和外部资源的创建和初始化
     */
    @BeforeAll
    public static void init(){

    }

    /**
     * 定义在整个测试类执行完之后执行的操作
     * 通过包括全局和外部资源的释放和销毁（各种连接的注销,释放等）
     */
    @AfterAll
    public static void cleanup(){

    }

    /**
     * 在每个测试用例开始前执行的操作
     * 通过包括基础数据和运行环境的准备（给数据库添加数据测试等等）
     */
    @BeforeEach
    public void create(){

        this.testClass = new TestClass();
    }

    /**
     * 在每个测试用例完成后执行的操作
     * 通常包括运行环境的清理
     */
    @AfterEach
    public void destory(){

    }

    /**
     * 测试用例,测试类的求和功能
     */
    @Test
    @DisplayName("测试求和功能")
    public void getSum(){
        int a = 1;
        int b = 2;
        int c = testClass.getSum(a,b);
        assertEquals(3,c);
    }

    /**
     * 输入多组参数,重复测试
     * @param a
     * @param b
     * @param ans
     */
    @ParameterizedTest
    @DisplayName("测试重复求和功能")
    @CsvSource({
            "1, 2, 3",
            "1, 3, 3"
    })
    public void getSums(int a, int b, int ans){
        int c = testClass.getSum(a,b);
        assertEquals(ans,c);
    }

    /**
     * 禁用该测试用例,该测试用例不会被执行
     * 会出现在最终的报告中
     */
    @Disabled
    @Test
    @DisplayName("测试类名是否正确")
    public void getName(){
        assertTrue("TestClass".equals(testClass.getName()));
    }

    @Nested
    @DisplayName("表示属于TestJunit测试的子级测试（如：交易服务测试下的用户交易测试,一般建议不超过三层）")
    class NestTest{

        @DisplayName("嵌套测试")
        public void nestTest(){
            assertTrue(true);
        }
    }

    /**
     * 标注运行频率,
     * 可以使用maven添加插件，先执行所有为fast的，后执行slow的测试用例
     */
    @Test
    @Tag("fast")  //slow
    @DisplayName("返回True")
    public void getTrue(){
        assertTrue(testClass.getTrue());
    }

    @Test
    @DisplayName("测试流式断言")
    public void testStreamAssert(){

    }

}
