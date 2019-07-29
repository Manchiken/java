package other;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public final class Call implements MethodInterceptor {
    private static int count = 1;

    @Override
    public Object invoke( MethodInvocation invocation) throws Throwable {
        if (count++ % 3 == 0) {
            System.out.println("3-fold input:");
        }
        return invocation.proceed();
    }
}
