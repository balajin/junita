package org.junita.core;

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
        return true;
    }

    public int size() {
        return testTargets.size();
    }

    public TargetAggregate<T> add(T target) {
        testTargets.add(target);
        return this;
    }

    public abstract void run(TestClass o, RunNotifier notifier) throws IllegalAccessException, InstantiationException, InvocationTargetException;
}
