package modules;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import other.Call;
import other.CombineLog;
import other.ConsoleAnnotation;
import other.ConsoleLog;
import other.FileAnnotation;
import other.FileLog;
import other.LogMethod;
import other.MethodCallLogging;

public class CombineModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LogMethod.class).to(CombineLog.class);
        bind(LogMethod.class)
                .annotatedWith(FileAnnotation.class)
                .to(FileLog.class);
        bind(LogMethod.class)
                .annotatedWith(ConsoleAnnotation.class)
                .to(ConsoleLog.class);
        bindInterceptor(
                Matchers.any(),
                Matchers.annotatedWith(MethodCallLogging.class),
                new Call()
        );
    }

}
