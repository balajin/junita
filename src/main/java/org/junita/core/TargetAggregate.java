package org.junita.core;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Balaji Narain
 */
public abstract class TargetAggregate<T> {

    protected List<T> testTargets;

    protected TargetAggregate() {
        testTargets = new ArrayList<T>();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return testTargets.isEmpty();
    }

    public int size() {
        return testTargets.size();
    }

    public TargetAggregate<T> add(T target) {
        testTargets.add(target);
        return this;
    }

    public abstract boolean run(TestClass o, RunNotifier notifier) throws Exception;

    public abstract void describe(Description suiteDescription, Class clazz);
}
