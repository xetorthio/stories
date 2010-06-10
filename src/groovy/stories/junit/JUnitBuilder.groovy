package stories.junit

import junit.framework.TestSuite

class JUnitBuilder {
        
    def _beforeStory
    def _afterStory
    def _before
    def _after
    def suite = new TestSuite()
    def currentStory = null
    def tests = 0

    //def clientHelper = new StoryRestClientHelper()

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
        currentStory = new TestSuite(name)
        //currentStory.name = name
        suite.addTest(currentStory) 
        yield()

        /*
        if (_afterStory){
            _afterStory()
        }
        */
    }

    def scenario(name, yield) {
        /*
        if(_before) {
            _before()
        }
        */
        tests++
        /*
        if (clientHelper){
            yield.delegate = clientHelper
        }
        */
        def sc = new ScenarioTest(name)
        sc.code = yield
        sc.name = name
        currentStory.addTest(sc)

        /*
        if(_after) {
            _after()
        }
        */
    }
}
