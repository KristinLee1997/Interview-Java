# Spring IOC 原理
手写Spring IOC demo : https://github.com/KristinLee1997/MyIOC
#### Spring容器核心类
1. BeanFactory
![BeanFactory](https://github.com/KristinLee1997/Interview-Java/raw/master/img/BeanFactory.png)
2. ApplicationContext
![ApplicationContext](https://github.com/KristinLee1997/Interview-Java/raw/master/img/ApplicationContext.png)

#### IOC步骤:
1. 通过ConfigReader读取及验证配置文件
2. 根据配置文件中的配置找到对应的类的配置,并通过ReflectionUtil进行实例化
3. 调用实例化后的实例

Spring存在4种基本的标签`<bean>,<import>,<alias>,<beans>`
对`<bean>` 的加载步骤:
1. 首先委托BeanDefinitionDelegate类的parseBeanDefinitionElement方法进行元素解析返回BeanDefinitionHolder类型的实例bdHolder,经过这个方法后,bdHolder实例已经包含我们配置文件中配置的各种属性了,例如class,name,id,alias之类的属性
    - 1.提取元素中的id及name属性;
    - 2.进一步解析其他所有属性并统一封装至GenericBeanDefinition类型的实例中;
    - 3.如果检测到bean没有指定beanName,那么使用默认的规则为此Bean生成beanName;
    - 4.将获取到的信息封装到BeanDefinitionHolder的实例中
2. 当返回的bdHolder不为空的情况下若存在默认标签的子节点下再有自定义属性,还需要再次对自定义标签进行解析
3. 解析完成后,需要对解析后的bdHolder进行注册,同样,注册操作委托给了BeanDefinitionReaderUtils的registerBeanDefinition方法
4. 最后发出响应事件,通知想关的监听器,这个bean已经加载完成了