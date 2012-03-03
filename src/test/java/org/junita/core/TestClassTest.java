package org.junita.core;

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
    public void shouldCreateInstanceOfClass() throws InstantiationException, IllegalAccessException {
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
        assertEquals(1, testClass.allTestMethods().size());
    }

    @Test
    public void shouldReturnAllTestMembersOfAClass() {
        testClass = new TestClass(TestClassWithMultipleTests.class);
        assertEquals(1, testClass.allTestMembers().size());
    }
}
