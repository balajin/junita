package org.junita.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.Enclosure;
import org.junita.core.TestClass;
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
public class AllTestMembersTest {

    @Mock
    private Enclosure enclosure;
    @Mock
    private RunNotifier notifier;
    @Mock
    private TestClass enclosingClass;

    private Class<?> enclosedTest;
    private AllTestMembers allTestMembers;

    public AllTestMembersTest() {
        enclosedTest = TestClassWithMultipleTests.InnerTest.class;
    }

    @Before
    public void setup() {
        initMocks(this);
        allTestMembers = new AllTestMembers(enclosure);
        when(enclosingClass.clazz()).thenReturn(TestClassWithMultipleTests.class);
    }

    @Test
    public void shouldDescribeTheEnclosedTest() {
        Description suiteDescription = mock(Description.class);
        allTestMembers.add(enclosedTest);

        allTestMembers.describe(suiteDescription, TestClassWithMultipleTests.class);
        verify(suiteDescription, times(1)).addChild(any(Description.class));
    }

    @Test
    public void shouldRecursivelyRunEnclosedTest() throws Exception {
        allTestMembers.add(enclosedTest);
        when(enclosingClass.newInstance()).thenReturn(new TestClassWithMultipleTests());

        allTestMembers.run(enclosingClass, notifier);
        verify(enclosure, times(1)).run(same(notifier));
    }

    @Test
    public void shouldDestroyTheInstanceAfterTest() throws Exception {
        allTestMembers.add(enclosedTest);
        when(enclosingClass.newInstance()).thenReturn(new TestClassWithMultipleTests());

        allTestMembers.run(enclosingClass, notifier);
        verify(enclosingClass).destroy(anyObject());
    }
}
