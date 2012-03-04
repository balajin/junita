package org.junita.model;

import org.junit.Before;
import org.junit.Test;
import org.junita.testdata.TestClassWithMultipleTests;
import org.mockito.Mock;

import java.lang.reflect.Method;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author : Balaji Narain
 */
public class AllDataModifiersTest {

    @Mock
    private TargetProxy targetProxy;
    private AllDataModifiers allDataModifiers;

    @Before
    public void setUp() {
        initMocks(this);
        allDataModifiers = new AllDataModifiers(targetProxy);
    }

    @Test
    public void shouldApplyDataModifiersOnInstance() throws Exception {
        Object instance = new TestClassWithMultipleTests();
        allDataModifiers.add(TestClassWithMultipleTests.class.getMethod("setup"));

        allDataModifiers.applyOn(instance);
        verify(targetProxy).invokeMethod(any(Method.class), same(instance));
    }
}
