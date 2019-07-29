package other;

public class ConsoleLog implements LogMethod {
    @Override
    @MethodCallLogging
    public void log(String string) {
        System.out.println(Application.count++ + ":" + string);
    }
}
