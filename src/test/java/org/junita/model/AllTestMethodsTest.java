package org.junita.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junita.testdata.TestClassWithMultipleTests;
import org.junita.util.TargetProxy;
import org.mockito.Mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author : Balaji Narain
 */
public class AllTestMethodsTest {

    @Mock
    private TargetProxy targetProxy;
    @Mock
    private RunNotifier notifier;
    private AllTestMethods testMethods;
    private TestClass testClass;
    private Method emptyMethod;
    private Method failureMethod;
    private Method ignoredMethod;

    public AllTestMethodsTest() throws NoSuchMethodException {
        testClass = new TestClass(TestClassWithMultipleTests.class);
        emptyMethod = TestClassWithMultipleTests.class.getMethod("emptyMethod");
        failureMethod = TestClassWithMultipleTests.class.getMethod("failureMethod");
        ignoredMethod = TestClassWithMultipleTests.class.getMethod("ignoredMethod");
    }

    @Before
    public void setup() {
        initMocks(this);
        testMethods = new AllTestMethods(targetProxy);
    }

    @Test
    public void shouldSignalStartOfTestForEachMethod() throws Exception {
        testMethods.add(emptyMethod);
        testMethods.run(testClass, notifier);
        verify(notifier).fireTestStarted(any(Description.class));
    }

    @Test
    public void shouldSignalTestsFinished() throws Exception {
        testMethods.add(emptyMethod);
        testMethods.run(testClass, notifier);
        verify(notifier).fireTestFinished(any(Description.class));
    }

    @Test
    public void shouldSignalFailureWhenMethodThrowsException() throws Exception {
        testMethods.add(failureMethod);
        InvocationTargetException expectedException = new InvocationTargetException(new Exception("msg"));

        when(targetProxy.invokeMethod(any(Method.class), anyObject())).thenThrow(expectedException);
        testMethods.run(testClass, notifier);
        verify(notifier).fireTestFailure(any(Failure.class));
    }

    @Test
    public void shouldSignalIgnoredWhenMethodIsIgnored() throws Exception {
        testMethods.add(ignoredMethod);
        testMethods.run(testClass, notifier);
        verify(notifier).fireTestIgnored(any(Description.class));
    }

    @Test
    public void shouldInvokeAllTestMethods() throws Exception {
        testMethods.add(emptyMethod).add(emptyMethod);
        testMethods.run(testClass, notifier);
        verify(targetProxy, times(2)).invokeMethod(any(Method.class), any(Object.class));
    }
}
