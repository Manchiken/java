package MyPackage2;


import MyPackage1.Author;
import MyPackage1.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Library {
    //private String[] books = new String[];
    Author author = new Author();
    Book book = new Book();
    List<Author> autors = new LinkedList<>();
    //List<Book> books = new LinkedList<>();

    //List<List<int>> books = new List<List<int>>();
    List<ArrayList<Author>> list = new ArrayList<>();

    List<String> book1 = new LinkedList<>();
    List<String> book2 = new LinkedList<>();
    //    List<String> book1 = new LinkedList<>();
    List<String> library = new LinkedList<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public List<String> createLibrary() {
        author.setFirstName("Bill");
        author.setSecondName("Pride");
        book.setBookName("Big Bang");
        book.setIssueDate("18.04.2019");

        book1.add(author.getFirstName() + author.getSecondName());
        book1.add(book.getBookName() + book.getIssueDate());
        String json1 = gson.toJson(book1);
        library.add(json1);
//        book.setBook("Радость");
//        author.setAuthor("Гарилян В.В");
//        book2.add(author.getAuthor());
//        book2.add(book.getBook());
//        String json2 = gson.toJson(book2);
//        library.add(json2);
        return library;
    }

    public void print() {
        System.out.println(createLibrary());
    }
}
