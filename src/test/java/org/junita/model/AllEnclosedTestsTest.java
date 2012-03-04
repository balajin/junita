package org.junita.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.Enclosure;
import org.junita.testdata.TestClassWithMultipleTests;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author : Balaji Narain
 */
public class AllEnclosedTestsTest {

    @Mock
    private Enclosure enclosure;
    @Mock
    private RunNotifier notifier;
    @Mock
    private TestClass enclosingClass;

    private Class<?> enclosedTest;
    private AllEnclosedTests allEnclosedTests;

    public AllEnclosedTestsTest() {
        enclosedTest = TestClassWithMultipleTests.InnerTest.class;
    }

    @Before
    public void setup() {
        initMocks(this);
        allEnclosedTests = new AllEnclosedTests(enclosure);
        when(enclosingClass.clazz()).thenReturn(TestClassWithMultipleTests.class);
    }

    @Test
    public void shouldDescribeTheEnclosedTest() {
        Description suiteDescription = mock(Description.class);
        allEnclosedTests.add(enclosedTest);

        allEnclosedTests.describe(suiteDescription, TestClassWithMultipleTests.class);
        verify(suiteDescription, times(1)).addChild(any(Description.class));
    }

    @Test
    public void shouldRecursivelyRunEnclosedTest() throws Exception {
        allEnclosedTests.add(enclosedTest);
        when(enclosingClass.newInstance()).thenReturn(new TestClassWithMultipleTests());

        allEnclosedTests.run(enclosingClass, notifier);
        verify(enclosure, times(1)).run(same(notifier));
    }

    @Test
    public void shouldDestroyTheInstanceAfterTest() throws Exception {
        allEnclosedTests.add(enclosedTest);
        when(enclosingClass.newInstance()).thenReturn(new TestClassWithMultipleTests());

        allEnclosedTests.run(enclosingClass, notifier);
        verify(enclosingClass).destroy(anyObject());
    }
}
