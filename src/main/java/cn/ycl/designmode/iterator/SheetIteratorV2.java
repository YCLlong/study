package cn.ycl.designmode.iterator;

import java.util.Iterator;
import java.util.List;

public class SheetIteratorV2 implements Iterator<Book> {
    private List<Book> list;
    private int index;
    public SheetIteratorV2(List<Book> list){
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if(index >= list.size()){
            return false;
        }
        if(list.get(index) == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Book next() {
        if(index >= list.size()){
            return null;
        }
        Book book= list.get(index);
        index++;
        return book;
    }
}
