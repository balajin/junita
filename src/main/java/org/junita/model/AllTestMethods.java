package org.junita.model;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.TestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A collection of all test methods
 *
 * @author : Balaji Narain
 */
public class AllTestMethods extends TargetAggregate<Method> {

    private TargetProxy targetProxy;

    public AllTestMethods() {
        super();
        targetProxy = new TargetProxy();
    }

    public AllTestMethods(TestClass testClass) {
        this();
        for (Method method : testClass.clazz().getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                this.add(method);
            }
        }
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
    public boolean run(Method method, Object instance, Description description, RunNotifier notifier) throws Exception {
        Description testDescription = Description.createTestDescription(instance.getClass(), method.getName());
        description.addChild(testDescription);
        if (notIgnored(method)) {
            notifier.fireTestStarted(testDescription);
            try {
                targetProxy.invokeMethod(method, instance);
            } catch (InvocationTargetException e) {
                notifier.fireTestFailure(new Failure(testDescription, e));
            }
        } else {
            notifier.fireTestIgnored(testDescription);
        }
        notifier.fireTestFinished(testDescription);
        return true;
    }

    private boolean notIgnored(Method method) {
        return !method.isAnnotationPresent(Ignore.class);
    }
}
