package cn.ycl.designmode.iterator;

import java.util.Iterator;

public class SheetIterator implements Iterator<Book> {
    Book[] books;
    private int index;
    public SheetIterator(Book[] books){
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        if(index >= books.length){
            return false;
        }
        if(books[index] == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Book next() {
        if(index >= books.length){
            return null;
        }
        Book book= books[index];
        index++;
        return book;
    }
}
