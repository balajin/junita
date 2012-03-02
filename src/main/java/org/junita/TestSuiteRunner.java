package org.junita;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junita.support.AnnotatedMethodFinder;

import java.lang.reflect.Method;

/**
 * @author : Balaji Narain
 */
public class TestSuiteRunner extends Runner {

    private Class testClass;
    private TestMethodExecutor testMethodExecutor;

    public TestSuiteRunner(Class testClass) {
        this(testClass, new TestMethodExecutor());
    }

    public TestSuiteRunner(Class testClass, TestMethodExecutor testMethodExecutor) {
        this.testClass = testClass;
        this.testMethodExecutor = testMethodExecutor;
    }

    @Override
    public Description getDescription() {
        return Description.createSuiteDescription(testClass);
    }

    @Override
    public void run(RunNotifier notifier) {
        AnnotatedMethodFinder annotatedMethodFinder = new AnnotatedMethodFinder(testClass);
        for (Method method : annotatedMethodFinder.annotatedMethods(org.junit.Test.class)) {
            Description description = Description.createSuiteDescription(testClass);
            notifier.fireTestStarted(description);
            try {
                if (testMethodExecutor.test(testClass, method)) {
                    notifier.fireTestFinished(description);
                } else {
                    notifier.fireTestIgnored(description);
                }
            } catch (Exception e) {
                notifier.fireTestFailure(new Failure(description, e));
            }
        }
    }
}

