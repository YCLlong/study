package cn.ycl.designmode.strategy;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Person p3 = new Person("yyc",8,1);
        Person p4 = new Person("jl",18,1);
        Person p1 = new Person("ycl",24,0);
        Person p2 = new Person("jyf",23,1);
        Person p5 = new Person("ytb",1,0);

        /**
         * 比较方法的具体实现是我们自定义的 PersonComparetor
         * 当我们需要改变比较的逻辑时，不需要改变TreeSet 的实现，只要自己再定义一个Comparator 的实现
         */
        Set<Person> s = new TreeSet<Person>(new PersonComparetor());
        s.add(p1);
        s.add(p4);
        s.add(p5);
        s.add(p2);
        s.add(p3);
        printCollection(s);


    }

    public static <T> void printCollection(Collection<T> c){
        Iterator<T> iterator = c.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
