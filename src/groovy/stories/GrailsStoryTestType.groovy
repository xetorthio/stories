package stories

import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import groovy.lang.GroovyShell
import stories.runners.MockRunner

class GrailsStoryTestType extends GrailsTestTypeSupport {
    def stories

    GrailsStoryTestType(String name, String relativeSourcePath) {
        super(name, relativeSourcePath)
        stories = []
    }

    protected List<String> getTestExtensions() {
        ["groovy"]
    }

    protected List<String> getTestSuffixes() { 
        ["Story"]
    }

    protected int doPrepare() {
        def shell = new GroovyShell()
        def counter = new GrailsStoryCounter()
        eachSourceFile { pattern, file ->
            def source = file.text
            def story = (Closure) shell.evaluate("{ it -> ${source} }")
            stories << story
            story.delegate = counter
            story()
        }
        return counter.tests;
    }

    GrailsTestTypeResult doRun(GrailsTestEventPublisher eventPublisher) {
        def runner = new MockRunner()
        stories.each {
            it.delegate = runner
            it()
        }
        return runner
    }
}
