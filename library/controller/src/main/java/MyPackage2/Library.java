package MyPackage2;


import MyPackage1.Author;
import MyPackage1.Book;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Library {
    List<Author> authors = new LinkedList<>();
    List<Book> books = new LinkedList<>();
    final String libraryName;

    public Library(String name) {
        this.libraryName = name;
    }
}
