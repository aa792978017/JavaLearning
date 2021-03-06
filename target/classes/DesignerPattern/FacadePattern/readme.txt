门面模式：  一个很好的封装模式

    定义：要求一个子系统的外部与内部的通信必须通过一个统一的对象进行。门面模式提供一个高层次的接口，使子系统更易于使用

    角色：
        1、门面角色：客户端调用该角色间接访问子系统，里面不包括具体的业务逻辑，只是对系统进行一个封装
        2、子系统角色：可以同时有一个或多个子系统。每个子系统都不是一个单独的类，而是一个类的集合。

    优点：
        1、减少系统的相互依赖：客户端现在只依赖门面对象，不依赖子系统
        2、提高了灵活性：无论子系统内部怎么变化，只要不影响到门面对象，随便改动。
        3、提高安全性：子系统不想开放的功能，只要不在门面对象加，外部无论怎么样都访问不了

    缺点：
        1、不符合开闭原则

    使用场景：
        1、为一个复杂的模块或者子系统提供一个供外界访问的接口
        2、子系统相对独立
        3、预防低水平技术人员带来的风险扩散:只允许其在子系统里面开发，然后提供门面进行访问

    注意事项：
        1、一个子系统可以有多个门面，如果一个门面里代码太长，可以分为两个或多个
        2、门面对象不参与子系统的业务逻辑，即：只应该单独的调用其的一个方法，不应该在一个方法里面有多个方法来实现其业务逻辑，
           否则出现依赖倒置的问题，子系统要依赖门面才能被访问