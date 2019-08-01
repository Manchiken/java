package MyPackage2;

import MyPackage1.Author;
import MyPackage1.Book;
import com.google.gson.Gson;
import com.google.inject.Guice;

public class Application {
    public static void main(String[] args) {
        int size = Integer.valueOf(args[0]);
        String fileName = args[1];
        if(fileName!=null|| fileName!="") {
            ModuleForIOC.fileName = fileName;
        }

        Library library = Guice
                .createInjector(new ModuleForIOC())
                .getInstance(LibraryFactory.class)
                .library(size);
        System.out.println("LIBRARY: " + library.getLibraryName());
        library.takeBook(2);
//        library.prettyPrint(true);
//
        Book newBook1 = new Book();
        newBook1.setName("TEST1");
        library.addBook(newBook1);
//        library.prettyPrint(true);
//        library.prettyPrint(false);

        Book newBook2 = new Book();
        newBook2.setName("TEST2");
        library.addBook(newBook2);
        library.prettyPrint(true);
        // library.prettyPrint(false);
    }

    public static void printAuthor(Author author) {
        System.out.println("Author: " + author.getName() + " " + author.getSecondName());
    }

    public static void printBook(Book book) {
        System.out.println("Book: " + book.getName() + " " + book.getIssueDate());
    }


    public static <T> void printJson(T object) {
        Gson gson = new Gson();
        System.out.println("JSON: \n" + gson.toJson(object));
    }

    public static void prettyPrintInfoAboutAuthor(Author author) {
        printAuthor(author);
        printJson(author);
//        author.getBooks().forEach(book -> {
//            printBook(book);
//            printJson(book);
//        });
    }
}
