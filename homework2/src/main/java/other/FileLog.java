package other;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileLog implements LogMethod {
    FileOutputStream fos = new FileOutputStream("notes.txt");
    String tagName = System.getProperty("tagName", "a");

    public FileLog() throws FileNotFoundException {
    }

    @Override
    public void log(String string) {
        String before = "<" + tagName + ">";
        String after = "</" + tagName + ">";

        byte[] buffer = string.getBytes();
        byte[] total = (Application.count++ + ":" + before + new String(buffer) + after + "\n").getBytes();
        try {
            fos.write(total, 0, total.length);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
