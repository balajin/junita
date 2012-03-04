package org.junita.model;

import org.junita.core.TestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : Balaji Narain
 */
public class AllDataModifiers extends AggregateOfInvokables<Method> {

    private TargetProxy targetProxy;

    public AllDataModifiers() {
        this(new TargetProxy());
    }

    public AllDataModifiers(TestClass testClass, Class annotation) {
        this();
        for (Method method : testClass.clazz().getMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                this.add(method);
            }
        }
    }

    public AllDataModifiers(TargetProxy targetProxy) {
        this.targetProxy = targetProxy;
    }

    public void applyOn(Object instance) throws InvocationTargetException, IllegalAccessException {
        for (Method method : invokables) {
            targetProxy.invokeMethod(method, instance);
        }
    }
}
