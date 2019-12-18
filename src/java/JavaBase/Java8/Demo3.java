/*
 * Copyright 2019-2022 the original author or authors.
 */

package JavaBase.Java8;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流学习
 */
public class Demo3 {

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

    // 流属于内部迭代，使用流，返回内部迭代中相应的接口：Stream，迭代式在集合类中进行的
    long count = allArtist.stream().filter(artist -> artist.isFrom("London")).count();

    // Stream对象不是一个新的集合，而是创建新集合的配方 TODO 这里的配方怎么理解
    // 其中，上述不产生值或集合的方法称为“惰性求值方法”，生成的是新集合的配方，如filter()；
    //   产生值得方法成为“及早求值方法”，如count()

    // 一般使用流式编程，就是使用多个惰性求值方法处理，最后再用一个及早求值方法获取想要得结果
    // 有点像建造者模式

    // 常用的流操作

    // collect(toList()) 由Stream里的值生成一个集合，集合可为很多,如list，set，map，currentMap，都在Collectors里
    // 定制集合collect(toCollection(TreeSet::new))，通过toCollection实现，新建一个所需集合，传进去即可
    // 属于及早求值方法
    List<String> colleted = Stream.of("a", "b", "c").collect(Collectors.toList());
    Set<String> set = Stream.of("a", "b", "c").collect(Collectors.toSet());

    // map 使用函数或代码块将Stream中的值从一种类型转换为另一种类型
    List<String> mapString = Stream.of("a", "b", "hello")
        .map(string -> string.toUpperCase())
        .collect(toList());

    // filter, 检查元素是否满足条件，返回true的元素会留下来
    List<String> filterString = Stream.of("a", "1abc", "abc1")
        .filter(value -> value.startsWith("a")).collect(toList());

    // flatMap 可用于用Stream替换值，然后把多个Stream连接成一个Stream
    List<Integer> flatMapInt = Stream.of(Stream.of(1, 2), Stream.of(3, 4))
        .flatMap(number -> number).collect(toList());

    // min lambda表达式里面传入Comparator对象，和要比较的内容，最后用get()获取最小的
    allArtist.add(new Artist("Tim", allArtist, "London"));
    Artist minArtist = allArtist.stream()
        .min(comparing(artist -> artist.members.size())).get();
    // 还能这么拆解
    Stream<Artist> artistStreams = allArtist.stream();
    Function<Artist, Long> getCount = artistStream -> artistStream.getMembers().count();
    Artist art = artistStreams.max(comparing(getCount)).get();
    // max 同上

    // 对于get()，返回Optional对象中的值，表示一个可能存在，也可能不存在的值(Stream为null是，就不存在)

    // reduce 实现累加
    int reduce = Stream.of(1, 2, 3)
        .reduce(0, (acc, element) -> acc + element);

    // Integer::sum 这种方式叫做方法引用，所有传入lambda表达式的地方都可以使用，语法：Classname::methodName
    // 例如：新建对象 User::new, String[]::new
    reduce = Stream.of(1, 2, 3)
        .reduce(0, Integer::sum); // 这里连参数都省了

    // 关于元素顺序的问题：流是有序的，
    // 只要原集合是有序的，流就会有序处理，如果无序的，就不能保证每次执行都以相同顺序处理
    // 如果原来无序(有序), 通过有序（无序）操作后，会变为有序（无序）
    reduce = Stream.of(3, 1, 2)
        .sorted()
        .reduce(0, Integer::sum);

    // 关于顺序上的性能：
    // 大多数操作都是在有序上效率更高，如filter，map，reduce
    // 一些操作在有序流上开销更大，可通过unordered消除顺序
    // 在并行流上，forEach操作不能保证顺序处理，得使用forEachOrder方法

    // Predicate,分类函数
    // 数据分块 partitioningBy(), 传入Predicate, 分成true和false两块
    Map<Boolean, List<Artist>> partitionMap = bandsAndSolo(allArtist.stream());

    // 数据分组 groupingBy,传入Predicate, 按不同的主演奏者分类
    Map<Artist, List<Album>> groupingMap = albumByArtist(albums.stream());

    // 字符串连接 ,joining收集字符串中的值，并在加上前后缀
    String result = allArtist.stream()
        .map(Artist::getName)
        .collect(Collectors.joining(",", "[", "]"));

    // 组合收集器
    Map<Artist, Long> togetherAlbumsCount = numberOfAlbum(albums.stream());

    Map<Artist, List<String>> togetherAlbumsName = nameOfAlbums(albums.stream());

    // peak 常用于日志记录，用于查看流中元素
    Set<String> nationalities
        = album.getMusicians()
               .filter(artist -> artist.getName().startsWith("The"))
               .map(artist -> artist.getNationality())
               .peek(nation -> System.out.println("Found nationality: " + nation))
               .collect(Collectors.toSet());


  }

  // 数据分块 partitioningBy(), 传入Predicate
  public static Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
    return artists.collect(partitioningBy(artist -> artist.isSolo()));
  }

  // 数据分组 groupingBy,传入Predicate, 按不同的主演奏者分类
  public static Map<Artist, List<Album>> albumByArtist(Stream<Album> albums) {
    return albums.collect(groupingBy(album -> album.getMainMusician()));
  }

  // 使用收集器计算每个艺术家的专辑数, counting()组合进来，多做一次处理
  public static Map<Artist, Long> numberOfAlbum(Stream<Album> albums) {
    return albums.collect(groupingBy(album -> album.getMainMusician(), counting()));
  }

  // 使用收集器收集每个艺术家的专辑名，mapping()属于下游收集器，会被上游收集器调用
  // mapping()允许在收集器的容器的基础上执行类似map的操作，需要指明使用的集合
  public static Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
    return albums.collect(
        groupingBy(album -> album.getMainMusician(), mapping(album -> album.getName(), toList())));
  }

  /**
   * 自定义收集器 StringCollector 完成字符串连接，前后用"[","]"括起来，元素间用“，”分隔
   *
   * @param artists
   * @return
   */
  public static String formatArtistsRefactor5(List<Artist> artists) {
    // BEGIN refactor_5
    String result =
        artists.stream()
            .map(Artist::getName)
            .collect(new StringCollector(", ", "[", "]"));
    // END refactor_5
    return result;
  }
}

