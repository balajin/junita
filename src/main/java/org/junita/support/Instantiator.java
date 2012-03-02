package org.junita.support;


import java.lang.reflect.Constructor;

import static junit.framework.Assert.fail;

public class Instantiator {

    public Object instantiate(Class testClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
        assertDefaultConstructorFound(testClass);
        return testClass.newInstance();
    }

    private void assertDefaultConstructorFound(Class testClass) throws NoSuchMethodException {
        if (testClass.getConstructor() == null)
            fail("Test class should have a default constructor");
    }
}
