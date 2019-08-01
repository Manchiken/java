package MyPackage2;

import MyPackage1.Book;

import java.util.Collection;

public interface BooksFactory {
    Collection<Book> books();
}
