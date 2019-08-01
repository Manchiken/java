package MyPackage1;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Book {
    String name;
    ZonedDateTime issueDate;
    Author author;

    public Book(String name){
        this.name=name;
    }

    public Book(){
    }
}
