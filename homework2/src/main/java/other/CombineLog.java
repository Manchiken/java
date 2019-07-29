package other;

import com.google.inject.Inject;

import java.util.ArrayList;

public class CombineLog implements LogMethod {

    @FileAnnotation
    @Inject
    LogMethod fileLog;

    @ConsoleAnnotation
    @Inject
    LogMethod consoleLog;

    @Override
    public void log(String string) {
        consoleLog.log(string);
        fileLog.log(string);

    }
}
