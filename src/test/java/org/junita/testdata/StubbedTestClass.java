package org.junita.testdata;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.fail;

/**
 * @author : Balaji Narain
 */
public class StubbedTestClass {

    private static boolean instantiated = false;
    private boolean setup;
    private boolean tested;
    private boolean toreDown;

    public StubbedTestClass() {
        instantiated = true;
        setup = false;
        tested = false;
    }

    @Before
    public void setup() {
        setup = true;
    }

    @Test
    public void testMethod() {
        tested = true;
    }

    @Test
    @Ignore
    public void ignored() {

    }

    @Test
    public void failingMethod() {
        fail();
    }

    @After
    public void tearDown() {
        toreDown = true;
    }

    public static boolean isInstantiated() {
        return instantiated;
    }

    public boolean isSetup() {
        return setup;
    }

    public boolean isTested() {
        return tested;
    }

    public boolean isToreDown() {
        return toreDown;
    }
}
