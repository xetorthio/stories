package stories.runners

import org.codehaus.groovy.grails.test.GrailsTestTypeResult;

class MockRunner implements GrailsTestTypeResult {
    def _before
    def _after
    def passed = 0
    def failed = 0

    def before(yield) {
        _before = yield
    }

    def after(yield) {
        _after = yield
    }

    def story(name, yield) {
        println "${name}"
        yield()
    }


    def scenario(name, yield) {
        if(_before) {
            _before()
        }
        try {
            yield()
            println "\t${name} - PASSED"
            passed++
        } catch(AssertionError ex) {
            println "\t${name} - FAILED..... " + ex.message
            failed++
        }
        if(_after) {
            _after()
        }
    }

    int getFailCount() {
        return failed
    }
    int getPassCount() {
        return passed
    }
}
