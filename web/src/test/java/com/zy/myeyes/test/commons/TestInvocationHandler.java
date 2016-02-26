package com.zy.myeyes.test.commons;

import javassist.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhougb on 2016/2/25.
 */
public class TestInvocationHandler {

    public static class MyTranslator implements Translator {

        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        }

        /* *
         * 类装载到JVM前进行代码织入
         */
        public void onLoad(ClassPool pool, String classname) {
            System.out.println("onLoad  classname:"+classname);
            if (!"com.zy.myeyes.test.commons.BussnesImpl".equals(classname)) {
                return;
            }
            //通过获取类文件
            try {
                CtClass cc = pool.get(classname);
                //获得指定方法名的方法
                CtMethod m = cc.getDeclaredMethod("doit");
                //在方法执行前插入代码
                m.insertBefore("{ System.out.println(\"记录日志\"); }");
            } catch (NotFoundException e) {
            } catch (CannotCompileException e) {
            }
        }

        public static void main(String[] args) {
            BussnesImpl b = new BussnesImpl();
            b.doit("javassist");
        }
    }

    public static void testJavassist() throws Exception{
        //获取存放CtClass的容器ClassPool
        ClassPool cp = ClassPool.getDefault();
        //创建一个类加载器
        Loader cl = new Loader();
        //增加一个转换器
        cl.addTranslator(cp, new MyTranslator());
        //启动MyTranslator的main函数
        try {
            cl.run("com.zy.myeyes.test.commons.TestInvocationHandler$MyTranslator", null);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static void testInvocationHandler(){
        final BussnesImpl bussnes = new BussnesImpl();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object ret = method.invoke(bussnes, args);
                System.out.println("do myself");
                return ret;
            }
        };

        Bussness bussnessProxy = (Bussness) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Bussness.class}, invocationHandler);
        bussnessProxy.doit("haha");
    }

    public static void testCGlib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BussnesImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object ret  = proxy.invokeSuper(target, args);
                System.out.println("do myself by CGlib");
                return ret;
            }
        });

        BussnesImpl a = (BussnesImpl) enhancer.create();
        a.doit("adasdf");
    }

    public static void main(String args[]) throws  Exception{
            ///testCGlib();

        testJavassist();
    }
}
