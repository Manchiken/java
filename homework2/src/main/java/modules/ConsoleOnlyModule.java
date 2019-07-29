package modules;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import other.Call;
import other.LogMethod;
import other.ConsoleLog;
import other.MethodCallLogging;

public class ConsoleOnlyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LogMethod.class).to(ConsoleLog.class);
        bindInterceptor(
                Matchers.any(),
                Matchers.annotatedWith(MethodCallLogging.class),
                new Call()
        );
    }
}
