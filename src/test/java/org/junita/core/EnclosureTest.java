package org.junita.core;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junita.model.AllTestMembers;
import org.junita.model.AllTestMethods;
import org.junita.testdata.TestClassWithMultipleTests;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author: Balaji Narain
 */
public class EnclosureTest {

    @Mock
    private TestClass testClass;
    @Mock
    private AllTestMethods allTestMethods;
    @Mock
    private AllTestMembers allTestMembers;
    @Mock
    private RunNotifier notifier;

    private Enclosure enclosure;

    @Before
    public void setup() {
        initMocks(this);
        when(testClass.clazz()).thenReturn(TestClassWithMultipleTests.class);
        enclosure = new Enclosure(testClass);
        when(testClass.allTestMethods()).thenReturn(allTestMethods);
        when(testClass.allTestMembers()).thenReturn(allTestMembers);
    }

    @Test
    public void shouldDescribeUnitUnderTest() {
        enclosure.getDescription();
        verify(testClass).describe(any(Description.class));
    }

    @Test
    public void shouldRunAllTestMethods() throws Exception {
        when(allTestMethods.isNotEmpty()).thenReturn(true);
        enclosure.run(notifier);
        verify(allTestMethods).run(testClass, notifier);
    }

    @Test
    public void shouldRunAllTestMembers() throws Exception {
        when(allTestMembers.isNotEmpty()).thenReturn(true);
        enclosure.run(notifier);
        verify(allTestMembers).run(testClass, notifier);
    }
}
