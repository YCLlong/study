package cn.ycl.designmode.iterator;

import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 书架类，使用数组实现书本
 */
public class BookSheet implements ICreateIterator<Book> {
    private final Lock lock = new ReentrantLock();
    private Book[] books = new Book[100];
    private Integer num=0;

    @Override
    public Iterator<Book> iterator() {
        /**
         * 巧妙在这个传参。books的数据结构并没有暴露给外部
         * 同时却给SheetIterator迭代器提供了遍历的基础，也就是内部存储书本的数据结构
         * 而SheetIterator的构造函数会暴露内部结构，但是SheetIterator并不会直接面向用户，用户并不知道迭代器的具体是哪个实现（当然真要找也肯定能找到。。。）
         */
        return new SheetIterator(books);
    }

    public void addBook(Book book) {
        lock.lock();
        try{
            books[num] = book;
            num++;
        }finally {
            lock.unlock();
        }
    }

    public void removeBook(Book book) {
        //数组删除要遍历删除，然后移动别的位置的Book。麻烦，这儿就不实现了
    }

    public Integer getNum() {
        return num;
    }
}
