组合模式：

    定义：将对象组成树形结构以表示”部分-整体“的层次结构，使得单个对象和组合对象的使用具有一致性

    角色：
        1、抽象构件：定义参加组合对象的公有属性和方法，定义默认行为和属性
        2、叶子构件：叶子对象，遍历的最小单位
        3、树枝构件：树枝对象，作用是组合树枝节点和叶子节点形成一个树形结构

    优点：
        1、高层模块调用简单：每个节点都是Component，局部和整体对调用者来说没有任何区别
        2、节点自由增加：只要找到了父亲节点，就可以扩展了

    缺点：
        1、违背依赖倒置原则

    使用场景：
        1、维和和展示部分与整体的场景，如树形菜单，文件和文件管理
        2、从一个整体中能独立出部分模块

    扩展：透明模式

