package org.junita.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junita.testdata.TestClassWithMultipleTests;
import org.junita.testdata.TestClassWithNoTests;

import static junit.framework.Assert.*;

/**
 * @author : Balaji Narain
 */
public class TestClassTest {

    private TestClass testClass;

    @Test
    public void shouldReturnEmptyTestMethodsForAClassWithNoTests() {
        testClass = new TestClass(TestClassWithNoTests.class);
        assertTrue(testClass.allTestMethods().isEmpty());
    }

    @Test
    public void shouldCreateInstanceOfClass() throws Exception {
        assertNotNull(new TestClass(TestClassWithMultipleTests.class).newInstance());
    }

    @Test
    public void shouldReturnEmptyTestMembersForAClassWithNoMembers() {
        testClass = new TestClass(TestClassWithNoTests.class);
        assertTrue(testClass.allTestMembers().isEmpty());
    }

    @Test
    public void shouldReturnAllTestMethodsOfAClass() {
        testClass = new TestClass(TestClassWithMultipleTests.class);
        assertEquals(2, testClass.allTestMethods().size());
    }

    @Test
    public void shouldReturnAllTestMembersOfAClass() {
        testClass = new TestClass(TestClassWithMultipleTests.class);
        assertEquals(1, testClass.allTestMembers().size());
    }

    @Test
    public void shouldReturnAllSetupMethodsOfTheClass() {
        testClass = new TestClass(TestClassWithMultipleTests.class);
        assertEquals(2, testClass.allDataModifiers(Before.class).size());
    }

    @Test
    public void shouldReturnAllTearDownMethodsOfTheClass() {
        testClass = new TestClass(TestClassWithMultipleTests.class);
        assertEquals(2, testClass.allDataModifiers(After.class).size());
    }
}
