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
方法参数:

1. loader : 类加载器
2. interfaces : 目标对象实现的接口
3. h : InvocationHandler的实现类

以下是Proxy.java的newProxyInstance方法

```java
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

 以下是Proxy类的getProxyClass0方法, 生成代理类
 
```java
private static Class<?> getProxyClass0(ClassLoader loader,
                                           Class<?>... interfaces) {
        //接口数不能超过65535
        if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }
        //如果缓存中有代理类就直接返回,否则由ProxyClassFactory生成
        return proxyClassCache.get(loader, interfaces);
    }
```

以下是Proxy的内部类ProxyClassFactory,看看ProxyClassFactory是怎样生成的代理类
```java
/**
 * A factory function that generates, defines and returns the proxy class given
 * the ClassLoader and array of interfaces.
 */
private static final class ProxyClassFactory
    implements BiFunction<ClassLoader, Class<?>[], Class<?>>
{
    // 所有代理类的名称前缀为"$Proxy"
    private static final String proxyClassNamePrefix = "$Proxy";

    // 使用唯一的编号作为代理类名称的一部分
    private static final AtomicLong nextUniqueNumber = new AtomicLong();

    @Override
    public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {

        Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);
        for (Class<?> intf : interfaces) {
            /*
             * 验证指定类加载器加载的Class对象是否与intf加载的Class对象相同
             */
            Class<?> interfaceClass = null;
            try {
                interfaceClass = Class.forName(intf.getName(), false, loader);
            } catch (ClassNotFoundException e) {
            }
            if (interfaceClass != intf) {
                throw new IllegalArgumentException(
                    intf + " is not visible from class loader");
            }
            /*
             * 验证Class对象是否是一个接口
             */
            if (!interfaceClass.isInterface()) {
                throw new IllegalArgumentException(
                    interfaceClass.getName() + " is not an interface");
            }
            /*
             * 验证该接口是否重复
             */
            if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
                throw new IllegalArgumentException(
                    "repeated interface: " + interfaceClass.getName());
            }
        }

        String proxyPkg = null;     // 声明代理类所在包
        int accessFlags = Modifier.PUBLIC | Modifier.FINAL;

        /*
         * 验证传入的接口是否为非public的,保证非public接口的代理类将被定义在同一个包中
         */
        for (Class<?> intf : interfaces) {
            int flags = intf.getModifiers();
            if (!Modifier.isPublic(flags)) {
                accessFlags = Modifier.FINAL;
                String name = intf.getName();
                int n = name.lastIndexOf('.');
                String pkg = ((n == -1) ? "" : name.substring(0, n + 1)); //截取完整的包名
                if (proxyPkg == null) {
                    proxyPkg = pkg;
                } else if (!pkg.equals(proxyPkg)) {
                    throw new IllegalArgumentException(
                        "non-public interfaces from different packages");
                }
            }
        }
        
        if (proxyPkg == null) {
            // 如果没有非public接口,那么这些代理类都放在com.sun.proxy包中
            proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
        }

        /*
         * 将nextUniqueNumber通过cas加1,对proxy类名编号进行generate,初始为1
         */
        long num = nextUniqueNumber.getAndIncrement();
        String proxyName = proxyPkg + proxyClassNamePrefix + num;      //代理类的全限定名,如com.sun.proxy.$Proxy0.calss,

        /*
         * 生成代理类的字节码文件
         */
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
            proxyName, interfaces, accessFlags);
        try {
            return defineClass0(loader, proxyName,
                                proxyClassFile, 0, proxyClassFile.length);
        } catch (ClassFormatError e) {
            /*
             * A ClassFormatError here means that (barring bugs in the
             * proxy class generation code) there was some other
             * invalid aspect of the arguments supplied to the proxy
             * class creation (such as virtual machine limitations
             * exceeded).
             */
            throw new IllegalArgumentException(e.toString());
        }
    }
}

```
 
 再看一看是怎样生成字节码文件的呢?
 以下是sun.misc.ProxyGenerator的静态方法generateProxyClass
 ```java
