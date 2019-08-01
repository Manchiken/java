package MyPackage2;

import MyPackage1.Author;
import MyPackage1.Book;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StaticBooksFactory  implements BooksFactory{
    @Override
    public Collection<Book> books() {
            LinkedList<Book> bookList = new LinkedList<>();
            //Author1
            Book book1Author1 = new Book();
            book1Author1.setName("Book1Author1");
            book1Author1.setIssueDate(ZonedDateTime.now());
            Book book2Author1 = new Book();
            book2Author1.setName("Book2Author1");
            book2Author1.setIssueDate(ZonedDateTime.now().minusDays(1));
            Author author1 = new Author();
            author1.setName("Author1");
            author1.setSecondName("AuthorSecondName1");
            // author1.getBooks().add(book1Author1);
            //  author1.getBooks().add(book2Author1);
            book1Author1.setAuthor(author1);
            book2Author1.setAuthor(author1);

            //Author2
            Book book1Author2 = new Book();
            book1Author2.setName("Book1Author2");
            book1Author2.setIssueDate(ZonedDateTime.now().minusYears(2).minusDays(20));
            Author author2 = new Author();
            author2.setName("Author2");
            author2.setSecondName("AuthorSecondName2");
            // author2.getBooks().add(book1Author2);
            book1Author2.setAuthor(author2);
            bookList.add(book1Author1);
            bookList.add(book1Author2);
            bookList.add(book2Author1);
            return bookList;
    }
}
