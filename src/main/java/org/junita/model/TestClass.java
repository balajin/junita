package org.junita.model;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.Description;

/**
 * Models a class under test.
 * <p/>
 * Is an aggregate of
 * <p/>
 * 1. All test methods
 * 2. All enclosed tests
 * 3. All data modifiers - Setup and Teardown methods
 *
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
        allEnclosedTests().describe(suiteDescription, clazz());
    }

    public AllTestMethods allTestMethods() {
        return new AllTestMethods(this);
    }

    public AllEnclosedTests allEnclosedTests() {
        return new AllEnclosedTests(this);
    }

    public AllDataModifiers allDataModifiers(Class annotation) {
        return new AllDataModifiers(this, annotation);
    }
}
