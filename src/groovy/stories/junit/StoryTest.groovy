package stories.junit

import junit.framework.Test
import junit.framework.TestResult

class StoryTest implements Test {
    def tests = []
    def name

    int countTestCases() {
        return tests.size()
    }

    void run(TestResult result) {
        println name
        tests.each {
            it.run(result)
        }
    }
}
