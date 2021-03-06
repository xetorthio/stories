package stories.junit

import junit.framework.TestCase
import junit.framework.TestResult
import stories.helpers.StoryRestClientHelper

class ScenarioTest extends TestCase {
    def name
    def code
    def before
    def after
    static sessionFactory
    def clientHelper = new StoryRestClientHelper()

    public ScenarioTest(testName) {
        super(testName)
    }

    public void runTest() {
        doRun()
    }

    void doRun() {
        if (clientHelper){
            code.delegate = clientHelper
        }
        code()
    }


    protected void setUp() {
//      transaction = sessionFactory.currentSession.beginTransaction()

//      if (!transaction.isActive())
//          transaction.begin()
        if(before) {
            before()
        }
    }

    protected void tearDown() {
        if(after) {
            after()
        }
//      println transaction.properties
//      transaction.rollback() Transactions not working :S
        sessionFactory.currentSession.clear()
    }

}

