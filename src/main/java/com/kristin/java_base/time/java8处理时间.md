## Java8 时间类库使用

英文：http://javarevisited.blogspot.sg/2015/03/20-examples-of-date-and-time-api-from-Java8.html

本文根据上述资料进行整理修改说明

 

java8引入了一套全新的时间日期API，本篇随笔将说明学习java8的这套API。

java。time包中的是类是不可变且线程安全的。新的时间及日期API位于java.time中，下面是一些关键类

●Instant——它代表的是时间戳

●LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。

●LocalTime——它代表的是不含日期的时间

●LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。

●ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。

### java8是如何处理时间及日期的

**1、如何在java8中获取当天的日期**

java8中有个叫LocalDate的类，能用来表示今天的日期。这个类与java.util.Date略有不同，因为它只包含日期，没有时间。

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160411142322957-99216045.png)

```
可以看到，他创建了今天的日期却不包含时间信息，并且格式化了日期。
```

 

 

**2、如何在java8中获取当前的年月日**

LocalDate类中提供了一些很方便的方法可以用来提取年月日以及其他的日期属性,特别方便，只需要使用对应的getter方法就可以了，非常直观

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160411151946551-1409956695.png)

 

**3、在java8中如何获取某个特定的日期**

通过另一个方法，可以创建出任意一个日期，它接受年月日的参数，然后返回一个等价的LocalDate实例。在这个方法里，需要的日期你填写什么就是什么，不想之前的API中月份必须从0开始

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160411152621660-2048695910.png)

 

**4、在java8中检查两个日期是否相等**

LocalDate重写了equals方法来进行日期的比较，如下所示：

 ![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160413140207785-181310540.png)

 

**5、在java8中如何检查重复事件，比如生日**

在java中还有一个与时间日期相关的任务就是检查重复事件，比如每月的账单日

如何在java中判断是否是某个节日或者重复事件，使用MonthDay类。这个类由月日组合，不包含年信息，可以用来代表每年重复出现的一些日期或其他组合。他和新的日期库中的其他类一样也都是不可变且线程安全的，并且它还是一个值类（value class）。

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160413141204801-1283498547.png)zzda

通过列子可以看到MonthDay只存储了月日，对比两个日期的月日即可知道是否重复

 

**6、如何在java8中获取当前时间**

这个与第一个例子获取当前日期非常相似，这里用的是LocalTime类，默认的格式是hh:mm:ss:nnn

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160413141614566-1039173597.png)

可以看到，这个时间是不包含日期的

 

**7、如何增加时间里面的小时数**

很多时候需要对时间进行操作，比如加一个小时来计算之后的时间，java8提供了更方便的方法 如plusHours，这些方法返回的是一个新的LocalTime实例的引用，因为LocalTime是不可变的

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160413144311863-407474060.png)

 

**8、如何获取1周后的日期**

这个与前一个获取2小时后的时间的例子很相似，这里我们获取的是1周后的日期。LocalDate是用来表示无时间的日期，他又一个plus()方法可以用来增加日，星期，月，ChronoUnit则用来表示时间单位，LocalDate也是不可变的，因此任何修改操作都会返回一个新的实例

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160413145554645-460234743.png)

可以看到一周后的日期是什么，也可以用这个方法来增加一个月，一年，一小时，一分等等

 

**9、一年前后的日期**

 在上个例子中我们使用了LocalDate的plus()方法来给日期增加日周月，现在我们用minus()方法来找出一年前的那天

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160413153302113-1179570170.png)

 

**10、在java8中使用时钟**

java8自带了Clock类，可以用来获取某个时区下（所以对时区是敏感的）当前的瞬时时间、日期。用来代替System.currentTimelnMillis()与TimeZone.getDefault()方法

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160413162111129-1177995394.png)

 

**11、在java中如何判断某个日期在另一个日期的前面还是后面**

 如何判断某个日期在另一个日期的前面还是后面或者相等，在java8中，LocalDate类中使用isBefore()、isAfter()、equals()方法来比较两个日期。如果调用方法的那个日期比给定的日期要早的话，isBefore()方法会返回true。equals()方法在前面的例子中已经说明了，这里就不举例了

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414103412020-80738682.png)

