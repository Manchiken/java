package other;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import modules.CombineModule;
import modules.ConsoleOnlyModule;
import modules.FileOnlyModule;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    @Inject
    LogMethod logger;
    public static Integer count = 0;

    public static void main(String[] args) {

        final Injector injector = Guice.createInjector(getModule(args[0]));
        injector.getInstance(Application.class).waitForInput();

    }

    void waitForInput() {
        String phrase = null;
        ArrayList<String> array = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");
            System.out.println("Please input:");
            while (true) {
                /*your code here*/
                phrase = scanner.nextLine();
                array.add(phrase);
            }
        } catch (IllegalStateException | NoSuchElementException e) {

        }
        array.forEach(logger::log);
    }

    private static AbstractModule getModule(String logType) {
        switch (logType) {
            case ("console"):
                return new FileOnlyModule();
            case ("file"):
                return new ConsoleOnlyModule();
            default:
                return new CombineModule();
        }
    }
}
