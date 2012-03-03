package org.junita;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.AllTestMethods;
import org.junita.core.TargetAggregate;
import org.junita.core.TestClass;

/**
 * @author : Balaji Narain
 *         <p/>
 *         TODO: Currently does not support running individual tests.
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
        Description suiteDescription = Description.createSuiteDescription(testClass.clazz());
        testClass.describe(suiteDescription);
        return suiteDescription;
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            run(testClass.allTestMethods(), notifier);
            run(testClass.allTestMembers(), notifier);
        } catch (Exception e) {
        }
    }

    public void run(Object instance, RunNotifier notifier) {
        try {
            run(testClass.allTestMethods(), instance, notifier);
            run(testClass.allTestMembers(), instance, notifier);
        } catch (Exception e) {
        }
    }

    private void run(TargetAggregate allTargets, Object instance, RunNotifier notifier) throws Exception {
        if (allTargets.isNotEmpty()) {
            allTargets.run(testClass, instance, notifier);
        }
    }

    private void run(TargetAggregate allTargets, RunNotifier notifier) throws Exception {
        if (allTargets.isNotEmpty()) {
            allTargets.run(testClass, notifier);
        }
    }
}

