package org.example.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class UserLoggedInInterceptor implements MethodInterceptor {

    private final Logger logger = LoggerFactory.getLogger(UserLoggedInInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        try {
            logger.info("proceed start: {}.{}", method.getDeclaringClass().getName(), method.getName());
            return invocation.proceed();
        } finally {
            logger.info("proceed end: {}.{}", method.getDeclaringClass().getName(), method.getName());
        }
    }
}
