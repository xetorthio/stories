package stories

import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport;

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
        eachSource { pattern, file ->
            println file
        }
        return 1;
    }

    GrailsTestTypeResult doRun(GrailsTestEventPublisher eventPublisher) {
        return new StoriesGrailsTestTypeResult()    
    }
}
