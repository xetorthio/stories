package stories

import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import stories.GrailsStoryTestTypeResult
import groovy.lang.GroovyShell

class GrailsStoryTestType extends GrailsTestTypeSupport {
    GrailsStoryTestType(String name, String relativeSourcePath) {
        super(name, relativeSourcePath)
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
            story.delegate = counter
            story()
        }
        return counter.tests;
    }

    GrailsTestTypeResult doRun(GrailsTestEventPublisher eventPublisher) {
        //TODO: run the tests... show results
        return new GrailsStoryTestTypeResult()    
    }
}
