package org.junita;

import org.junit.Before;
import org.junit.Test;
import org.junita.support.Instantiator;
import org.junita.testdata.StubbedTestClass;
import org.mockito.Mock;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author : Balaji Narain
 */
public class TestMethodExecutorTest {

    @Mock
    private Instantiator instantiator;
    private TestMethodExecutor controlledMethodExecutor;
    private TestMethodExecutor testMethodExecutor;
    private StubbedTestClass stubbedTestClass;

    @Before
    public void setup() throws Exception {
        initMocks(this);
        stubbedTestClass = new StubbedTestClass();

        controlledMethodExecutor = new TestMethodExecutor(instantiator);
        testMethodExecutor = new TestMethodExecutor();
    }

    @Test
    public void shouldInstantiateTestClassForEveryMethod() throws Exception {
        testMethodExecutor.test(StubbedTestClass.class, stubbedTestClass.getClass().getMethod("testMethod"));
        assertTrue(StubbedTestClass.isInstantiated());
    }

    @Test
    public void shouldCallSetupClassMethod() {
        fail("Not implemented yet");
    }

    @Test
    public void shouldCallSetupMethod() throws Exception {
        when(instantiator.instantiate(any(Class.class))).thenReturn(stubbedTestClass);
        controlledMethodExecutor.test(StubbedTestClass.class, stubbedTestClass.getClass().getMethod("testMethod"));
        assertTrue(stubbedTestClass.isSetup());
    }

    @Test
    public void shouldInvokeTestMethod() throws Exception {
        when(instantiator.instantiate(any(Class.class))).thenReturn(stubbedTestClass);
        controlledMethodExecutor.test(StubbedTestClass.class, stubbedTestClass.getClass().getMethod("testMethod"));
        assertTrue(stubbedTestClass.isTested());
    }

    @Test
    public void shouldCallTearDownMethod() throws Exception {
        when(instantiator.instantiate(any(Class.class))).thenReturn(stubbedTestClass);
        controlledMethodExecutor.test(StubbedTestClass.class, stubbedTestClass.getClass().getMethod("testMethod"));
        assertTrue(stubbedTestClass.isToreDown());
    }

    @Test
    public void shouldReturnTrueWhenTestDoesNotThrowException() throws Exception {
        assertTrue(testMethodExecutor.test(StubbedTestClass.class, stubbedTestClass.getClass().getMethod("testMethod")));
    }

    @Test
    public void shouldReturnFalseWhenTestIsIgnored() throws Exception {
        assertFalse(testMethodExecutor.test(StubbedTestClass.class, stubbedTestClass.getClass().getMethod("ignored")));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenTestFails() throws Exception {
        assertFalse(testMethodExecutor.test(StubbedTestClass.class, stubbedTestClass.getClass().getMethod("failingTest")));
    }

    @Test
    public void shouldCallTearDownClassMethod() {
        fail("Not implemented yet");
    }
}
