package MyPackage1;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Book {
    String bookName;
    ZonedDateTime issueDate;
    List<Author> authors=new ArrayList<>();
}
