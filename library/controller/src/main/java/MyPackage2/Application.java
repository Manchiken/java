package MyPackage2;

import MyPackage1.Author;
import MyPackage1.Book;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        String authorName = args[0];
        Library library = LibraryFactory.getLibrary();
        System.out.println("LIBRARY: " + library.getLibraryName());

//        System.out.println("======================== FIRST WAY ===============");
//
//        Author findAuthor = library
//                .getAuthors()
//                .stream()
//                .filter(author -> author.getFirstName().equals(authorName))
//                .findFirst()
//                .orElseThrow(() -> new NullPointerException("Not found author " + authorName));
//        prettyPrintInfoAboutAuthor(findAuthor);

        //second patho to find author by book collection
        System.out.println("======================== SECOND WAY ===============");
        List<Book> authorBooks = new ArrayList<>();
        Set<Author> findAuthor2 = new HashSet<>();
        library
                .getBooks()
                .forEach(book -> book
                        .getAuthors()
                        .stream()
                        .filter(author -> author.getFirstName().equals(authorName))
                        .forEach(author -> {
                            findAuthor2.add(author);
                            authorBooks.add(book);
                        })
                );

        findAuthor2.forEach(Application::printJson);
        authorBooks.forEach(Application::printJson);
    }

    public static void printAuthor(Author author) {
        System.out.println("Author: " + author.getFirstName() + " " + author.getSecondName());
    }

    public static void printBook(Book book) {
        System.out.println("Book: " + book.getBookName() + " " + book.getIssueDate());
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
