package MyPackage2;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ModuleForIOC extends AbstractModule {
    public static String fileName = "books.json"; //looks not safe

    @Override
    public final synchronized void configure() {
        bind(LibraryFactory.class);
    }

    @Provides
    BooksFactory provideBooksFactory() {
        return new FileBookFactory(fileName);
    }
}
