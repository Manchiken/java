package modules;

import com.google.inject.AbstractModule;
import other.LogMethod;
import other.FileLog;

public class FileOnlyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LogMethod.class).to(FileLog.class);
    }

}
