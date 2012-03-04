package org.junita.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junita.model.AllDataModifiers;
import org.junita.model.AllTestMembers;
import org.junita.model.AllTestMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : Balaji Narain
 */
public class TestClass {

    private Class<?> testClass;

    public TestClass(Class<?> testClass) {
        this.testClass = testClass;
    }

    public Object newInstance() throws Exception {
        Object instance = testClass.newInstance();
        allDataModifiers(Before.class).applyOn(instance);
        return instance;
    }

    public Object newInstance(Object enclosingObject) throws Exception {
        Object instance = testClass.getConstructor(enclosingObject.getClass()).newInstance(enclosingObject);
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
        AllTestMethods allTestMethods = new AllTestMethods();
        for (Method method : testClass.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                allTestMethods.add(method);
            }
        }
        return allTestMethods;
    }

    AllTestMembers allTestMembers() {
        AllTestMembers allTestMembers = new AllTestMembers();
        for (Class innerClass : testClass.getClasses()) {
            if (innerClass.isAnnotationPresent(EnclosedTest.class)) {
                allTestMembers.add(innerClass);
            }
        }
        return allTestMembers;
    }

    AllDataModifiers allDataModifiers(Class annotation) {
        AllDataModifiers allDataModifiers = new AllDataModifiers();
        for (Method method : testClass.getMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                allDataModifiers.add(method);
            }
        }
        return allDataModifiers;
    }
}
