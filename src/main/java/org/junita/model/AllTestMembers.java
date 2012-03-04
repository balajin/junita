package org.junita.model;


import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.Enclosure;
import org.junita.core.TestClass;

/**
 * @author : Balaji Narain
 */
public class AllTestMembers extends TargetAggregate<Class<?>> {

    public AllTestMembers() {
        super();
    }

    @Override
    public void describe(Description suiteDescription, Class clazz) {
        for (Class<?> target : testTargets) {
            Description description = new Enclosure(target).getDescription();
            suiteDescription.addChild(description);
        }
    }

    @Override
    public boolean run(TestClass testClass, RunNotifier notifier) throws Exception {
        Object instance = testClass.newInstance();
        return run(testClass, instance, notifier);
    }

    @Override
    public boolean run(TestClass testClass, Object instance, RunNotifier notifier) throws Exception {
        for (Class<?> target : testTargets) {
            Object newInnerInstance = target.getConstructor(instance.getClass()).newInstance(instance);
            new Enclosure(target).run(newInnerInstance, notifier);
        }
        return true;
    }
}
