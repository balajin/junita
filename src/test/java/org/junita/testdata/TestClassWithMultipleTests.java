package org.junita.testdata;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junita.EnclosedTest;
import org.junita.RecursiveRunner;

import static junit.framework.Assert.fail;

/**
 * @author : Balaji Narain
 */
@Ignore
public class TestClassWithMultipleTests {

    public void emptyMethod() {

    }

    @Ignore
    public void ignoredMethod() {

    }

    public void failureMethod() {
        fail("Epic");
    }

    @Test
    public void testMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @EnclosedTest
    public class InnerTest {

        public InnerTest() {
        }

        @Test
        public void sample() {
        }
    }
}
