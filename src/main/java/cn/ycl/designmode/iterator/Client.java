package cn.ycl.designmode.iterator;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        BookSheet sheet = new BookSheet();
        sheet.addBook(new Book("时间简史","霍金"));
        sheet.addBook(new Book("史上最帅","燕小龙"));
        sheet.addBook(new Book("史上最丑","雕燕灰"));
        printBook(sheet.iterator());

        System.out.println("============V2版本实现===========");
        BookSheetV2 sheetV2 = new BookSheetV2();
        sheetV2.addBook(new Book("时间简史2","霍金2"));
        sheetV2.addBook(new Book("史上最帅2","燕小龙2"));
        sheetV2.addBook(new Book("史上最丑2","雕燕灰2"));
        printBook(sheetV2.iterator());
    }

    /**
     * 打印Book，对于客户端而言，获取书架的所有书本直接通过迭代器获得，统一了获取的方式
     * 而且当书架内部实现的数据结构改变了，客户端完全不需要改变了，这就解耦了
     * @param iterator
     */
    public static void printBook(Iterator<Book> iterator){
        while (iterator.hasNext()){
            Book book = iterator.next();
            System.out.println(book.toString());
        }
    }
}
