package com.kristin.design_pattern.Iterator;

public class BookShelf implements Aggregate {
    private Book[] book;

    private int last = 0;

    public BookShelf(int maxsize) {
        this.book = new Book[maxsize];
    }

    public Book getBookAt(int last) {
        return book[last];
    }

    public void appendBook(Book book) {
        this.book[last] = book;
        last++;
    }

    public int getLength() {
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