可以看到java8中比较日期非常简单，不再需要使用Calendar这样另外的类来完成类似的任务了

 

**12、在java8中处理不同的时区**

java8中不仅将日期和时间进行了分离，同时还有时区。比如ZonId代表的是某个特定时区，ZonedDateTime代表带时区的时间，等同于以前的GregorianCalendar类。使用该类，可以将本地时间转换成另一个时区中的对应时间。

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414105003301-1722105867.png)

注意：对应时区的文本可以直接手动填写，也可以调用定义好了的，手动填写的时候注意不可填错了，否则你会遇到下面的异常

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414105246910-698112874.png)

**13、如何表示固定的日期，比如信用卡过期时间**

正如MonthDay表示的是某个重复出现的日子，YearMonth是另外一个组合，代表的是像信用卡还款日，定期存款到期日，options到期日这类的日期。你可以用这个类找出这个月有多少天，LengthOfMonth()这个方法返回的是这个YearMonth实例有多少天，这对于检查2月是否润2月很有用

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414111055098-855202876.png)

**14、如何在java8中检查闰年**

LocalDate类由一个isLeapYear()方法来返回当前LocalDate对应的那年是否是闰年

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414111536207-461556981.png)

 

**15、两个日期之间包含多少天，多少月**

计算两个日期之间包含多少天、周、月、年。可以用java.time.Period类完成该功能。下面例子中将计算日期与将来的日期之间一共有几个月

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414113748332-171008862.png)

 

**16、带时区的日期与时间**

在java8中，可以使用ZoneOffset来代表某个时区，可以使用它的静态方法ZoneOffset.of()方法来获取对应的时区，只要获得了这个偏移量，就可以用这个偏移量和LocalDateTime创建一个新的OffsetDateTime

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414140648754-1630601844.png)

可以看到现在时间日期和时区关联上了，注意OffsetDateTime主要是用来给机器理解的，平时使用就用前面结束的ZoneDateTime类就可以了

 

**17、在java8中获取当前时间戳**

java8获取时间戳特别简单。Instant类由一个静态的工厂方法now()可以返回当前时间戳

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414140938973-1395309408.png)

可以看到，当前时间戳是包含日期和时间的，与java.util.Date很类似，事实上Instant就是java8以前的Date，可以使用这个两个类中的方法在这两个类型之间进行转换，比如Date.from(Instant)就是用来把Instant转换成java.util.date的，而Date。toInstant()就是将Date转换成Instant的

 

**18，如何在java8中使用预定义的格式器来对日期进行解析/格式化**dd

在java8之前，时间日期的格式化非常麻烦，经常使用SimpleDateFormat来进行格式化，但是SimpleDateFormat并不是线程安全的。在java8中，引入了一个全新的线程安全的日期与时间格式器。并且预定义好了格式。比如，本例中使用的BASICISODATE格式会将20160414格式化成2016-04-14

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414142144738-703542743.png)

在DateTimeFormatter中还有很多定义好的格式，有兴趣的可以自己去看一下

 

**19、如何在java中使用自定义的格式器来解析日期**

 在上例中，我们使用了预置的时间日期格式器来解析日期字符串了，但是有时预置的不能满足的时候就需要我们自定义日期格式器了，下面的例子中的日期格式是"MM dd yyyy".你可以给DateTimeFormatter的ofPattern静态方法()传入任何的模式，它会返回一个实例，这个模式的字面量与前例中是相同的。比如M代表月，m仍代表分，无效的模式会抛异常DateTimeParseException。

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414143730645-322666987.png)

 

**20、如何在java8中对日期进行格式化，转换成字符串**

 前面的两个例子中，我们主要是对日期字符串来进行解析转换成日期，在这个例子我们相反，是把日期转换成字符。这里我们有个LocalDateTime类的实例，我们要把他转换成一个格式化好的日期串，与前例相同的是，我们仍需要制定模式串去创建一个DateTimeFormatter类的实例，但调用的是LocalDate.format()。这个方法会返回一个代表当前日期的字符串，对应的模式就是传入的DateTimeFormatter实例中定义好的。