public static byte[] generateProxyClass(final String var0, Class<?>[] var1, int var2) {
    ProxyGenerator var3 = new ProxyGenerator(var0, var1, var2);
    // 真正生成代理类字节码文件的方法
    final byte[] var4 = var3.generateClassFile();
    // 保存字节码文件
    if (saveGeneratedFiles) {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                try {
                    int var1 = var0.lastIndexOf(46);
                    Path var2;
                    if (var1 > 0) {
                        Path var3 = Paths.get(var0.substring(0, var1).replace('.', File.separatorChar));
                        Files.createDirectories(var3);
                        var2 = var3.resolve(var0.substring(var1 + 1, var0.length()) + ".class");
                    } else {
                        var2 = Paths.get(var0 + ".class");
                    }

                    Files.write(var2, var4, new OpenOption[0]);
                    return null;
                } catch (IOException var4x) {
                    throw new InternalError("I/O exception saving generated file: " + var4x);
                }
            }
        });
    }
    return var4;
}
```

以下是sun.misc.ProxyGenerator的内部静态方法,也是真正生成代理类字节码文件的方法

```java
private byte[] generateClassFile() {
    /* 将Object的三个方法,hashCode、equals、toString添加到代理类容器中
     * hashCodeMethod方法位于静态代码块中通过Object对象获得，
     * hashCodeMethod=Object.class.getMethod("hashCode",new Class[0]),
     * 相当于从Object中继承过来了这三个方法equalsMethod,toStringMethod
     */ 
    this.addProxyMethod(hashCodeMethod, Object.class);
    this.addProxyMethod(equalsMethod, Object.class);
    this.addProxyMethod(toStringMethod, Object.class);
    Class[] var1 = this.interfaces;
    int var2 = var1.length;

    int var3;
    Class var4;
    //将所有接口中的所有方法添加到代理方法中
    for(var3 = 0; var3 < var2; ++var3) {
        var4 = var1[var3];
        Method[] var5 = var4.getMethods();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Method var8 = var5[var7];
            this.addProxyMethod(var8, var4);
        }
    }

    Iterator var11 = this.proxyMethods.values().iterator();

    List var12;
    while(var11.hasNext()) {
        var12 = (List)var11.next();
        checkReturnTypes(var12);    //验证具有相同方法签名的的方法的返回值类型是否一致，因为不可能有两个方法名相同,参数相同，而返回值却不同的方法
    }
    //以下步骤是写代理类文件
    Iterator var15;
    try {
        this.methods.add(this.generateConstructor());    //生成代理类构造函数
        var11 = this.proxyMethods.values().iterator();

        while(var11.hasNext()) {
            var12 = (List)var11.next();
            var15 = var12.iterator();

            while(var15.hasNext()) {
                ProxyGenerator.ProxyMethod var16 = (ProxyGenerator.ProxyMethod)var15.next();
                /* 将代理字段声明为Method，10为ACC_PRIVATE和ACC_STATAIC的与运算，表示该字段的修饰符为private static
                 * 所以代理类的字段都是private static Method XXX
                 */
                this.fields.add(new ProxyGenerator.FieldInfo(var16.methodFieldName, "Ljava/lang/reflect/Method;", 10));
                //生成代理类的代理方法
                this.methods.add(var16.generateMethod());
            }
        }
        //为代理类生成静态代码块，对一些字段进行初始化
        this.methods.add(this.generateStaticInitializer());
    } catch (IOException var10) {
        throw new InternalError("unexpected I/O Exception", var10);
    }

    if (this.methods.size() > 65535) {    //代理方法数量超过65535将抛出异常
        throw new IllegalArgumentException("method limit exceeded");
    } else if (this.fields.size() > 65535) {    //代理类的字段数量超过65535将抛出异常
        throw new IllegalArgumentException("field limit exceeded");
    } else {    //从这里开始就是写代理类字节码文件啦
        this.cp.getClass(dotToSlash(this.className));
        this.cp.getClass("java/lang/reflect/Proxy");
        var1 = this.interfaces;
        var2 = var1.length;

        for(var3 = 0; var3 < var2; ++var3) {
            var4 = var1[var3];
            this.cp.getClass(dotToSlash(var4.getName()));
        }

        this.cp.setReadOnly();
        ByteArrayOutputStream var13 = new ByteArrayOutputStream();
        DataOutputStream var14 = new DataOutputStream(var13);

        try {
            var14.writeInt(-889275714);
            var14.writeShort(0);
            var14.writeShort(49);
            this.cp.write(var14);
            var14.writeShort(this.accessFlags);
            var14.writeShort(this.cp.getClass(dotToSlash(this.className)));
            var14.writeShort(this.cp.getClass("java/lang/reflect/Proxy"));
            var14.writeShort(this.interfaces.length);
            Class[] var17 = this.interfaces;
            int var18 = var17.length;

            for(int var19 = 0; var19 < var18; ++var19) {
                Class var22 = var17[var19];
                var14.writeShort(this.cp.getClass(dotToSlash(var22.getName())));
            }

            var14.writeShort(this.fields.size());
            var15 = this.fields.iterator();

            while(var15.hasNext()) {
                ProxyGenerator.FieldInfo var20 = (ProxyGenerator.FieldInfo)var15.next();
                var20.write(var14);
            }

            var14.writeShort(this.methods.size());
            var15 = this.methods.iterator();

            while(var15.hasNext()) {
                ProxyGenerator.MethodInfo var21 = (ProxyGenerator.MethodInfo)var15.next();
                var21.write(var14);
            }

            var14.writeShort(0);
            return var13.toByteArray();
        } catch (IOException var9) {
            throw new InternalError("unexpected I/O Exception", var9);
        }
    }
}
```

是怎样将Object方法添加到代理类容器的呢?下面我们看一看sun.misc.ProxyGenerator中的addProxyMethod方法
```java
private void addProxyMethod(Method var1, Class<?> var2) {
    String var3 = var1.getName();    // 方法名
    Class[] var4 = var1.getParameterTypes();    // 方法参数类型
    Class var5 = var1.getReturnType();    // 方法返回值类型
    Class[] var6 = var1.getExceptionTypes();    // 方法异常类型
    String var7 = var3 + getParameterDescriptors(var4);    // 方法签名
    Object var8 = (List)this.proxyMethods.get(var7);    //根据方法签名获得方法对象
    if (var8 != null) {    // 处理多个代理接口中的重复方法
        Iterator var9 = ((List)var8).iterator();

        while(var9.hasNext()) {
            ProxyGenerator.ProxyMethod var10 = (ProxyGenerator.ProxyMethod)var9.next();
            if (var5 == var10.returnType) {
                ArrayList var11 = new ArrayList();
                collectCompatibleTypes(var6, var10.exceptionTypes, var11);
                collectCompatibleTypes(var10.exceptionTypes, var6, var11);
                var10.exceptionTypes = new Class[var11.size()];
                var10.exceptionTypes = (Class[])var11.toArray(var10.exceptionTypes);
                return;
            }
        }
    } else {
        var8 = new ArrayList(3);
        this.proxyMethods.put(var7, var8);
    }

    ((List)var8).add(new ProxyGenerator.ProxyMethod(var3, var4, var5, var6, var2, null));
}
```
通过下面这个设置,可以将代理类字节码文件保存到项目根目录下的com.sun.proxy文件夹中看到$Proxy.class
`System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");`

