package tests;

import MyPackage1.Book;
import MyPackage2.BooksFactory;
import MyPackage2.Library;
import MyPackage2.LibraryFactory;
import MyPackage2.ModuleForIOC;
import MyPackage2.StaticBooksFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.util.Modules;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tests2 {

    @NotNull
    private BooksFactory booksFactory = Mockito.spy(new StaticBooksFactory());
    Injector injector;

    @Before
    public void setUpTest() {
        injector =
                Guice.createInjector(Modules
                        .override(new ModuleForIOC())
                        .with(new Tests2.TestModule())
                );
        injector.injectMembers(this);
    }

    //1
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void checkExceptionWhenCreate() {
        int tooLowSize = 1;
        assertTrue(booksFactory.books().size() > tooLowSize);
        injector.getInstance(LibraryFactory.class).library(tooLowSize);
    }

    //2
    @Test
    public void checkOrderAll() {
        Book first = new Book("first");
        Book second = new Book("second");
        Mockito
                .when(booksFactory.books())
                .thenReturn(asList(first, second));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outContent);
        System.setOut(stream);

        Library library = injector.getInstance(LibraryFactory.class).library(3);
        library.prettyPrint(true);

        assertEquals("first second Empty Cell: 2 ", outContent.toString().replace("\r", "").replace("\n", " "));

    }

    //4
    @Test(expected = IndexOutOfBoundsException.class)
    public void checkInfoPrint() {
        Library library = injector.getInstance(LibraryFactory.class).library(3);
        library.takeBook(3);
    }

    //4
    @Test
    public void takeEmptyCell() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream stream = Mockito.spy(new PrintStream(outContent));
        System.setOut(stream);
        Library library = injector.getInstance(LibraryFactory.class).library(3);
        library.takeBook(0);
        Mockito.verify(stream).println("Take index: 0; Book: Book1Author1");
    }

    //5
    @Test
    public void checkOrder() {
        Book first = new Book("first");
        Book second = new Book("second");
        Mockito
                .when(booksFactory.books())
                .thenReturn(asList(first, second));
        Library library = injector.getInstance(LibraryFactory.class).library(3);
        assertEquals(first, library.takeBook(0));
        assertEquals(second, library.takeBook(1));
        assertEquals(Library.emptyCell, library.takeBookAnyway(2));
    }

    //6
    @Test
    public void putFirstEmpty() {
        Book first = new Book("first");
        Book second = new Book("second");

        Mockito
                .when(booksFactory.books())
                .thenReturn(asList(first));
        Library library = injector.getInstance(LibraryFactory.class).library(3);
        assertEquals(Library.emptyCell, library.takeBookAnyway(1)); //check it's empty
        library.addBook(second);
        assertEquals(first, library.takeBook(0));
        assertEquals(second, library.takeBook(1));
        assertEquals(Library.emptyCell, library.takeBookAnyway(2));
    }

    //7
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void noMoreSpace() {
        Book first = new Book("first");
        Book second = new Book("second");

        Mockito
                .when(booksFactory.books())
                .thenReturn(asList(first));
        Library library = injector.getInstance(LibraryFactory.class).library(1);
        library.addBook(second);
    }

    //8
    @Test
    public void checkPrettyPrint() {
        Book first = new Book("first");
        Mockito
                .when(booksFactory.books())
                .thenReturn(asList(first));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outContent);
        System.setOut(stream);

        Library library = injector.getInstance(LibraryFactory.class).library(3);
        library.prettyPrint(true);

        assertEquals("first Empty Cell: 1 Empty Cell: 2 ", outContent.toString().replace("\r", "").replace("\n", " "));
    }

    @After
    public void tearDownTest() {
        Mockito.reset(booksFactory);
    }

    private final class TestModule extends AbstractModule {
        @Override
        public final synchronized void configure() {
            bind(LibraryFactory.class);
        }

        @Provides
        BooksFactory provideBooksFactory() {
            return booksFactory;
        }
    }
}
