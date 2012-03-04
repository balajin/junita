package org.junita.core;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junita.model.TargetAggregate;

/**
 * The Intention was to decorate TestClass with this class. But thanks to jUnit making Runner a class,
 * this is not possible
 *
 * @author : Balaji Narain
 *         TODO: Currently does not support running individual tests.
 */
public class Enclosure extends Runner {

    private TestClass testClass;

    public Enclosure(Class<?> testClass) {
        this(new TestClass(testClass));
    }

    public Enclosure(TestClass testClass) {
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

    private void run(TargetAggregate allTargets, RunNotifier notifier) throws Exception {
        if (allTargets.isNotEmpty()) {
            allTargets.run(testClass, notifier);
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
}

