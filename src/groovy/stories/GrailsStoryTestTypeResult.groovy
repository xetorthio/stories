package stories

import org.codehaus.groovy.grails.test.GrailsTestTypeResult;

class GrailsStoryTestTypeResult implements GrailsTestTypeResult {
    int getFailCount() {
        return 1
    }
    int getPassCount() {
        return 0
    }
}
