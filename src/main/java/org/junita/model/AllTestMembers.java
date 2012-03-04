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
        for (Class<?> target : invokables) {
            TestClass innerTest = new TestClass(target);
            Description description = enclosure(innerTest).getDescription();
            suiteDescription.addChild(description);
        }
    }

    @Override
    public boolean run(TestClass enclosingClass, RunNotifier notifier) throws Exception {
        Object instance = enclosingClass.newInstance();
        boolean result = run(enclosingClass, instance, notifier);
        enclosingClass.destroy(instance);
        return result;
    }

    @Override
    public boolean run(TestClass testClass, Object instance, RunNotifier notifier) throws Exception {
        for (Class<?> target : invokables) {
            TestClass innerTest = new TestClass(target);
            Object newInnerInstance = innerTest.newInstance(instance);
            enclosure(innerTest).run(newInnerInstance, notifier);
            innerTest.destroy(newInnerInstance);
        }
        return true;
    }

    private Enclosure enclosure(TestClass target) {
        if (enclosure != null)
            return enclosure;
        return new Enclosure(target);
    }
}
