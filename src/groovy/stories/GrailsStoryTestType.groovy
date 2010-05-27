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
        def cl = Thread.currentThread().contextClassLoader

        //loads the shell with the current classLoader so we have all the domain classes, etc.
        def shell = new GroovyShell(cl)

        def counter = new GrailsStoryCounter()

        //sets proxies to the counter class so we can load the Story as a regular groovy script
        shell.setVariable("story", { name, yield -> counter.story(name, yield) })
        shell.setVariable("scenario", { name, yield -> counter.scenario(name, yield) })

        eachSourceFile { pattern, file ->
            def source = file.text
            def story = shell.parse(source)
            story.run()
            stories << story
        }
       
        return counter.tests;
    }

    GrailsTestTypeResult doRun(GrailsTestEventPublisher eventPublisher) {
        def runner = new MockRunner()
        
        stories.each {
            //sets proxies to the runner
            it.setProperty("story", { name, yield -> runner.story(name, yield) })
            it.setProperty("scenario", { name, yield -> runner.scenario(name, yield) })
            it.run()
        }
        
        return runner
    }
}
