package org.junita.core;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junita.model.AllDataModifiers;
import org.junita.model.AllEnclosedTests;
import org.junita.model.AllTestMethods;
import org.junita.model.TestClass;
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
    private AllDataModifiers allDataModifiers;
    @Mock
    private AllEnclosedTests allEnclosedTests;

    @Mock
    private RunNotifier notifier;
    private Enclosure enclosure;

    @Before
    public void setup() throws Exception {
        initMocks(this);
        when(testClass.clazz()).thenReturn(TestClassWithMultipleTests.class);
        when(testClass.newInstance()).thenReturn(new TestClassWithMultipleTests());

        enclosure = new Enclosure(testClass);
        when(testClass.allTestMethods()).thenReturn(allTestMethods);
        when(testClass.allEnclosedTests()).thenReturn(allEnclosedTests);
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
    public void shouldRunAllEnclosedTests() throws Exception {
        when(allEnclosedTests.isNotEmpty()).thenReturn(true);
        enclosure.run(notifier);
        verify(allEnclosedTests).run(testClass, notifier);
    }
}
