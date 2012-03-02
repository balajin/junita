package org.junita;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junita.testdata.TestClassWithManyTestMethods;
import org.junita.testdata.TestClassWithTestMethod;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author: Balaji Narain
 */
public class TestSuiteRunnerTest {

    @Mock
    private RunNotifier notifier;
    @Mock
    private TestMethodExecutor testMethodExecutor;

    public TestSuiteRunnerTest() {
        initMocks(this);
    }

    @Before
    public void setup() {
        reset(notifier);
    }

    @Test
    public void shouldReportStartForTests() {
        TestSuiteRunner testSuiteRunner = new TestSuiteRunner(TestClassWithTestMethod.class, testMethodExecutor);
        testSuiteRunner.run(notifier);
        verify(notifier).fireTestStarted(any(Description.class));
    }

    @Test
    public void shouldReportStartForEveryTest() {
        TestSuiteRunner testSuiteRunner = new TestSuiteRunner(TestClassWithManyTestMethods.class, testMethodExecutor);
        testSuiteRunner.run(notifier);
        verify(notifier, times(2)).fireTestStarted(any(Description.class));
    }

    @Test
    public void shouldReportSuccessWhenTestIsTrue() throws Exception {
        TestSuiteRunner testSuiteRunner = new TestSuiteRunner(TestClassWithManyTestMethods.class, testMethodExecutor);

        when(testMethodExecutor.test(eq(TestClassWithManyTestMethods.class), any(Method.class))).thenReturn(true);
        testSuiteRunner.run(notifier);
        verify(notifier, atLeastOnce()).fireTestFinished(any(Description.class));
    }

    @Test
    public void shouldReportIgnoredWhenTestIsFalse() throws Exception {
        TestSuiteRunner testSuiteRunner = new TestSuiteRunner(TestClassWithManyTestMethods.class, testMethodExecutor);

        when(testMethodExecutor.test(eq(TestClassWithManyTestMethods.class), any(Method.class))).thenReturn(false);
        testSuiteRunner.run(notifier);
        verify(notifier, atLeastOnce()).fireTestIgnored(any(Description.class));
    }

    @Test
    public void shouldReportFailureWhenTestThrowsException() throws Exception {
        TestSuiteRunner testSuiteRunner = new TestSuiteRunner(TestClassWithManyTestMethods.class, testMethodExecutor);

        when(testMethodExecutor.test(eq(TestClassWithManyTestMethods.class), any(Method.class))).thenThrow(new Exception());
        testSuiteRunner.run(notifier);
        verify(notifier, atLeastOnce()).fireTestFailure(any(Failure.class));
    }
}
