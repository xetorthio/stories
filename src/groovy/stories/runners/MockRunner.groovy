package stories.runners

import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.junit.runner.JUnitCore
import org.junit.internal.runners.JUnit38ClassRunner
import junit.framework.TestCase

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
            def msg = "\t${name} - FAILED..... " + ex.message
            println msg          
            failed++
        } catch (Exception e){
            def msg = "\t${name} - EXCEPTION..... " + e.message
            println msg
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
