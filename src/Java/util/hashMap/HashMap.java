/*
 * Copyright 2019-2022 the original author or authors.
 */

package Java.util.hashMap;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 学习HashMap
 */
public class HashMap<K, V> extends AbstractMap<K, V>
    implements Map<K, V> {

  /**
   * 默认容量16
   */
  static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

  /**
   * 最大容量 1的30次幂
   */
  static final int MAXIMUM_CAPACITY = 1 << 30;

  /**
   * 默认的负载因子
   */
  static final float DEFAULT_LOAD_FACTOR = 0.75f;

  /**
   * 链表转为红黑树的门禁值，8
   */
  static final int TREEIFY_THRESHOLD = 8;

  /**
   * 去红黑树的
   */
  static final int UNTREEIFY_THRESHOLD = 6;

  /**
   * 转化为红黑树时，table的容量起码为64
   */
  static final int MIN_TREEIFY_CAPACITY = 64;

  /**
   * 下面的变量都是再AbstractMap里面的
   */
  transient Set<K> keySet;

  transient Collection<V> values;

  /**
   * 存储具体<K,V>的结点结构
   *
   * @param <K> key值
   * @param <V> value值
   */
  static class Node<K, V> implements Map.Entry<K, V> {

    final int hash; //存放key根据Hash函数计算出的hash值
    final K key;
    V value;
    Node<K, V> next; //初始为链表，指向下一个结点

    Node(int hash, K key, V value, Node<K, V> next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public final K getKey() {
      return key;
    }

    public final V getValue() {
      return value;
    }

    public final String toString() {
      return key + "=" + value;
    }

    /**
     * 获取该节点的hash值
     *
     * @return hash值
     */
    public final int hashCode() {
      return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
      V oldValue = value;
      value = newValue;
      return oldValue;
    }

    public final boolean equals(Object o) {
      if (o == this) {
        return true;
      } //是该类，而且key，value相等即为相等
      if (o instanceof Map.Entry) {
        Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
        if (Objects.equals(key, e.getKey()) &&
            Objects.equals(value, e.getValue())) {
          return true;
        }
      }
      return false;
    }

  }

  /**
   * 计算hash值的静态方法 hash算法（尽可能少冲突） 值的计算是通过hashCode()的高16位异或低16位实现的 主要是从速度、功效、质量来考虑的，这么做可以在数组table的length比较小的时候，也能保证考虑到高低Bit都参与到Hash的计算中，同时不会有太大的开销
   *
   * @param key key值
   * @return hash值
   */
  static final int hash(Object key) {
    int h;
    //使用异或运算符杂糅高位和低位，让元素更加均匀分配在各个index上，减少冲突
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }

  /**
   * 判断是否实现了comparable接口吗？
   *
   * @param x 传入的类
   * @return Class对象或null
   */
  static Class<?> comparableClassFor(Object x) {
    if (x instanceof Comparable) {
      Class<?> c;
      Type[] ts, as;
      Type t;
      ParameterizedType p;
      if ((c = x.getClass()) == String.class) // bypass checks
      {
        return c;
      }
      if ((ts = c.getGenericInterfaces()) != null) {
        for (int i = 0; i < ts.length; ++i) {
          if (((t = ts[i]) instanceof ParameterizedType) &&
              ((p = (ParameterizedType) t).getRawType() ==
                  Comparable.class) &&
              (as = p.getActualTypeArguments()) != null &&
              as.length == 1 && as[0] == c) // type arg is c
          {
            return c;
          }
        }
      }
    }
    return null;
  }


  /**
   * 如果x的类为kc，比较k和x的大小
   */
  @SuppressWarnings({"rawtypes", "unchecked"}) // for cast to Comparable
  static int compareComparables(Class<?> kc, Object k, Object x) {
    return (x == null || x.getClass() != kc ? 0 :
        ((Comparable) k).compareTo(x));
  }


  /**
   * 把传入的容量值转化为：刚大于传入容量的的2次幂的最小值
   */
  static final int tableSizeFor(int cap) {
    int n = cap - 1; //保证有效最高位为1
    n |= n >>> 1; //依次把后面位数都变为1
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    //最后如果没有大于最大值，就+1，变为2的幂次
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }

  /**
   * 一个entry<K,V>数组，就是存储node的数组结构
   */
  transient Node<K, V>[] table;

  /**
   * 存放Map转为的Set<Entry>
   */
  transient Set<Entry<K, V>> entrySet;

  /**
   * map里面有多少个key
   */
  transient int size;

  /**
   * 记录　hashMap 发生结构性变化的次数（注意　value 的覆盖不属于结构性变化） 如果expectedModCount与该值不相等，就会快速失败
   */
  transient int modCount;

  /**
   * 这个值在resize的时候会用确认resize时新的table长度，门禁值；等于=(capacity * load factor)
   */
  int threshold;

  /**
   * 负载因子
   */
  final float loadFactor;

  /**
   * 构造函数
   *
   * @param initialCapacity 初始化容量
   * @param loadFactor      负载因子
   */
  public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Illegal initial capacity: " +
          initialCapacity);
    }
    if (initialCapacity > MAXIMUM_CAPACITY) {
      initialCapacity = MAXIMUM_CAPACITY;
    }
    if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
      throw new IllegalArgumentException("Illegal load factor: " +
          loadFactor);
    }
    this.loadFactor = loadFactor;
    //门禁值，也就是table的数组长度
    this.threshold = tableSizeFor(initialCapacity);
  }

  /**
   * 构造器，只传入初始容量
   *
   * @param initialCapacity 初始容量
   */
  public HashMap(int initialCapacity) {
//    负载因子为默认值
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
  }

  //  负载因子和初始容量都为默认值
  public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
  }

  /**
   * 把一个实现了map接口的对象转为hashmap
   *
   * @param m map
   */
  public HashMap(Map<? extends K, ? extends V> m) {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    putMapEntries(m, false);
  }

  /**
   * @param m     需要转为hashMap的map
   * @param evict false指创建hashMap时调用这个方法，true指创建hashmap后才调用该方法
   */
  final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
    int s = m.size();
    //如果该map的存在key
    if (s > 0) {
      //新键的hashmap没有初始化table
      if (table == null) {
        //计算初始化容量大小
        float ft = ((float) s / loadFactor) + 1.0F;
        int t = ((ft < (float) MAXIMUM_CAPACITY) ?
            (int) ft : MAXIMUM_CAPACITY);
        //大于当前resize门禁，计算大于t的最小2次幂
        if (t > threshold) {
          threshold = tableSizeFor(t);
        }
      } //如果table不为空，而且map的长度大于门禁值，需要对table进行resize
      else if (s > threshold) {
        resize();
      }
      //遍历旧map，把元素存到新的hashMap里面
      for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
        K key = e.getKey();
        V value = e.getValue();
        putVal(hash(key), key, value, false, evict);
      }
    }
  }


  /**
   * 返回key的个数
   *
   * @return key的个数
   */
  public int size() {
    return size;
  }

  /**
   * 判断是否为空
   *
   * @return 是否为空
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 根据key值获取Value
   *
   * @param key 键
   * @return 返回value
   */
  public V get(Object key) {
    Node<K, V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
  }

  /**
   * 获取value
   *
   * @param hash key的hash值
   * @param key  key
   * @return key对应的value
   */
  final Node<K, V> getNode(int hash, Object key) {
    Node<K, V>[] tab;
    Node<K, V> first, e;
    int n;
    K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
      // 每次先判断首个结点是否为要获取的，因此最后一个插入的，往往是最有可能马上要用的
      if (first.hash == hash &&
          ((k = first.key) == key || (key != null && key.equals(k)))) {
        return first;
      }
      // 首节点不为null，则判断是链表结构还是红黑树结构
      if ((e = first.next) != null) {
        if (first instanceof TreeNode)
        //调用红黑树获取value方法
        {
          return ((TreeNode<K, V>) first).getTreeNode(hash, key);
        }
        do {
          // 遍历链表
          if (e.hash == hash &&
              ((k = e.key) == key || (key != null && key.equals(k)))) {
            return e;
          }
        } while ((e = e.next) != null);
      }
    }
    return null;
  }

  /**
   * 根据hash值，直接调用getNode方法
   *
   * @param key 键
   * @return 是否存在boolean
   */
  public boolean containsKey(Object key) {
    return getNode(hash(key), key) != null;
  }

  /**
   * 把值放入hashMap，默认是覆盖原来的value
   *
   * @param key   键
   * @param value 值
   * @return 值
   */
  public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
  }


  /**
   * 把键值对存进hashMap
   *
   * @param hash         根据key计算出的hash值
   * @param key          key值
   * @param value        value值
   * @param onlyIfAbsent 如果key存在，是否改变原来key对应的值
   * @param evict        是否延迟加载
   * @return 返回key对应得value
   */
  final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
      boolean evict) {
    Node<K, V>[] tab;
    Node<K, V> p;
    int n, i; // p是table[index]的首结点
    if ((tab = table) == null || (n = tab.length) == 0) //如果未初始化，则先resize进行初始化
    {
      n = (tab = resize()).length;
    }
    if ((p = tab[i = (n - 1) & hash]) == null) //判断要放进的table[index]是否为空
    {
      tab[i] = newNode(hash, key, value, null); // table[index]为空，新建一个Node，直接放进去
    } else {  // table[index]不为空
      Node<K, V> e;
      K k;
      // key值与首元素的key值相等，hash值也相等，都相等后，后续会判断是否覆盖值
      if (p.hash == hash &&
          ((k = p.key) == key || (key != null && key.equals(k)))) {
        e = p;
      } else if (p instanceof TreeNode)  //如果是红黑树结点，调用红黑树相关插入方法
      {
        e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
      } else { // 如果是链表结点
        for (int binCount = 0; ; ++binCount) { // 遍历链表
          if ((e = p.next) == null) {
            // 到了链表尾部，还没有找到key相同的结点，尾插法，保证原来的次序
            p.next = newNode(hash, key, value, null);
            // 判断此时该链表是否到了转化红黑树的门禁值
            if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
            {
              treeifyBin(tab, hash); // 链表转化为红黑树
            }
            break;
          }
          // 没有到尾结点，判断key值与首元素的key值，hash值，都相等则跳出循环
          if (e.hash == hash &&
              ((k = e.key) == key || (key != null && key.equals(k)))) {
            break;
          }
          p = e;
        }
      }
      if (e != null) { // 存在一样的key值，判断是否替换value
        V oldValue = e.value;
        // value为null或onlyIfAbsent为fales则覆盖
        if (!onlyIfAbsent || oldValue == null) {
          e.value = value;
        }
        afterNodeAccess(e); // hashMap内该方法为null，LinkedHashMap实现了该方法，把值移动到table[index]的首位
        return oldValue; // 返回旧的值，因为这里新value可能覆盖旧value
      }
    }
    ++modCount; // 快速失败长度计数自增
    if (++size > threshold) // 长度如果大于resize门禁值，扩容
    {
      resize();
    }
    afterNodeInsertion(evict); // hashMap内该方法为null，LinkedHashMap实现了该方法
    return null; // 因为没有oldValue，所以返回null
  }

  /**
   * 扩容 在扩充HashMap的时候，只需要看看原来的hash值新增的那个bit是1还是0就好了，是0的话索引没变，是1的话索引变成“原索引+oldCap”
   * 既省去了重新计算hash值的时间，而且同时，由于新增的1bit是0还是1可以认为是随机的，因此resize的过程，均匀的把之前的冲突的节点分散到新的槽中了
   *
   * @return 返回重新扩容后得table
   */
  final Node<K, V>[] resize() {
    Node<K, V>[] oldTab = table; //旧table
    int oldCap = (oldTab == null) ? 0 : oldTab.length; //旧table的元素个数
    int oldThr = threshold;
    int newCap, newThr = 0;
    if (oldCap > 0) {
      //旧table的长度为最大值，无法扩容了，返回旧table
      if (oldCap >= MAXIMUM_CAPACITY) {
        threshold = Integer.MAX_VALUE;
        return oldTab;
      } //如果旧长度*2小于最大值，那么新容量=旧长度*2，新的扩容门槛=旧门槛*2
      else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
          oldCap >= DEFAULT_INITIAL_CAPACITY) {
        newThr = oldThr << 1; // double threshold
      }
    } else if (oldThr > 0) // 如果初始化的扩容门禁已经被代替，
    {
      newCap = oldThr;
    } else {               // 如果hashMap原来是未初始化的，那么使用初始化配置
      newCap = DEFAULT_INITIAL_CAPACITY;
      newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    if (newThr == 0) {
      float ft = (float) newCap * loadFactor;
      newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
          (int) ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes", "unchecked"})
    Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap]; //新建一个table
    table = newTab;
    if (oldTab != null) {  //如果旧table不为null，把遍历旧table，把旧的元素迁移到新的table里面
      for (int j = 0; j < oldCap; ++j) {
        Node<K, V> e;
        if ((e = oldTab[j]) != null) {
          oldTab[j] = null;
          if (e.next == null) //如果该index下只要一个节点，那么直接赋值
          {
            newTab[e.hash & (newCap - 1)] = e;
          } else if (e instanceof TreeNode)  //该index有下一个节点，且为红黑树结点
          {
            ((TreeNode<K, V>) e).split(this, newTab, j, oldCap); //调用红黑树的方法
          } else { // 该index有下一个节点，此时且为链表，使用头插法
            Node<K, V> loHead = null, loTail = null; //resize后还在原来index上的元素链表
            Node<K, V> hiHead = null, hiTail = null; //resize后在index+oldCap的元素链表
            Node<K, V> next;
            do {
              next = e.next;
              if ((e.hash & oldCap) == 0) {    // hash值的高一位为0
                if (loTail == null) {
                  loHead = e;
                } else {
                  loTail.next = e; // 头插法，保证了原来的次序，导致成环的概率小了很多
                }
                loTail = e;
              } else {  // hash值的高一位为1
                if (hiTail == null) {
                  hiHead = e;
                } else {
                  hiTail.next = e;
                }
                hiTail = e;
              }
            } while ((e = next) != null);
            // 再hash后的值会在原来的index或者是index+oldCap上，因为长度是2的幂次,高一位为0或1,
            if (loTail != null) {
              loTail.next = null;
              newTab[j] = loHead; // hash值的高一位为0的元素还在原来的index上
            }
            if (hiTail != null) {
              hiTail.next = null;
              newTab[j + oldCap] = hiHead; // hash值的高一位为1的元素在index+oldCap上
            }
          }
        }
      }
    }
    return newTab;
  }

  /**
   * 把一个table[index]链表转为红黑树
   *
   * @param tab  table
   * @param hash 需要转为红黑树的hash值
   */
  final void treeifyBin(Node<K, V>[] tab, int hash) {
    int n, index;
    Node<K, V> e;
    // 没有到红黑树化的最小容量，则扩容处理，不转为红黑树
    if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY) {
      resize();
    }
    // 需要转化为红黑树
    else if ((e = tab[index = (n - 1) & hash]) != null) {
      TreeNode<K, V> hd = null, tl = null;
      // 先把链表的所有结点都转为红黑树结点，并保持链表结构
      do {
        // 结点替换为红黑树结点
        TreeNode<K, V> p = replacementTreeNode(e, null);
        if (tl == null) {
          hd = p;
        } else {
          p.prev = tl;
          tl.next = p;
        }
        tl = p;
      } while ((e = e.next) != null);
      if ((tab[index] = hd) != null) {
        hd.treeify(tab);
      }
    }
  }

  /**
   * 把一个map里面的所有元素传进来
   *
   * @param m 需要转化的map
   */
  public void putAll(Map<? extends K, ? extends V> m) {
    putMapEntries(m, true); // true说明是在hashmap创建完后，再调用putMapEntries方法
  }

  /**
   * 移除某个key
   *
   * @param key 键
   * @return 要移除的values
   */
  public V remove(Object key) {
    Node<K, V> e;
    return (e = removeNode(hash(key), key, null, false, true)) == null ?
        null : e.value;
  }

  /**
   * 移除一个元素
   *
   * @param hash       hash值
   * @param key        键
   * @param value      值
   * @param matchValue 是否仅当value相等的时候才移除
   * @param movable    false时，当别的结点正在被移除得时候不能操作
   * @return 返回删除的value
   */
  final Node<K, V> removeNode(int hash, Object key, Object value,
      boolean matchValue, boolean movable) {
    Node<K, V>[] tab;
    Node<K, V> p;
    int n, index;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (p = tab[index = (n - 1) & hash]) != null) { // 判断map，index下是否为空
      // 先获取到该结点
      Node<K, V> node = null, e;
      K k;
      V v;
      // 先比较首元素是否相等
      if (p.hash == hash &&
          ((k = p.key) == key || (key != null && key.equals(k)))) {
        node = p;
      } else if ((e = p.next) != null) {
        // 是红黑树结点，则调用相关方法
        if (p instanceof TreeNode) {
          node = ((TreeNode<K, V>) p).getTreeNode(hash, key);
        } else {
          // 是链表结点，用循环遍历去找
          do {
            if (e.hash == hash &&
                ((k = e.key) == key ||
                    (key != null && key.equals(k)))) {
              node = e;
              break;
            }
            p = e;
          } while ((e = e.next) != null);
        }
      }
      // 获取结点后，判断是否要根据value值是否相等来决定删除
      if (node != null && (!matchValue || (v = node.value) == value ||
          (value != null && value.equals(v)))) {
        // 如果是红黑树结点，调用相关方法
        if (node instanceof TreeNode) {
          ((TreeNode<K, V>) node).removeTreeNode(this, tab, movable);
        }
        // 如果是链表得首结点，修改首结点指针
        else if (node == p) {
          tab[index] = node.next;
        }
        //如果是链表，且非首结点
        else {
          p.next = node.next;
        }
        ++modCount;
        --size;
        // 执行结点删除后的操作，hashmap里面什么都不做
        afterNodeRemoval(node);
        return node;
      }
    }
    return null;
  }

  /**
   * 清除所有元素
   */
  public void clear() {
    Node<K, V>[] tab;
    // 先修改结构性变化次数，每次修改都是自增
    modCount++;
    if ((tab = table) != null && size > 0) {
      size = 0;
      // 直接for循环置空所有index首结点
      for (int i = 0; i < tab.length; ++i) {
        tab[i] = null;
      }
    }
  }

  /**
   * 判断Value是否存在
   *
   * @param value 值
   * @return 是否存在
   */
  public boolean containsValue(Object value) {
    Node<K, V>[] tab;
    V v;
    if ((tab = table) != null && size > 0) {
      // 从index=0开始遍历，效率不高噢，O(n)
      for (int i = 0; i < tab.length; ++i) {
        for (Node<K, V> e = tab[i]; e != null; e = e.next) {
          if ((v = e.value) == value ||
              (value != null && value.equals(v))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * 返回所有key值得集合
   *
   * @return key值集合
   */
  public Set<K> keySet() {
    Set<K> ks = keySet;
    if (ks == null) {
      ks = new KeySet();
      keySet = ks;
    }
    return ks;
  }

  /**
   * key值集合类
   */
  final class KeySet extends AbstractSet<K> {

    public final int size() {
      return size;
    }

    public final void clear() {
      HashMap.this.clear();
    }

    public final Iterator<K> iterator() {
      return new KeyIterator();
    }

    public final boolean contains(Object o) {
      return containsKey(o);
    }

    public final boolean remove(Object key) {
      return removeNode(hash(key), key, null, false, true) != null;
    }

    public final Spliterator<K> spliterator() {
      return new KeySpliterator<>(HashMap.this, 0, -1, 0, 0);
    }

    public final void forEach(Consumer<? super K> action) {
      Node<K, V>[] tab;
      if (action == null) {
        throw new NullPointerException();
      }
      if (size > 0 && (tab = table) != null) {
        int mc = modCount;
        for (int i = 0; i < tab.length; ++i) {
          for (Node<K, V> e = tab[i]; e != null; e = e.next) {
            action.accept(e.key);
          }
        }
        if (modCount != mc) {
          throw new ConcurrentModificationException();
        }
      }
    }
  }

  public Collection<V> values() {
    Collection<V> vs = values;
    if (vs == null) {
      vs = new Values();
      values = vs;
    }
    return vs;
  }

  final class Values extends AbstractCollection<V> {

    public final int size() {
      return size;
    }

    public final void clear() {
      HashMap.this.clear();
    }

    public final Iterator<V> iterator() {
      return new ValueIterator();
    }

    public final boolean contains(Object o) {
      return containsValue(o);
    }

    public final Spliterator<V> spliterator() {
      return new ValueSpliterator<>(HashMap.this, 0, -1, 0, 0);
    }

    public final void forEach(Consumer<? super V> action) {
      Node<K, V>[] tab;
      if (action == null) {
        throw new NullPointerException();
      }
      if (size > 0 && (tab = table) != null) {
        int mc = modCount;
        for (int i = 0; i < tab.length; ++i) {
          for (Node<K, V> e = tab[i]; e != null; e = e.next) {
            action.accept(e.value);
          }
        }
        if (modCount != mc) {
          throw new ConcurrentModificationException();
        }
      }
    }
  }

  public Set<Map.Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> es;
    return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
  }


  final class EntrySet extends AbstractSet<Entry<K, V>> {

    public final int size() {
      return size;
    }

    public final void clear() {
      HashMap.this.clear();
    }

    public final Iterator<Entry<K, V>> iterator() {
      return new EntryIterator();
    }

    public final boolean contains(Object o) {
      if (!(o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
      Object key = e.getKey();
      Node<K, V> candidate = getNode(hash(key), key);
      return candidate != null && candidate.equals(e);
    }

    public final boolean remove(Object o) {
      if (o instanceof Map.Entry) {
        Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
        Object key = e.getKey();
        Object value = e.getValue();
        return removeNode(hash(key), key, value, true, true) != null;
      }
      return false;
    }

    public final Spliterator<Entry<K, V>> spliterator() {
      return new EntrySpliterator<>(HashMap.this, 0, -1, 0, 0);
    }

    public final void forEach(Consumer<? super Entry<K, V>> action) {
      Node<K, V>[] tab;
      if (action == null) {
        throw new NullPointerException();
      }
      if (size > 0 && (tab = table) != null) {
        int mc = modCount;
        for (int i = 0; i < tab.length; ++i) {
          for (Node<K, V> e = tab[i]; e != null; e = e.next) {
            action.accept(e);
          }
        }
        if (modCount != mc) {
          throw new ConcurrentModificationException();
        }
      }
    }
  }

  /**
   * 获取key，如果没有，就返回默认值
   *
   * @param key
   * @param defaultValue
   * @return
   */
  @Override
  public V getOrDefault(Object key, V defaultValue) {
    Node<K, V> e;
    return (e = getNode(hash(key), key)) == null ? defaultValue : e.value;
  }

  /**
   * 如果key已经对应有值，就不覆盖
   *
   * @return 返回最终key对应的v
   */
  @Override
  public V putIfAbsent(K key, V value) {
    return putVal(hash(key), key, value, true, true);
  }

  /**
   * 如果存在，则用新值替换旧值
   * @param key 键
   * @param value 值
   * @return 旧值
   */
  @Override
  public V replace(K key, V value) {
    Node<K,V> e;
    if ((e = getNode(hash(key), key)) != null) {
      V oldValue = e.value;
      e.value = value;
      afterNodeAccess(e);
      return oldValue;
    }
    return null;
  }

  /**
   * 判断旧值是否相等， 相等，则替换新值，返回true
   * @param key 键
   * @param oldValue 旧值
   * @param newValue 新值
   * @return 旧值是否相等
   */
  @Override
  public boolean replace(K key, V oldValue, V newValue) {
    Node<K,V> e; V v;
    if ((e = getNode(hash(key), key)) != null &&
        ((v = e.value) == oldValue || (v != null && v.equals(oldValue)))) {
      e.value = newValue;
      afterNodeAccess(e);
      return true;
    }
    return false;
  }

  /* 迭代器-----------------------------------------*/
  abstract class HashIterator {

    Node<K, V> next;        // next entry to return
    Node<K, V> current;     // current entry
    int expectedModCount;  // for fast-fail
    int index;             // current slot

    HashIterator() {
      expectedModCount = modCount;
      Node<K, V>[] t = table;
      current = next = null;
      index = 0;
      if (t != null && size > 0) { // advance to first entry
        do {
        } while (index < t.length && (next = t[index++]) == null);
      }
    }

    public final boolean hasNext() {
      return next != null;
    }

    final Node<K, V> nextNode() {
      Node<K, V>[] t;
      Node<K, V> e = next;
      if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
      }
      if (e == null) {
        throw new NoSuchElementException();
      }
      if ((next = (current = e).next) == null && (t = table) != null) {
        do {
        } while (index < t.length && (next = t[index++]) == null);
      }
      return e;
    }

    public final void remove() {
      Node<K, V> p = current;
      if (p == null) {
        throw new IllegalStateException();
      }
      if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
      }
      current = null;
      K key = p.key;
      removeNode(hash(key), key, null, false, false);
      expectedModCount = modCount;
    }
  }

  final class KeyIterator extends HashIterator
      implements Iterator<K> {

    public final K next() {
      return nextNode().key;
    }
  }

  final class ValueIterator extends HashIterator
      implements Iterator<V> {

    public final V next() {
      return nextNode().value;
    }
  }

  final class EntryIterator extends HashIterator
      implements Iterator<Map.Entry<K, V>> {

    public final Map.Entry<K, V> next() {
      return nextNode();
    }
  }

  /* 迭代器-----------------------------------------*/


  static class HashMapSpliterator<K,V> {
    final HashMap<K,V> map;
    Node<K,V> current;          // current node
    int index;                  // current index, modified on advance/split
    int fence;                  // one past last index
    int est;                    // size estimate
    int expectedModCount;       // for comodification checks

    HashMapSpliterator(HashMap<K,V> m, int origin,
        int fence, int est,
        int expectedModCount) {
      this.map = m;
      this.index = origin;
      this.fence = fence;
      this.est = est;
      this.expectedModCount = expectedModCount;
    }

    final int getFence() { // initialize fence and size on first use
      int hi;
      if ((hi = fence) < 0) {
        HashMap<K,V> m = map;
        est = m.size;
        expectedModCount = m.modCount;
        Node<K,V>[] tab = m.table;
        hi = fence = (tab == null) ? 0 : tab.length;
      }
      return hi;
    }

    public final long estimateSize() {
      getFence(); // force init
      return (long) est;
    }
  }


  static final class KeySpliterator<K,V>
      extends HashMapSpliterator<K,V>
      implements Spliterator<K> {
    KeySpliterator(HashMap<K,V> m, int origin, int fence, int est,
        int expectedModCount) {
      super(m, origin, fence, est, expectedModCount);
    }

    public KeySpliterator<K,V> trySplit() {
      int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
      return (lo >= mid || current != null) ? null :
          new KeySpliterator<>(map, lo, index = mid, est >>>= 1,
              expectedModCount);
    }

    public void forEachRemaining(Consumer<? super K> action) {
      int i, hi, mc;
      if (action == null)
        throw new NullPointerException();
      HashMap<K,V> m = map;
      Node<K,V>[] tab = m.table;
      if ((hi = fence) < 0) {
        mc = expectedModCount = m.modCount;
        hi = fence = (tab == null) ? 0 : tab.length;
      }
      else
        mc = expectedModCount;
      if (tab != null && tab.length >= hi &&
          (i = index) >= 0 && (i < (index = hi) || current != null)) {
        Node<K,V> p = current;
        current = null;
        do {
          if (p == null)
            p = tab[i++];
          else {
            action.accept(p.key);
            p = p.next;
          }
        } while (p != null || i < hi);
        if (m.modCount != mc)
          throw new ConcurrentModificationException();
      }
    }

    public boolean tryAdvance(Consumer<? super K> action) {
      int hi;
      if (action == null)
        throw new NullPointerException();
      Node<K,V>[] tab = map.table;
      if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
        while (current != null || index < hi) {
          if (current == null)
            current = tab[index++];
          else {
            K k = current.key;
            current = current.next;
            action.accept(k);
            if (map.modCount != expectedModCount)
              throw new ConcurrentModificationException();
            return true;
          }
        }
      }
      return false;
    }

    public int characteristics() {
      return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
          Spliterator.DISTINCT;
    }
  }

  static final class ValueSpliterator<K,V>
      extends HashMapSpliterator<K,V>
      implements Spliterator<V> {
    ValueSpliterator(HashMap<K,V> m, int origin, int fence, int est,
        int expectedModCount) {
      super(m, origin, fence, est, expectedModCount);
    }

    public ValueSpliterator<K,V> trySplit() {
      int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
      return (lo >= mid || current != null) ? null :
          new ValueSpliterator<>(map, lo, index = mid, est >>>= 1,
              expectedModCount);
    }

    public void forEachRemaining(Consumer<? super V> action) {
      int i, hi, mc;
      if (action == null)
        throw new NullPointerException();
      HashMap<K,V> m = map;
      Node<K,V>[] tab = m.table;
      if ((hi = fence) < 0) {
        mc = expectedModCount = m.modCount;
        hi = fence = (tab == null) ? 0 : tab.length;
      }
      else
        mc = expectedModCount;
      if (tab != null && tab.length >= hi &&
          (i = index) >= 0 && (i < (index = hi) || current != null)) {
        Node<K,V> p = current;
        current = null;
        do {
          if (p == null)
            p = tab[i++];
          else {
            action.accept(p.value);
            p = p.next;
          }
        } while (p != null || i < hi);
        if (m.modCount != mc)
          throw new ConcurrentModificationException();
      }
    }

    public boolean tryAdvance(Consumer<? super V> action) {
      int hi;
      if (action == null)
        throw new NullPointerException();
      Node<K,V>[] tab = map.table;
      if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
        while (current != null || index < hi) {
          if (current == null)
            current = tab[index++];
          else {
            V v = current.value;
            current = current.next;
            action.accept(v);
            if (map.modCount != expectedModCount)
              throw new ConcurrentModificationException();
            return true;
          }
        }
      }
      return false;
    }

    public int characteristics() {
      return (fence < 0 || est == map.size ? Spliterator.SIZED : 0);
    }
  }



  static final class EntrySpliterator<K,V>
      extends HashMapSpliterator<K,V>
      implements Spliterator<Map.Entry<K,V>> {
    EntrySpliterator(HashMap<K,V> m, int origin, int fence, int est,
        int expectedModCount) {
      super(m, origin, fence, est, expectedModCount);
    }

    public EntrySpliterator<K,V> trySplit() {
      int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
      return (lo >= mid || current != null) ? null :
          new EntrySpliterator<>(map, lo, index = mid, est >>>= 1,
              expectedModCount);
    }

    public void forEachRemaining(Consumer<? super Map.Entry<K,V>> action) {
      int i, hi, mc;
      if (action == null)
        throw new NullPointerException();
      HashMap<K,V> m = map;
      Node<K,V>[] tab = m.table;
      if ((hi = fence) < 0) {
        mc = expectedModCount = m.modCount;
        hi = fence = (tab == null) ? 0 : tab.length;
      }
      else
        mc = expectedModCount;
      if (tab != null && tab.length >= hi &&
          (i = index) >= 0 && (i < (index = hi) || current != null)) {
        Node<K,V> p = current;
        current = null;
        do {
          if (p == null)
            p = tab[i++];
          else {
            action.accept(p);
            p = p.next;
          }
        } while (p != null || i < hi);
        if (m.modCount != mc)
          throw new ConcurrentModificationException();
      }
    }

    public boolean tryAdvance(Consumer<? super Map.Entry<K,V>> action) {
      int hi;
      if (action == null)
        throw new NullPointerException();
      Node<K,V>[] tab = map.table;
      if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
        while (current != null || index < hi) {
          if (current == null)
            current = tab[index++];
          else {
            Node<K,V> e = current;
            current = current.next;
            action.accept(e);
            if (map.modCount != expectedModCount)
              throw new ConcurrentModificationException();
            return true;
          }
        }
      }
      return false;
    }

    public int characteristics() {
      return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
          Spliterator.DISTINCT;
    }
  }



  /* ------------------------------------------------------------ */
  // LinkedHashMap 来提供以下的功能

  Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
    return new Node<>(hash, key, value, next);
  }

  // For conversion from TreeNodes to plain nodes
  Node<K,V> replacementNode(Node<K,V> p, Node<K,V> next) {
    return new Node<>(p.hash, p.key, p.value, next);
  }

  // Create a tree bin node
  TreeNode<K,V> newTreeNode(int hash, K key, V value, Node<K,V> next) {
    return new TreeNode<>(hash, key, value, next);
  }

  // For treeifyBin
  TreeNode<K,V> replacementTreeNode(Node<K,V> p, Node<K,V> next) {
    return new TreeNode<>(p.hash, p.key, p.value, next);
  }
  /**
   * 重新初始化hashMap
   */
  void reinitialize() {
    table = null;
    entrySet = null;
    keySet = null;
    values = null;
    modCount = 0;
    threshold = 0;
    size = 0;
  }



  void afterNodeAccess(Node<K, V> p) {
  }

  void afterNodeInsertion(boolean evict) {
  }

  void afterNodeRemoval(Node<K, V> p) {
  }

  static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;  // red-black tree links
    TreeNode<K,V> left;
    TreeNode<K,V> right;
    TreeNode<K,V> prev;    // needed to unlink next upon deletion
    boolean red;
    TreeNode(int hash, K key, V val, Node<K,V> next) {
      super(hash, key, val, next);
    }

    /**
     * Returns root of tree containing this node.
     */
    final TreeNode<K,V> root() {
      for (TreeNode<K,V> r = this, p;;) {
        if ((p = r.parent) == null)
          return r;
        r = p;
      }
    }

    /**
     * Ensures that the given root is the first node of its bin.
     */
    static <K,V> void moveRootToFront(Node<K,V>[] tab, TreeNode<K,V> root) {
      int n;
      if (root != null && tab != null && (n = tab.length) > 0) {
        int index = (n - 1) & root.hash;
        TreeNode<K,V> first = (TreeNode<K,V>)tab[index];
        if (root != first) {
          Node<K,V> rn;
          tab[index] = root;
          TreeNode<K,V> rp = root.prev;
          if ((rn = root.next) != null)
            ((TreeNode<K,V>)rn).prev = rp;
          if (rp != null)
            rp.next = rn;
          if (first != null)
            first.prev = root;
          root.next = first;
          root.prev = null;
        }
        assert checkInvariants(root);
      }
    }

    /**
     * Finds the node starting at root p with the given hash and key.
     * The kc argument caches comparableClassFor(key) upon first use
     * comparing keys.
     */
    final TreeNode<K,V> find(int h, Object k, Class<?> kc) {
      TreeNode<K,V> p = this;
      do {
        int ph, dir; K pk;
        TreeNode<K,V> pl = p.left, pr = p.right, q;
        if ((ph = p.hash) > h)
          p = pl;
        else if (ph < h)
          p = pr;
        else if ((pk = p.key) == k || (k != null && k.equals(pk)))
          return p;
        else if (pl == null)
          p = pr;
        else if (pr == null)
          p = pl;
        else if ((kc != null ||
            (kc = comparableClassFor(k)) != null) &&
            (dir = compareComparables(kc, k, pk)) != 0)
          p = (dir < 0) ? pl : pr;
        else if ((q = pr.find(h, k, kc)) != null)
          return q;
        else
          p = pl;
      } while (p != null);
      return null;
    }

    /**
     * Calls find for root node.
     */
    final TreeNode<K,V> getTreeNode(int h, Object k) {
      return ((parent != null) ? root() : this).find(h, k, null);
    }

    static int tieBreakOrder(Object a, Object b) {
      int d;
      if (a == null || b == null ||
          (d = a.getClass().getName().
              compareTo(b.getClass().getName())) == 0)
        d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
            -1 : 1);
      return d;
    }

    final void treeify(Node<K,V>[] tab) {
      TreeNode<K,V> root = null;
      for (TreeNode<K,V> x = this, next; x != null; x = next) {
        next = (TreeNode<K,V>)x.next;
        x.left = x.right = null;
        if (root == null) {
          x.parent = null;
          x.red = false;
          root = x;
        }
        else {
          K k = x.key;
          int h = x.hash;
          Class<?> kc = null;
          for (TreeNode<K,V> p = root;;) {
            int dir, ph;
            K pk = p.key;
            if ((ph = p.hash) > h)
              dir = -1;
            else if (ph < h)
              dir = 1;
            else if ((kc == null &&
                (kc = comparableClassFor(k)) == null) ||
                (dir = compareComparables(kc, k, pk)) == 0)
              dir = tieBreakOrder(k, pk);

            TreeNode<K,V> xp = p;
            if ((p = (dir <= 0) ? p.left : p.right) == null) {
              x.parent = xp;
              if (dir <= 0)
                xp.left = x;
              else
                xp.right = x;
              root = balanceInsertion(root, x);
              break;
            }
          }
        }
      }
      moveRootToFront(tab, root);
    }

    /**
     * Returns a list of non-TreeNodes replacing those linked from
     * this node.
     */
    final Node<K,V> untreeify(HashMap<K,V> map) {
      Node<K,V> hd = null, tl = null;
      for (Node<K,V> q = this; q != null; q = q.next) {
        Node<K,V> p = map.replacementNode(q, null);
        if (tl == null)
          hd = p;
        else
          tl.next = p;
        tl = p;
      }
      return hd;
    }

    /**
     * Tree version of putVal.
     */
    final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
        int h, K k, V v) {
      Class<?> kc = null;
      boolean searched = false;
      TreeNode<K,V> root = (parent != null) ? root() : this;
      for (TreeNode<K,V> p = root;;) {
        int dir, ph; K pk;
        if ((ph = p.hash) > h)
          dir = -1;
        else if (ph < h)
          dir = 1;
        else if ((pk = p.key) == k || (k != null && k.equals(pk)))
          return p;
        else if ((kc == null &&
            (kc = comparableClassFor(k)) == null) ||
            (dir = compareComparables(kc, k, pk)) == 0) {
          if (!searched) {
            TreeNode<K,V> q, ch;
            searched = true;
            if (((ch = p.left) != null &&
                (q = ch.find(h, k, kc)) != null) ||
                ((ch = p.right) != null &&
                    (q = ch.find(h, k, kc)) != null))
              return q;
          }
          dir = tieBreakOrder(k, pk);
        }

        TreeNode<K,V> xp = p;
        if ((p = (dir <= 0) ? p.left : p.right) == null) {
          Node<K,V> xpn = xp.next;
          TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
          if (dir <= 0)
            xp.left = x;
          else
            xp.right = x;
          xp.next = x;
          x.parent = x.prev = xp;
          if (xpn != null)
            ((TreeNode<K,V>)xpn).prev = x;
          moveRootToFront(tab, balanceInsertion(root, x));
          return null;
        }
      }
    }

    final void removeTreeNode(HashMap<K,V> map, Node<K,V>[] tab,
        boolean movable) {
      int n;
      if (tab == null || (n = tab.length) == 0)
        return;
      int index = (n - 1) & hash;
      TreeNode<K,V> first = (TreeNode<K,V>)tab[index], root = first, rl;
      TreeNode<K,V> succ = (TreeNode<K,V>)next, pred = prev;
      if (pred == null)
        tab[index] = first = succ;
      else
        pred.next = succ;
      if (succ != null)
        succ.prev = pred;
      if (first == null)
        return;
      if (root.parent != null)
        root = root.root();
      if (root == null || root.right == null ||
          (rl = root.left) == null || rl.left == null) {
        tab[index] = first.untreeify(map);  // too small
        return;
      }
      TreeNode<K,V> p = this, pl = left, pr = right, replacement;
      if (pl != null && pr != null) {
        TreeNode<K,V> s = pr, sl;
        while ((sl = s.left) != null) // find successor
          s = sl;
        boolean c = s.red; s.red = p.red; p.red = c; // swap colors
        TreeNode<K,V> sr = s.right;
        TreeNode<K,V> pp = p.parent;
        if (s == pr) { // p was s's direct parent
          p.parent = s;
          s.right = p;
        }
        else {
          TreeNode<K,V> sp = s.parent;
          if ((p.parent = sp) != null) {
            if (s == sp.left)
              sp.left = p;
            else
              sp.right = p;
          }
          if ((s.right = pr) != null)
            pr.parent = s;
        }
        p.left = null;
        if ((p.right = sr) != null)
          sr.parent = p;
        if ((s.left = pl) != null)
          pl.parent = s;
        if ((s.parent = pp) == null)
          root = s;
        else if (p == pp.left)
          pp.left = s;
        else
          pp.right = s;
        if (sr != null)
          replacement = sr;
        else
          replacement = p;
      }
      else if (pl != null)
        replacement = pl;
      else if (pr != null)
        replacement = pr;
      else
        replacement = p;
      if (replacement != p) {
        TreeNode<K,V> pp = replacement.parent = p.parent;
        if (pp == null)
          root = replacement;
        else if (p == pp.left)
          pp.left = replacement;
        else
          pp.right = replacement;
        p.left = p.right = p.parent = null;
      }

      TreeNode<K,V> r = p.red ? root : balanceDeletion(root, replacement);

      if (replacement == p) {  // detach
        TreeNode<K,V> pp = p.parent;
        p.parent = null;
        if (pp != null) {
          if (p == pp.left)
            pp.left = null;
          else if (p == pp.right)
            pp.right = null;
        }
      }
      if (movable)
        moveRootToFront(tab, r);
    }


    final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
      TreeNode<K,V> b = this;
      // Relink into lo and hi lists, preserving order
      TreeNode<K,V> loHead = null, loTail = null;
      TreeNode<K,V> hiHead = null, hiTail = null;
      int lc = 0, hc = 0;
      for (TreeNode<K,V> e = b, next; e != null; e = next) {
        next = (TreeNode<K,V>)e.next;
        e.next = null;
        if ((e.hash & bit) == 0) {
          if ((e.prev = loTail) == null)
            loHead = e;
          else
            loTail.next = e;
          loTail = e;
          ++lc;
        }
        else {
          if ((e.prev = hiTail) == null)
            hiHead = e;
          else
            hiTail.next = e;
          hiTail = e;
          ++hc;
        }
      }

      if (loHead != null) {
        if (lc <= UNTREEIFY_THRESHOLD)
          tab[index] = loHead.untreeify(map);
        else {
          tab[index] = loHead;
          if (hiHead != null) // (else is already treeified)
            loHead.treeify(tab);
        }
      }
      if (hiHead != null) {
        if (hc <= UNTREEIFY_THRESHOLD)
          tab[index + bit] = hiHead.untreeify(map);
        else {
          tab[index + bit] = hiHead;
          if (loHead != null)
            hiHead.treeify(tab);
        }
      }
    }

    /* ------------------------------------------------------------ */
    // Red-black tree methods, all adapted from CLR

    static <K,V> TreeNode<K,V> rotateLeft(TreeNode<K,V> root,
        TreeNode<K,V> p) {
      TreeNode<K,V> r, pp, rl;
      if (p != null && (r = p.right) != null) {
        if ((rl = p.right = r.left) != null)
          rl.parent = p;
        if ((pp = r.parent = p.parent) == null)
          (root = r).red = false;
        else if (pp.left == p)
          pp.left = r;
        else
          pp.right = r;
        r.left = p;
        p.parent = r;
      }
      return root;
    }

    static <K,V> TreeNode<K,V> rotateRight(TreeNode<K,V> root,
        TreeNode<K,V> p) {
      TreeNode<K,V> l, pp, lr;
      if (p != null && (l = p.left) != null) {
        if ((lr = p.left = l.right) != null)
          lr.parent = p;
        if ((pp = l.parent = p.parent) == null)
          (root = l).red = false;
        else if (pp.right == p)
          pp.right = l;
        else
          pp.left = l;
        l.right = p;
        p.parent = l;
      }
      return root;
    }

    static <K,V> TreeNode<K,V> balanceInsertion(TreeNode<K,V> root,
        TreeNode<K,V> x) {
      x.red = true;
      for (TreeNode<K,V> xp, xpp, xppl, xppr;;) {
        if ((xp = x.parent) == null) {
          x.red = false;
          return x;
        }
        else if (!xp.red || (xpp = xp.parent) == null)
          return root;
        if (xp == (xppl = xpp.left)) {
          if ((xppr = xpp.right) != null && xppr.red) {
            xppr.red = false;
            xp.red = false;
            xpp.red = true;
            x = xpp;
          }
          else {
            if (x == xp.right) {
              root = rotateLeft(root, x = xp);
              xpp = (xp = x.parent) == null ? null : xp.parent;
            }
            if (xp != null) {
              xp.red = false;
              if (xpp != null) {
                xpp.red = true;
                root = rotateRight(root, xpp);
              }
            }
          }
        }
        else {
          if (xppl != null && xppl.red) {
            xppl.red = false;
            xp.red = false;
            xpp.red = true;
            x = xpp;
          }
          else {
            if (x == xp.left) {
              root = rotateRight(root, x = xp);
              xpp = (xp = x.parent) == null ? null : xp.parent;
            }
            if (xp != null) {
              xp.red = false;
              if (xpp != null) {
                xpp.red = true;
                root = rotateLeft(root, xpp);
              }
            }
          }
        }
      }
    }

    static <K,V> TreeNode<K,V> balanceDeletion(TreeNode<K,V> root,
        TreeNode<K,V> x) {
      for (TreeNode<K,V> xp, xpl, xpr;;)  {
        if (x == null || x == root)
          return root;
        else if ((xp = x.parent) == null) {
          x.red = false;
          return x;
        }
        else if (x.red) {
          x.red = false;
          return root;
        }
        else if ((xpl = xp.left) == x) {
          if ((xpr = xp.right) != null && xpr.red) {
            xpr.red = false;
            xp.red = true;
            root = rotateLeft(root, xp);
            xpr = (xp = x.parent) == null ? null : xp.right;
          }
          if (xpr == null)
            x = xp;
          else {
            TreeNode<K,V> sl = xpr.left, sr = xpr.right;
            if ((sr == null || !sr.red) &&
                (sl == null || !sl.red)) {
              xpr.red = true;
              x = xp;
            }
            else {
              if (sr == null || !sr.red) {
                if (sl != null)
                  sl.red = false;
                xpr.red = true;
                root = rotateRight(root, xpr);
                xpr = (xp = x.parent) == null ?
                    null : xp.right;
              }
              if (xpr != null) {
                xpr.red = (xp == null) ? false : xp.red;
                if ((sr = xpr.right) != null)
                  sr.red = false;
              }
              if (xp != null) {
                xp.red = false;
                root = rotateLeft(root, xp);
              }
              x = root;
            }
          }
        }
        else { // symmetric
          if (xpl != null && xpl.red) {
            xpl.red = false;
            xp.red = true;
            root = rotateRight(root, xp);
            xpl = (xp = x.parent) == null ? null : xp.left;
          }
          if (xpl == null)
            x = xp;
          else {
            TreeNode<K,V> sl = xpl.left, sr = xpl.right;
            if ((sl == null || !sl.red) &&
                (sr == null || !sr.red)) {
              xpl.red = true;
              x = xp;
            }
            else {
              if (sl == null || !sl.red) {
                if (sr != null)
                  sr.red = false;
                xpl.red = true;
                root = rotateLeft(root, xpl);
                xpl = (xp = x.parent) == null ?
                    null : xp.left;
              }
              if (xpl != null) {
                xpl.red = (xp == null) ? false : xp.red;
                if ((sl = xpl.left) != null)
                  sl.red = false;
              }
              if (xp != null) {
                xp.red = false;
                root = rotateRight(root, xp);
              }
              x = root;
            }
          }
        }
      }
    }

    /**
     * Recursive invariant check
     */
    static <K,V> boolean checkInvariants(TreeNode<K,V> t) {
      TreeNode<K,V> tp = t.parent, tl = t.left, tr = t.right,
          tb = t.prev, tn = (TreeNode<K,V>)t.next;
      if (tb != null && tb.next != t)
        return false;
      if (tn != null && tn.prev != t)
        return false;
      if (tp != null && t != tp.left && t != tp.right)
        return false;
      if (tl != null && (tl.parent != t || tl.hash > t.hash))
        return false;
      if (tr != null && (tr.parent != t || tr.hash < t.hash))
        return false;
      if (t.red && tl != null && tl.red && tr != null && tr.red)
        return false;
      if (tl != null && !checkInvariants(tl))
        return false;
      if (tr != null && !checkInvariants(tr))
        return false;
      return true;
    }
  }


}
