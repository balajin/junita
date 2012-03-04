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
        for (Method method : invokables) {
            suiteDescription.addChild(Description.createTestDescription(clazz, method.getName()));
        }
    }

    @Override
    public boolean run(TestClass rootClass, RunNotifier notifier) throws Exception {
        for (Method method : invokables) {
            Description description = Description.createTestDescription(rootClass.clazz(), method.getName());
            if (notIgnored(method)) {
                Object instance = rootClass.newInstance();
                notifier.fireTestStarted(description);
                try {
                    targetProxy.invokeMethod(method, instance);
                } catch (InvocationTargetException e) {
                    notifier.fireTestFailure(new Failure(description, e));
                }
                rootClass.destroy(instance);
            } else {
                notifier.fireTestIgnored(description);
            }
            notifier.fireTestFinished(Description.createTestDescription(rootClass.clazz(), method.getName()));
        }
        return true;
    }

    @Override
    public boolean run(TestClass innerClass, Object enclosingInstance, RunNotifier notifier) throws Exception {
        for (Method method : invokables) {
            Description description = Description.createTestDescription(innerClass.clazz(), method.getName());
            if (notIgnored(method)) {
                notifier.fireTestStarted(description);
                try {
                    targetProxy.invokeMethod(method, enclosingInstance);
                } catch (InvocationTargetException e) {
                    notifier.fireTestFailure(new Failure(description, e));
                }
            } else {
                notifier.fireTestIgnored(description);
            }
            notifier.fireTestFinished(Description.createTestDescription(innerClass.clazz(), method.getName()));
        }
        return true;
    }

    private boolean notIgnored(Method method) {
        return !method.isAnnotationPresent(Ignore.class);
    }
}
