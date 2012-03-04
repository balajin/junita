package org.junita.model;


import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.Enclosure;
import org.junita.core.TestClass;

/**
 * @author : Balaji Narain
 */
public class AllTestMembers extends TargetAggregate<Class<?>> {

    private Enclosure enclosure;

    public AllTestMembers() {
        super();
    }

    public AllTestMembers(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    @Override
    public void describe(Description suiteDescription, Class clazz) {
        for (Class<?> target : testTargets) {
            Description description = enclosure(target).getDescription();
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
            enclosure(target).run(newInnerInstance, notifier);
        }
        return true;
    }

    private Enclosure enclosure(Class<?> target) {
        if (enclosure != null)
            return enclosure;
        return new Enclosure(target);
    }
}
