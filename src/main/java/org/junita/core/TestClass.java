package org.junita.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junita.model.AggregateOfInvokables;
import org.junita.model.AllDataModifiers;
import org.junita.model.AllTestMembers;
import org.junita.model.AllTestMethods;

import java.lang.reflect.Method;

/**
 * @author : Balaji Narain
 */
public class TestClass {

    private Class<?> testClass;
    private Object enclosingInstance;

    public TestClass(Class<?> testClass) {
        this.testClass = testClass;
    }

    public TestClass(Class<?> testClass, Object enclosingInstance) {
        this(testClass);
        this.enclosingInstance = enclosingInstance;
    }

    public Object newInstance() throws Exception {
        Object instance = null;
        if (enclosingInstance == null) {
            instance = testClass.newInstance();
        } else {
            instance = testClass.getConstructor(enclosingInstance.getClass()).newInstance(enclosingInstance);
        }
        allDataModifiers(Before.class).applyOn(instance);
        return instance;
    }

    public void destroy(Object instance) throws Exception {
        allDataModifiers(After.class).applyOn(instance);
    }

    public Class clazz() {
        return testClass;
    }

    public void describe(Description suiteDescription) {
        allTestMethods().describe(suiteDescription, clazz());
        allTestMembers().describe(suiteDescription, clazz());
    }

    AllTestMethods allTestMethods() {
        return new AllTestMethods(this);
    }

    AllTestMembers allTestMembers() {
        return new AllTestMembers(this);
    }

    AllDataModifiers allDataModifiers(Class annotation) {
        return new AllDataModifiers(this, annotation);
    }
}
