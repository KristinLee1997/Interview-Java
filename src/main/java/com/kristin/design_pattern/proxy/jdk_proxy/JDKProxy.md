# JDK动态代理

## 1.定义

代理类在程序运行时被创建,并不是在Java代码中定义的，而是在运行时根据我们在Java代码中的“指示”动态生成的.


## 2.原理
Java 动态代理机制的出现，使得 Java 开发人员不用手工编写代理类，只要简单地指定一组接口及委托类对象，便能动态地获得代理类。
代理类会负责将所有的方法调用分派到委托对象上反射执行，在分派执行的过程中，开发人员还可以按需调整委托类对象及其功能，这是一套非常灵活有弹性的代理框架。在java的java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口，通过这个类和这个接口可以生成JDK动态代理类和动态代理对象。

## 3.应用
如果想对代理类的所有方法都加上日志,可以通过动态代理可以对代理类的所有方法进行统一的处理,而不用一一更改每一个方法. 

## 4. 实现原理

#### 4.1相关的类和接口
Class : java.lang.reflect.Proxy
Interface : java.lang.reflect.InvocationHandler

#### 4.2代理步骤
<p>1.Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)</p>
<p>loader : 类加载器</p>
<p>interfaces : 目标对象实现的接口</p>
<p>h : InvocationHandler的实现类</p>

以下是Proxy.java的newProxyInstance方法
```
@CallerSensitive
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
        throws IllegalArgumentException
    {
        Objects.requireNonNull(h); // 如果h为null,就会抛出空指针异常,所以一定要有InvocationHandler的实现类

        final Class<?>[] intfs = interfaces.clone();  //拷贝目标对象所实现的接口
        final SecurityManager sm = System.getSecurityManager(); //进行一些安全检查
        if (sm != null) {
            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
        }

        /*
         * 根据类加载器和接口生成代理类
         */
        Class<?> cl = getProxyClass0(loader, intfs);

        /*
         * Invoke its constructor with the designated invocation handler.
         */
        try {
            if (sm != null) {
                checkNewProxyPermission(Reflection.getCallerClass(), cl);
            }
            //获取代理类的构造函数对象
            //参数constructorParames为常量值：private static final Class<?>[] constructorParams = { InvocationHandler.class };
            final Constructor<?> cons = cl.getConstructor(constructorParams);
            final InvocationHandler ih = h;
            if (!Modifier.isPublic(cl.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        cons.setAccessible(true);
                        return null;
                    }
                });
            }
            //根据代理类的构造函数对象创建代理类对象
            return cons.newInstance(new Object[]{h});
        } catch (IllegalAccessException|InstantiationException e) {
            throw new InternalError(e.toString(), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new InternalError(t.toString(), t);
            }
        } catch (NoSuchMethodException e) {
            throw new InternalError(e.toString(), e);
        }
    }
```
 
 