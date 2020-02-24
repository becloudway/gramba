package be.cloudway.grambda.runtime.helpers;

import be.cloudway.gramba.runtime.helpers.JacksonHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonHelperTest {

    private final JacksonHelper jacksonHelper = new JacksonHelper();

    private final String TEST_STRING = "{\"test\":1}";

    static class TestClass {
        private int test;

        public TestClass() {
        }

        public int getTest() {
            return test;
        }

        public void setTest(int test) {
            this.test = test;
        }
    }

    @Test
    public void shouldParseAObjectToAString () {
        TestClass testClass = new TestClass();
        testClass.setTest(1);

        assertThat(jacksonHelper.fromObj(testClass)).isEqualTo(TEST_STRING);
    }

    @Test
    public void shouldParseAStringToAObject () {
        assertThat(jacksonHelper.toObject(TEST_STRING, TestClass.class).getTest()).isEqualTo(1);
    }
}
