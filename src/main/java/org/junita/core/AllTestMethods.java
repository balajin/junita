package org.junita.core;


import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : Balaji Narain
 */
public class AllTestMethods extends TargetAggregate<Method> {

    private TargetProxy targetProxy;

    public AllTestMethods() {
        super();
        targetProxy = new TargetProxy();
    }

    public AllTestMethods(TargetProxy targetProxy) {
        this.targetProxy = targetProxy;
    }

    public void run(TestClass o, RunNotifier notifier)
            throws IllegalAccessException,
            InstantiationException,
            InvocationTargetException {

        for (Method method : testTargets) {
            targetProxy.invokeMethod(method, o.newInstance());
        }
    }
}
