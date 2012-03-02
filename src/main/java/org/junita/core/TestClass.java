package org.junita.core;

import org.junit.Test;
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

    public Object newInstance() throws IllegalAccessException, InstantiationException {
        return testClass.newInstance();
    }
}
