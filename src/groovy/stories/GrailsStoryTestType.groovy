package stories

import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import groovy.lang.GroovyShell
import stories.runners.MockRunner

class GrailsStoryTestType extends GrailsTestTypeSupport {
    def stories
    def tests = 0

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

        eachSourceFile { pattern, file ->
            def source = file.text
            Script story = shell.parse(source)

            stories << story
            story.metaClass = createEMC(story.class, counter)
            story.run()
        }

        return counter.tests;
    }

    def createEMC(Class clazz, delegator){
      def emc = new ExpandoMetaClass(clazz, false)

      emc.story = { name, cl ->
        cl.delegate = delegator
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        delegator.story(name, cl)
      }

      emc.initialize()
      emc
    }

    GrailsTestTypeResult doRun(GrailsTestEventPublisher eventPublisher) {
        def runner = new MockRunner()
        stories.each {
          it.metaClass = createEMC(it.class, runner)
          it.run()
        }
        return runner
    }
}
