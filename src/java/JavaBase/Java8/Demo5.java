/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 并行流
 */
public class Demo5 {

  public static void main(String[] args) {
    List<Artist> allArtist = new ArrayList<>();
    allArtist.add(new Artist("jack", "London"));
    allArtist.add(new Artist("wangchang", "China"));
    allArtist.add(new Artist("tom", "London"));

    List<Track> tracks = new ArrayList<>();
    tracks.add(new Track("track1", 1));
    tracks.add(new Track("track2", 2));
    tracks.add(new Track("track3", 3));
    Album album = new Album("album", tracks, allArtist);
    List<Album> albums = new ArrayList<>();
    albums.add(album);

    // 并行化计算专辑曲目长度
    int sum = albums.parallelStream()
        .flatMap(Album::getTracks)
        .mapToInt(Track::getLength)
        .sum();

    // 使用限制：1、初始值为任意值；2、组合操作满足结合律
    // 考虑因素：
    // 1、数据大小，数据很少就没必要使用并行了
    // 2、源数据结构：不同的数据结构，分解操作的性能不同
    //    性能好（ArrayList、数组、IntStream.range等支持随机读取的）
    //    性能一般（HashSet,TreeSet,因为不易公平分解）
    //    性能差（LinkedList、Stream.iterate、BufferedRead.lines等，可能分解得O(n)）
    // 3、装箱，基本类型比装箱更快
    // 4、计算机核的数量，核心越多
    // 5、单元处理开销，如果单元处理开销大，带来的提升更大

    // 流单独操作每一块时，操作分未无状态(map、filter、flatmap)和有状态(sorted、distinct、limit),
    // 无状态操作的并行性能更快

    // 底层实现：fork/join框架

    // 并行化数组操作，操作在Arrays工具类中
    // parallelPrefix 给定一个函数，计算数组的和
    double[] doubles;
    doubles = parallelInitialize(10);
    Arrays.parallelPrefix(doubles,Double::sum);
    // parallelSetAll 使用lambda表达式更新数组元素
    doubles = parallelInitialize(100000);

    // parallelSort 并行化对数组元素排序

  }

  /**
   * 并行化序列化数组
   * @param size 数组大小
   * @return 数组
   */
  public static double[] parallelInitialize(int size) {
    double[] values = new double[size];
    // 给每个数组元素赋予其序号为其值
    Arrays.parallelSetAll(values, i -> i);
    return values;
  }




}
