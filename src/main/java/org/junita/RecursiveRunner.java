package org.junita;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.TargetAggregate;
import org.junita.core.TestClass;

import java.lang.reflect.InvocationTargetException;

/**
 * @author : Balaji Narain
 */
public class RecursiveRunner extends Runner {

    private TestClass testClass;

    public RecursiveRunner(Class<?> testClass) {
        this(new TestClass(testClass));
    }

    public RecursiveRunner(TestClass testClass) {
        this.testClass = testClass;
    }

    @Override
    public Description getDescription() {
        return null;
    }

    @Override
    public void run(RunNotifier notifier) {
        run(testClass.allTestMethods(), notifier);
        run(testClass.allTestMembers(), notifier);
    }

    private void run(TargetAggregate allTargets, RunNotifier notifier) {
        if (allTargets.isNotEmpty()) {
            try {
                allTargets.run(testClass, notifier);
            } catch (Exception e) {

            }
        }
    }
}

