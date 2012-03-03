package org.junita;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.AllTestMembers;
import org.junita.core.AllTestMethods;
import org.junita.core.TestClass;
import org.junita.testdata.TestClassWithMultipleTests;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author: Balaji Narain
 */
public class RecursiveRunnerTest {

    @Mock
    private TestClass testClass;
    @Mock
    private AllTestMethods allTestMethods;
    @Mock
    private AllTestMembers allTestMembers;
    @Mock
    private RunNotifier notifier;

    private RecursiveRunner recursiveRunner;

    @Before
    public void setup() {
        initMocks(this);
        when(testClass.clazz()).thenReturn(TestClassWithMultipleTests.class);
        recursiveRunner = new RecursiveRunner(testClass);
        when(testClass.allTestMethods()).thenReturn(allTestMethods);
        when(testClass.allTestMembers()).thenReturn(allTestMembers);
    }

    @Test
    public void shouldDescribeUnitUnderTest() {
        recursiveRunner.getDescription();
        verify(testClass).describe(any(Description.class));
    }

    @Test
    public void shouldRunAllTestMethods() throws Exception {
        when(allTestMethods.isNotEmpty()).thenReturn(true);
        recursiveRunner.run(notifier);
        verify(allTestMethods).run(testClass, notifier);
    }

    @Test
    public void shouldRunAllTestMembers() throws Exception {
        when(allTestMembers.isNotEmpty()).thenReturn(true);
        recursiveRunner.run(notifier);
        verify(allTestMembers).run(testClass, notifier);
    }
}
