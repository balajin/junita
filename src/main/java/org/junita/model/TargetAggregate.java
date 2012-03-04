package org.junita.model;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.TestClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Balaji Narain
 */
public abstract class TargetAggregate<T> extends AggregateOfInvokables<T> {


    public abstract boolean run(TestClass o, RunNotifier notifier) throws Exception;

    public abstract boolean run(TestClass testClass, Object instance, RunNotifier notifier) throws Exception;

    public abstract void describe(Description suiteDescription, Class clazz);
}
