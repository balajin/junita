package org.junita.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;
import org.junita.testdata.TestClassWithMultipleTests;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Before
    public void setup() {
        initMocks(this);
        testClass = new TestClass(TestClassWithMultipleTests.class);
        testMethods = new AllTestMethods(targetProxy);
    }

    @Test
    public void shouldInvokeAllTestMethods() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        testMethods.add(null).add(null);
        testMethods.run(testClass, notifier);
        verify(targetProxy, times(2)).invokeMethod(any(Method.class), any(Object.class));
    }
}
