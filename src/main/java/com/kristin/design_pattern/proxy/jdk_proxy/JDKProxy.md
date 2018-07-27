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
 
 