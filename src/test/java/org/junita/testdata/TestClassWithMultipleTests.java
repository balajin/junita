package org.junita.testdata;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junita.core.EnclosedTest;
import org.junita.core.Enclosure;

import static junit.framework.Assert.fail;

/**
 * @author : Balaji Narain
 */
@RunWith(Enclosure.class)
public class TestClassWithMultipleTests {

    public void emptyMethod() {

    }

    @Before
    public void setup() {

    }

    @Before
    public void anotherSetup() {

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

    @Test
    public void anotherTestMethod() {

    }

    @EnclosedTest
    public class InnerTest {

        @Before
        public void innerSetup() {

        }

        @Test
        public void sample() {
        }

        @Test
        public void anotherTest() {

        }

        @EnclosedTest
        public class NestedTest {

            @Test
            public void sample() {
            }
        }

        @After
        public void innerTearDown() {

        }
    }

    @After
    public void tearDown() {

    }

    @After
    public void anotherTearDown() {

    }
}