下面我们看一看$Proxy0.class

```java
package com.sun.proxy;

import com.kristin.design_pattern.proxy.jdk_proxy.UserDao;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class $Proxy0 extends Proxy implements UserDao {
    private static Method m1;
    private static Method m2;
    private static Method m3;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final void add() throws  {
        try {
            /**
             * this:  当前生成的$Proxy0实例
             * m3: 就是我们定义的接口方法
             * (Object)null: 被传入的被调用的接口方法的实际参数
             * 
             * 可以看到实际上这个方法中的3个参数Object proxy, Method method, Object[] args就是$Proxy0通过h.invoke(this, m3, new Object[]{var1});传递给我们的，
             * 也就是说，我们实现的InvocationHandler的invoke()实际上是一个回调，也就是我们预先定义好的，然后JDK生成的类$Proxy0回过来调用的。
             */
            super.h.invoke(this, m3, (Object[])null);   //注意这里super.h其实就是InvocationHandler的对象h
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() throws  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m3 = Class.forName("com.kristin.design_pattern.proxy.jdk_proxy.UserDao").getMethod("add");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}

```
#### in all:
<p>JDK动态代理的基本原理就是 我们定义好接口和默认实现，JDK根据通过生成class文件的方式”动态”的生成一个代理类，这个代理类实现了我们定义的接口，并在接口实现方法中回调了我们通过InvocationHandler定义的处理流程，这个处理流程中我们回去调用默认实现，并提供增强。</p>

#### 不足
JDK动态代理只能代理实现了接口的类,而Cglib动态代理可以代理没有实现接口的类

#### JDK vs CGLIB
JDK动态代理和CGLIB字节码生成的区别？

 (1)JDK动态代理只能对实现了接口的类生成代理，而不能针对类

 (2)CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
   因为是继承，所以该类或方法最好不要声明成final

结论

1.同样情况下,cglib两种实现方式，invokeSuper + setSuperClass 永远比 invoke + setInterfaces慢

2.cglib invoke + setInterfaces 在方法数量较少的时候,在函数平均调用的情况下 比jdkProxy快,随着函数增多，优势越来越不明显,到达某个数量级一定比jdk动态代理慢

3.cglib invoke + setInterfaces 在调用特定函数(在switch中靠后的case) 会比jdk动态代理慢


思考

cglib的瓶颈在于:
调用net.sf.cglib.reflect.FastClass#invoke(int, java.lang.Object, java.lang.Object[])时需要switch的case:
如果有n个函数，那么就要一个个比较，复杂度O(n)
这里如果有一个key -> behavior的映射就好了，目前并没有。
如果可以用asm在这里写成一个二分搜索，cglib就会快多了,变成O(log2 n)，时间上就是一个飞跃，只不过这个fastclass就会看起来很丑。（目前最新3.2.5的版本也没有改动这一块）

参考:
 https://www.cnblogs.com/MOBIN/p/5597215.html

 https://since1986.github.io/blog/bf178159.html

 https://www.jianshu.com/p/1aaacf92e2cd
