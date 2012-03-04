package org.junita.model;


import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.TestClass;

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

    @Override
    public void describe(Description suiteDescription, Class clazz) {
        for (Method method : testTargets) {
            suiteDescription.addChild(Description.createTestDescription(clazz, method.getName()));
        }
    }

    @Override
    public boolean run(TestClass o, RunNotifier notifier) throws Exception {
        Object instance = o.newInstance();
        return run(o, instance, notifier);
    }

    @Override
    public boolean run(TestClass o, Object instance, RunNotifier notifier) throws Exception {
        for (Method method : testTargets) {
            Description description = Description.createTestDescription(o.clazz(), method.getName());
            if (notIgnored(method)) {
                notifier.fireTestStarted(description);
                try {
                    targetProxy.invokeMethod(method, instance);
                } catch (InvocationTargetException e) {
                    notifier.fireTestFailure(new Failure(description, e));
                }
            } else {
                notifier.fireTestIgnored(description);
            }
            notifier.fireTestFinished(Description.createTestDescription(o.clazz(), method.getName()));
        }
        return true;
    }

    private boolean notIgnored(Method method) {
        return !method.isAnnotationPresent(Ignore.class);
    }
}