![img](https://images2015.cnblogs.com/blog/426886/201604/426886-20160414144815332-1977784025.png)

**21、java8时间对象如何转化成Date**

```java
public static Date LocalDateToDate(LocalDateTime d){
        return Date.from(d.atZone(ZoneId.systemDefault()).toInstant());
    }
```



### java8中日期与时间API的几个关键点

经过上面的例子，我们已经对java8的时间日期有了一定的了解，现在回顾一下

 

●它提供了javax.time.ZoneId用来处理时区。

●它提供了LocalDate与LocalTime类

●Java 8中新的时间与日期API中的所有类都是不可变且线程安全的，这与之前的Date与Calendar API中的恰好相反，那里面像java.util.Date以及SimpleDateFormat这些关键的类都不是线程安全的。

●新的时间与日期API中很重要的一点是它定义清楚了基本的时间与日期的概念，比方说，瞬时时间，持续时间，日期，时间，时区以及时间段。它们都是基于ISO日历体系的。

 

**每个Java开发人员都应该至少了解这套新的API中的这五个类：**

●Instant 它代表的是时间戳，比如2016-04-14T14:20:13.592Z，这可以从java.time.Clock类中获取，像这样： Instant current = Clock.system(ZoneId.of("Asia/Tokyo")).instant();

●LocalDate 它表示的是不带时间的日期，比如2016-04-14。它可以用来存储生日，周年纪念日，入职日期等。

●LocalTime - 它表示的是不带日期的时间

●LocalDateTime - 它包含了时间与日期，不过没有带时区的偏移量

●ZonedDateTime - 这是一个带时区的完整时间，它根据UTC/格林威治时间来进行时区调整

●这个库的主包是java.time，里面包含了代表日期，时间，瞬时以及持续时间的类。它有两个子package，一个是java.time.foramt，这个是什么用途就很明显了，还有一个是java.time.temporal，它能从更低层面对各个字段进行访问。

●时区指的是地球上共享同一标准时间的地区。每个时区都有一个唯一标识符，同时还有一个地区/城市(Asia/Tokyo)的格式以及从格林威治时间开始的一个偏移时间。比如说，东京的偏移时间就是+09:00。

●OffsetDateTime类实际上包含了LocalDateTime与ZoneOffset。它用来表示一个包含格林威治时间偏移量（+/-小时：分，比如+06:00或者 -08：00）的完整的日期（年月日）及时间（时分秒，纳秒）。

●DateTimeFormatter类用于在Java中进行日期的格式化与解析。与SimpleDateFormat不同，它是不可变且线程安全的，如果需要的话，可以赋值给一个静态变量。DateTimeFormatter类提供了许多预定义的格式器，你也可以自定义自己想要的格式。当然了，根据约定，它还有一个parse()方法是用于将字符串转换成日期的，如果转换期间出现任何错误，它会抛出DateTimeParseException异常。类似的，DateFormatter类也有一个用于格式化日期的format()方法，它出错的话则会抛出DateTimeException异常。

●再说一句，“MMM d yyyy”与“MMm dd yyyy”这两个日期格式也略有不同，前者能识别出"Jan 2 2014"与"Jan 14 2014"这两个串，而后者如果传进来的是"Jan 2 2014"则会报错，因为它期望月份处传进来的是两个字符。为了解决这个问题，在天为个位数的情况下，你得在前面补0，比如"Jan 2 2014"应该改为"Jan 02 2014"。

 

关于Java 8这个新的时间日期API就讲到这了。这几个简短的示例 对于理解这套新的API中的一些新增类已经足够了。我们学习了如何创建与修改日期实例。我们还了解了纯日期，日期加时间，日期加时区的区别，知道如何比较两个日期，如何找到某天到指定日期比如说下一个生日，周年纪念日或者保险日还有多少天。我们还学习了如何在Java 8中用线程安全的方式对日期进行解析及格式化，而无需再使用线程本地变量或者第三方库这种取巧的方式。新的API能胜任任何与时间日期相关的任务。

