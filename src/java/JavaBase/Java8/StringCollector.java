/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

// Collector<带收集元素的类型，累加器类型，最终结果类型>
public class StringCollector implements Collector<String, StringCombiner, String> {

  // TODO 有什么用处呢？
  private static final Set<Characteristics> characteristics = Collections.emptySet();

  private final String delim;
  private final String prefix;
  private final String suffix;

  public StringCollector(String delim, String prefix, String suffix) {
    this.delim = delim;
    this.prefix = prefix;
    this.suffix = suffix;
  }
  // 收集器分四部分

  // Suppier是创建容器的工厂
  @Override
  public Supplier<StringCombiner> supplier() {
    return () -> new StringCombiner(delim, prefix, suffix);
  }

  /**
   * 累加器实现
   * 结合之前操作的结果，返回新的值
   * 这里是将流中的值加入supplier创建容器中
   * @return
   */
  @Override
  public BiConsumer<StringCombiner, String> accumulator() {
    return StringCombiner::add;
  }

  /**
   * 如果有多个容器，需要将其合并
   * 这里实现多个容器合并的过程
   * @return
   */
  @Override
  public BinaryOperator<StringCombiner> combiner() {
    return StringCombiner::merge;
  }
  // END combiner

  /**
   * 合并容器后，调用finisher方法返回最终结果
   * @return
   */
  @Override
  // BEGIN finisher
  public Function<StringCombiner, String> finisher() {
    return StringCombiner::toString;
  }
  // END finisher

  @Override
  public Set<Characteristics> characteristics() {
    return characteristics;
  }

}
