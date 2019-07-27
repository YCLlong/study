package cn.ycl.designmode.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用list作为底层实现书架
 */
public class BookSheetV2 implements ICreateIterator<Book>{
    private List<Book> list = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    @Override
    public Iterator<Book> iterator() {
        return new SheetIteratorV2(list);
    }
    public void addBook(Book book) {
        lock.lock();
        try{
            list.add(book);
        }finally {
            lock.unlock();
        }
    }
}
