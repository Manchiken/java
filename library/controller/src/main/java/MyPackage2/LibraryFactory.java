package MyPackage2;

import MyPackage1.Author;
import MyPackage1.Book;
import com.github.javafaker.Faker;

import java.time.ZonedDateTime;

public class LibraryFactory {

    public static Library getLibrary() {
        return staticLibrary(new Faker().pokemon().name());
    }

    private static final Library staticLibrary(String name) {
        Library library = new Library(name);

        //Author1
        Book book1Author1 = new Book();
        book1Author1.setBookName("Book1Author1");
        book1Author1.setIssueDate(ZonedDateTime.now());
        Book book2Author1 = new Book();
        book2Author1.setBookName("Book2Author1");
        book2Author1.setIssueDate(ZonedDateTime.now().minusDays(1));
        Author author1 = new Author();
        author1.setFirstName("Author1");
        author1.setSecondName("AuthorSecondName1");
       // author1.getBooks().add(book1Author1);
      //  author1.getBooks().add(book2Author1);
        book1Author1.getAuthors().add(author1);
        book2Author1.getAuthors().add(author1);

        //Author2
        Book book1Author2 = new Book();
        book1Author1.setBookName("Book1Author2");
        book1Author1.setIssueDate(ZonedDateTime.now().minusYears(2).minusDays(20));
        Author author2 = new Author();
        author2.setFirstName("Author2");
        author2.setSecondName("AuthorSecondName2");
       // author2.getBooks().add(book1Author2);
        book1Author2.getAuthors().add(author2);

        library.getAuthors().add(author1);
        library.getAuthors().add(author2);
        library.getBooks().add(book1Author1);
        library.getBooks().add(book2Author1);
        library.getBooks().add(book1Author2);
        return library;
    }
}
