package stories

import org.codehaus.groovy.grails.test.GrailsTestTypeResult


class GrailsStoryTypeResult implements GrailsTestTypeResult {
    def result

    def GrailsStoryTypeResult(result) {
        this.result = result
    }

    int getFailCount() {
        return result.failureCount() + result.errorCount()
    }
    int getPassCount() {
        return result.runCount() - getFailCount()
    }
}
