package MyPackage2;

import MyPackage1.Book;
import com.github.javafaker.Faker;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

public class LibraryFactory {
    BooksFactory factory;

    @Inject
    public LibraryFactory(BooksFactory factory) {
        this.factory = factory;
    }

    public static Library getLibrary() {
        return staticLibrary(new Faker().pokemon().name());
    }

    private static final Library staticLibrary(String name) {
        Collection<Book> bookFactory = new StaticBooksFactory().books();
        return new Library(name, 3, bookFactory);

    }

    public Library library(int size) {
        String name = new Faker().pokemon().name();
        return new Library(name, size, factory.books());
    }
}
