package org.junita.testdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junita.EnclosedTest;
import org.junita.RecursiveRunner;

/**
 * @author : Balaji Narain
 */
@RunWith(RecursiveRunner.class)
public class TestClassWithMultipleTests {

    @Test
    public void testMethod() {

    }

    @Test
    public void anotherTestMethod() {

    }

    @EnclosedTest
    public class InnerTest {
    }
}
