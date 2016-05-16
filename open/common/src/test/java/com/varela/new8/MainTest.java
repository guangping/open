package com.varela.new8;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lance on 2016/5/16.
 */
public class MainTest {


    @Test
    public void run() {
        Stream<String> stream = Stream.of("a", "b", "c", "d", null);
     /*   long size = stream.filter(s -> s != null).count();
        System.out.println(size);*/

        //stream.filter(s -> s != null).forEach(System.out::println);

        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("a");

        //list.stream().forEach(System.out::println);

        List itmes=list.stream().filter(s -> s.contains("a")).collect(Collectors.toList());
        System.out.println(itmes);



    }
}
