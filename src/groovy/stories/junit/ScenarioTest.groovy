package stories.junit

import junit.framework.TestCase
import junit.framework.TestResult

class ScenarioTest extends TestCase {
    def name
    def code

    public ScenarioTest(testName) {
        super(testName)
    }

    public void runTest() {
        doRun()
    }

    void doRun() {
        code()
    }
}

