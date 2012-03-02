package org.junita;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;
import org.junita.core.AllTestMembers;
import org.junita.core.AllTestMethods;
import org.junita.core.TestClass;
import org.mockito.Mock;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.Assert.fail;
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
        recursiveRunner = new RecursiveRunner(testClass);
        when(testClass.allTestMethods()).thenReturn(allTestMethods);
        when(testClass.allTestMembers()).thenReturn(allTestMembers);
    }

    @Test
    public void shouldRunAllTestMethods() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        when(allTestMethods.isNotEmpty()).thenReturn(true);
        recursiveRunner.run(notifier);
        verify(allTestMethods).run(testClass, notifier);
    }

    @Test
    public void shouldRunAllTestMembers() {
        when(allTestMembers.isNotEmpty()).thenReturn(true);
        recursiveRunner.run(notifier);
        verify(allTestMembers).run(testClass, notifier);
    }

    @Test
    public void shouldDescribeTestMethods() {
        fail("Epic");
    }

    @Test
    public void shouldDescribeTestMembers() {
        fail("Epic");
    }
}
