package org.junita.core;

import org.junit.Test;
import org.junit.runner.Description;
import org.junita.EnclosedTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : Balaji Narain
 */
public class TestClass {

    private Class<?> testClass;

    public TestClass(Class<?> testClass) {
        this.testClass = testClass;
    }

    public Object newInstance() throws IllegalAccessException, InstantiationException {
        return testClass.newInstance();
    }

    public Class clazz() {
        return testClass;
    }

    public void describe(Description suiteDescription) {
        allTestMethods().describe(suiteDescription, clazz());
        allTestMembers().describe(suiteDescription, clazz());
    }

    public AllTestMethods allTestMethods() {
        AllTestMethods allTestMethods = new AllTestMethods();
        for (Method method : testClass.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                allTestMethods.add(method);
            }
        }
        return allTestMethods;
    }

    public AllTestMembers allTestMembers() {
        AllTestMembers allTestMembers = new AllTestMembers();
        for (Class innerClass : testClass.getClasses()) {
            if (innerClass.isAnnotationPresent(EnclosedTest.class)) {
                allTestMembers.add(innerClass);
            }
        }
        return allTestMembers;
    }
}
