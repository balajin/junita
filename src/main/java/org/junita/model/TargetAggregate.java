package org.junita.model;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.TestClass;

/**
 * A collection of testable entities viz.
 *
 *      1. Test methods
 *      2. Enclosed tests
 *
 * @author : Balaji Narain
 */
public abstract class TargetAggregate<T> extends AggregateOfInvokables<T> {

    public boolean run(TestClass o, RunNotifier notifier) throws Exception {
        boolean result = true;
        for (T invokable : invokables) {
            Description description = Description.createSuiteDescription(o.clazz());
            Object instance = o.newInstance();
            result &= run(invokable, instance, description, notifier);
            o.destroy(instance);
        }
        return result;
    }

    public abstract boolean run(T invokable, Object instance, Description description, RunNotifier notifier) throws Exception;

    public abstract void describe(Description suiteDescription, Class clazz);
}
