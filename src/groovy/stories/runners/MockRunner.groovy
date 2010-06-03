package stories.runners

import org.codehaus.groovy.grails.test.GrailsTestTypeResult

class MockRunner implements GrailsTestTypeResult {
    def _beforeStory
    def _afterStory
    def _before
    def _after
    def passed = 0
    def failed = 0

    def clientHelper = new StoryRestClientHelper()

    def before(yield) {
        _before = yield
    }

    def after(yield) {
        _after = yield
    }

    def beforeStory(yield){
        //Tiene que correr si o sí y tiene que estar arriba de todo
        yield()
    }

    def afterStory(yield){
        _afterStory = yield
    }

    def story(name, yield) {
        println "${name}"
        yield()

        if (_afterStory){
            _afterStory()
        }
    }

    def scenario(name, yield) {
        if(_before) {
            _before()
        }

        if (clientHelper){
            yield.delegate = clientHelper
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
            e.printStackTrace()
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
