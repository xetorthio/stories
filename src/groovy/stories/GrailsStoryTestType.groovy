package stories

import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import groovy.lang.GroovyShell
import stories.junit.JUnitBuilder
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.test.junit3.JUnit3GrailsTestTypeRunner
import org.codehaus.groovy.grails.test.report.junit.JUnitReportsFactory

class GrailsStoryTestType extends GrailsTestTypeSupport {
    def builder

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
        
        def cl = Thread.currentThread().contextClassLoader
        //loads the shell with the current classLoader so we have all the domain classes, etc.
        def shell = new GroovyShell(cl)
        builder = new JUnitBuilder()

        eachSourceFile { pattern, file ->
            def source = file.text
            Script story = shell.parse(source)

            story.metaClass = createEMC(story.class, builder)
            story.run()
        }

        return builder.tests;
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

    protected JUnit3GrailsTestTypeRunner createRunner(GrailsTestEventPublisher eventPublisher) {
        return new JUnit3GrailsTestTypeRunner(JUnitReportsFactory.createFromBuildBinding(getBuildBinding()), eventPublisher, createSystemOutAndErrSwapper());
    }

    GrailsTestTypeResult doRun(GrailsTestEventPublisher eventPublisher) {
        return new GrailsStoryTypeResult(createRunner(eventPublisher).runTests(builder.suite))
    }
}
