package MyPackage2;


import MyPackage1.Author;
import MyPackage1.Book;
import lombok.Data;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
public class Library {
    List<Author> authors = new LinkedList<>();
    List<Book> books = new LinkedList<>();
    final String libraryName;
    final LinkedList<Book> booksArray;
    public static final Book emptyCell = null;
    static final String outOfBoundsMessage = "Too low size";

    public Library(String name, int size, Collection<Book> listBook) {
        if (size < listBook.size()) {
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMessage + " Actual size: " + listBook.size());
        }
        this.libraryName = name;
        this.booksArray = initBookStorage(size);
        listBook.stream().forEachOrdered(this::addBook); //I hope that keeps the original order as well
    }

    private LinkedList<Book> initBookStorage(int storageSize) {
        LinkedList<Book> booksStorage = new LinkedList<>();
        for (int i = 0; i < storageSize; i++) {
            booksStorage.add(emptyCell);
        }
        return booksStorage;
    }

    public Book takeBookAnyway(int i) {
        Book returnBook = booksArray.get(i);
        booksArray.set(i, emptyCell);
        if (returnBook != emptyCell) {
            System.out.println("Take index: " + i + "; Book: " + returnBook.getName());
        }
        return returnBook;
    }

    public Book takeBook(int i) {
        Book returnBook = booksArray.get(i);
        if(returnBook==null){
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMessage);
        }
        booksArray.set(i, emptyCell);
            System.out.println("Take index: " + i + "; Book: " + returnBook.getName());
        return returnBook;
    }

    public void addBook(Book newBook) {
        int index = booksArray.indexOf(emptyCell);
        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMessage);
        }
        booksArray.set(index, newBook);
    }

    public void prettyPrint(boolean printEmptyCells) {
        for (int i = 0; i < booksArray.size(); i++) {
            Book currentCell = booksArray.get(i);
            if (currentCell == emptyCell) {
                if (printEmptyCells) {
                    System.out.println("Empty Cell: " + i);
                }
            } else {
                System.out.println(currentCell.getName());
            }
        }
    }
}
