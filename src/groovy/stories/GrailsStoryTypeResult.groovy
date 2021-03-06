package stories

import org.codehaus.groovy.grails.test.GrailsTestTypeResult


class GrailsStoryTypeResult implements GrailsTestTypeResult {
    def result

    def GrailsStoryTypeResult(result) {
        this.result = result
    }

    int getFailCount() {
        return result.getFailureCount();
    }
    int getPassCount() {
        return result.getRunCount() - getFailCount()
    }
}
