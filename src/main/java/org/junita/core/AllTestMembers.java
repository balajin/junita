package org.junita.core;


import org.junit.runner.notification.RunNotifier;

/**
 * @author : Balaji Narain
 */
public class AllTestMembers extends TargetAggregate<Class<?>> {

    public AllTestMembers() {
        super();
    }

    @Override
    public boolean run(TestClass testClass, RunNotifier notifier) throws Exception {
        return true;
    }
}
