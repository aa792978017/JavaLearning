package Java.zhujie.AOP;

import Java.zhujie.DI.DI1.SimpleInject;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;



public class CGLibContainer {

    //标识每个类的每个且点的方法列表
    private static Map<Class<?>, Map<InterceptPoint, List<Method>>> interceptMethodMap = new HashMap<>();

    //这是扫描的类，手动添加在这里
    static Class<?>[] aspects = new Class<?>[]{
            ServiceLogAspect.class, ExceptionAspect.class
    };

    static {
        //初始化CGLibContainer
        init();
    }

    /**
     * 初始化方法
     * 分析有@Aspect注解的类
     * 分析出每个类的方法在调用前/调用后/出现异常时应该调用哪些方法，
     * 在创建该类的对象时，如果有需要被调用的方法，则创建一个动态代理对象
     */
    private static void init() {

        for (Class<?> cls : aspects){
            //获取定义为切面的类
            Aspect aspect = cls.getAnnotation(Aspect.class);
            //获取切面类处理逻辑的新增方法
            if(aspect != null){
                Method before = getMethod(cls,"before", new Class<?>[]{
                        Object.class, Method.class,Object[].class
                });
                Method after = getMethod(cls, "after", new Class<?>[]{
                        Object.class, Method.class, Object[].class, Object.class
                });
                Method exception = getMethod(cls, "exception", new Class<?>[]{
                    Object.class, Method.class, Object[].class, Throwable.class
                });
                //获取所有要增强的类（被代理的类）
                Class<?>[] intercepttedArr = aspect.value();
                //把被代理类要添加的新方法列表存储到map里面
                for (Class<?> interceptted : intercepttedArr){
                    addInterceptMethod(interceptted, InterceptPoint.BEFORE, before);
                    addInterceptMethod(interceptted, InterceptPoint.AFTER, after);
                    addInterceptMethod(interceptted, InterceptPoint.EXCEPTION, exception);
                }

            }
        }
    }
    private static void addInterceptMethod(Class<?> cls, InterceptPoint point, Method method){
        //在该切点上，没有要新增的方法
        if (method == null){
            return;
        }
        //判断被代理类的方法是不是已经有一个对应的map在总散列表里
        Map<InterceptPoint, List<Method>> map = interceptMethodMap.get(cls);
        if (map == null){
            //如果没有，则新增一个空的方进去
            map = new HashMap<>();
            interceptMethodMap.put(cls, map);
        }

        //获取被代理类在该切点上的增强方法集合
        List<Method> methods = map.get(point);
        if (methods == null){
            //如果为空，则新增一个空方法列表
            methods = new ArrayList<>();
            map.put(point,methods);
        }
        //把该切面的该切点的增强方法加入进去
        methods.add(method);
    }

    private static <T> T createInstance(Class<T> cls) throws IllegalAccessException, InstantiationException {
        //如果cls不需要增强，则直接通过反射获取实例
        if (!interceptMethodMap.containsKey(cls)){
            return (T) cls.newInstance();
        }
        //如果需要增强，则使用cglib新建出增强类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        //callback是实现了对应cglib接口的方法拦截器
        enhancer.setCallback(new AspectInterceptor());
        return (T) enhancer.create();
    }
    private static Method getMethod(Class<?> cls, String name, Class<?>[] paramTypes) {
        try {
            return cls.getMethod(name, paramTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }


    static class AspectInterceptor implements MethodInterceptor {
        //拦截原始类的方法，并且植入要添加的逻辑
        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            //执行before
            List<Method> beforeMethods = getInterceptMethods(o.getClass().getSuperclass(),
                    InterceptPoint.BEFORE);
            for (Method m : beforeMethods){
                m.invoke(null, new Object[] {o,method,args});
            }
            try{
                //调用原来的方法
                Object result = proxy.invokeSuper(o, args);
                //执行after方法
                List<Method> afterMethods = getInterceptMethods(o.getClass().getSuperclass(),
                        InterceptPoint.AFTER);
                for (Method m : afterMethods){
                    m.invoke(null, new Object[]{o, method, args, result});
                }
                return result;
            }catch (Exception e){
                //执行exception方法
                List<Method> exceptionMethods = getInterceptMethods(o.getClass().getSuperclass(),
                        InterceptPoint.EXCEPTION);
                for (Method m : exceptionMethods){
                    m.invoke(null, new Object[]{o,method, args,e});
                }
                throw e;
            }


        }

        private static List<Method> getInterceptMethods(Class<?> cls,
                                                        InterceptPoint point) {
            Map<InterceptPoint, List<Method>> map = interceptMethodMap.get(cls);
            if (map == null){
                return Collections.emptyList();  //因为为空，就返回一个空值，用这个方法速度快，安全
            }
            List<Method> methods = map.get(point);
            if (methods == null){
                return Collections.emptyList();
            }
            return methods;
        }
    }


    public static <T> T getInstance(Class<T> cls){
        try{
            T obj = createInstance(cls);
            Field[] fields =  cls.getDeclaredFields();
            for (Field field : fields){
                if (field.isAnnotationPresent(SimpleInject.class)){
                    if (!field.isAccessible()){
                        field.setAccessible(true);
                    }
                    Class<?> fieldCls = field.getType();
                    field.set(obj, getInstance(fieldCls));
                }
            }
            return obj;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
