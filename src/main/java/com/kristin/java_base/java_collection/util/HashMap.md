# HashMap
## ONE-Base 1.7
#### 1.1属性 
JDK1.7HashMap数据结构: 数组+链表
1. DEFAULT_INITIAL_CAPACITY : 初始化桶大小，因为底层是数组，所以这是数组默认的大小。
2. MAXIMUM_CAPACITY : 桶最大值。
3. DEFAULT_LOAD_FACTOR : 默认的负载因子（0.75）
4. table[] :  真正存放数据的数组。
5. size : Map 存放数量的大小。
6. threshold : 桶大小，可在初始化时显式指定。
7. loadFactor : 负载因子，可在初始化时显式指定。

#### 1.2 方法 
###### Entry内部类
1. key 就是写入时的键。
2. value 自然就是值。
3. 开始的时候就提到HashMap是由数组和链表组成，所以这个next就是用于实现链表结构。
4. hash 存放的是当前key的hashcode。

###### hash(int h)
```java
static int hash(int h) {  
    h ^= (h >>> 20) ^ (h >>> 12);  
    return h ^ (h >>> 7) ^ (h >>> 4);  
}  
static int indexFor(int h, int length) {  
    return h & (length-1);  
}  
```
###### put(K key, V value)
1. 判断当前数组是否需要初始化。
2. 如果 key 为空，则 put 一个空值进去。
3. 根据 key 计算出 hashcode。
4. 根据计算出的 hashcode 定位出所在桶。
5. 如果桶是一个链表则需要遍历判断里面的 hashcode、key 是否和传入 key 相等，如果相等则进行覆盖，并返回原来的值。
6. 如果桶是空的，说明当前位置没有数据存入；新增一个 Entry 对象写入当前位置。

###### addEntry(int hash, K key, V value, int bucketIndex)
1. 当调用 addEntry 写入 Entry 时需要判断是否需要扩容。
2. 如果需要就进行两倍扩充，并将当前的 key 重新 hash 并定位。
3. 而在 createEntry 中会将当前位置的桶传入到新建的桶中，如果当前桶有值就会在位置形成链表。

###### get(Object key)
1. 首先也是根据 key 计算出 hashcode，然后定位到具体的桶中。
2. 判断该位置是否为链表。
3. 不是链表就根据 key、key 的 hashcode 是否相等来返回值。
4. 为链表则需要遍历直到 key 及 hashcode 相等时候就返回值。
5. 啥都没取到就直接返回 null 。

## TWO-Base 1.8
#### 2.1 属性
由于JDK1.8数据结构改变为数组+链表+红黑树,所以在JDK1.7属性的基础上新增了几个属性
```java
static final int TREEIFY_THRESHOLD = 8;	//当桶(bucket)上的结点数大于TREEIFY_THRESHOLD时会转成红黑树
static final int UNTREEIFY_THRESHOLD = 6;	//当桶(bucket)上的结点数小于UNTREEIFY_THRESHOLD时树转链表
static final int MIN_TREEIFY_CAPACITY = 64;	//桶中结构转化为红黑树对应的数组的最小容量，如果当前容量小于它，就不会将链表转化为红黑树，而是用resize()代替
transient int modCount;     //数据结构改变次数
```
这里要说一下modCount属性,在JDK1.7和JDK1.8中都有,因为HashMap是不安全的,所以每一次修改数据结构(例如增加或者删除元素)都会改变modCount的值,所以如果在并发的情况下,
一个线程在遍历,而另一个线程在修改数据结构,那么当遍历中的线程发现modCount值发生改变就会立即报异常,不会继续遍历下去,这就是fail-fast机制,发现ArrayList,HashMap这类不安全的集合,都会有modCount来实现fail-fast

#### 2.2 方法
###### hash(Object key)
JDK1.8 去除了indexFor(),并且更改hash方法为hashCode的高16位与低16位进行异或,是得每一位都参与运算,hash结果更为均匀
```java
static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```

###### Node<K,V>
JDK1.8仅仅是将内部结构改名为Node,其实属性和方法均与JDK1.7的Entry<K,V>相同
```java
static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;
}
```

###### HashMap构造函数
```java
public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);
    this.loadFactor = loadFactor;
    this.threshold = tableSizeFor(initialCapacity);
}

public HashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
}

//默认构造方法
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}

public HashMap(Map<? extends K, ? extends V> m) {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    putMapEntries(m, false);
}
```

###### put(K key, V value)
1. 判断当前桶是否为空，空的就需要初始化（resize 中会判断是否进行初始化）。
2. 根据当前 key 的 hashcode 定位到具体的桶中并判断是否为空，为空表明没有 Hash 冲突就直接在当前位置创建一个新桶即可。
3. 如果当前桶有值（ Hash 冲突），那么就要比较当前桶中的 key、key 的 hashcode 与写入的 key 是否相等，相等就赋值给 e,在第 8 步的时候会统一进行赋值及返回。
4. 如果当前桶为红黑树，那就要按照红黑树的方式写入数据。
5. 如果是个链表，就需要将当前的 key、value 封装成一个新节点写入到当前桶的后面（形成链表）。
6. 接着判断当前链表的大小是否大于预设的阈值，大于时就要转换为红黑树。
7. 如果在遍历过程中找到 key 相同时直接退出遍历。
8. 如果 e != null 就相当于存在相同的 key,那就需要将值覆盖。
9. 最后判断是否需要进行扩容。

###### get(Object key)
1. 首先将 key hash 之后取得所定位的桶。
2. 如果桶为空则直接返回 null 。
3. 否则判断桶的第一个位置(有可能是链表、红黑树)的 key 是否为查询的 key，是就直接返回 value。
4. 如果第一个不匹配，则判断它的下一个是红黑树还是链表。
5. 红黑树就按照树的查找方式返回值。
6. 不然就按照链表的方式遍历匹配返回值。

###### tableSizeFor(int cap)
获取大于等于当前容量的最小的2的整数幂,所以移位数都是2的整数幂,而且数值最大为32位,所以不需要移32位,最多只需要移位16即可
```java
static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```

###### resize()
1. 计算扩容后的容量，临界值。
2. 将hashMap的临界值修改为扩容后的临界值
3. 根据扩容后的容量新建数组，然后将hashMap的table的引用指向新数组。
4. 将旧数组的元素复制到table中。

resize()方法的改进:
由于JDK1.8更新了hash方法,index时只需要hash&(length-1),所以扩容时就每个元素不需要重新计算hash值,原因如下:
新表索引：hash & (newCap - 1)---》低x位为Index
旧表索引：hash & (oldCap - 1)---》低x-1位为Index
newCap = oldCap << 1
举例说明：resize()之前为低x-1位为Index，resize()之后为低x位为Index
则所有Entry中，hash值第x位为0的，不需要哈希到新位置，只需要呆在当前索引下的新位置j
hash值第x位为1的，需要哈希到新位置，新位置为j+oldCap

https://javadoop.com/post/hashmap#Java8%20HashMap