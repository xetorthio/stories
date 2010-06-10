package stories.junit

import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher;
import org.codehaus.groovy.grails.test.io.SystemOutAndErrSwapper;
import org.codehaus.groovy.grails.test.report.junit.JUnitReportsFactory;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.framework.Test;

import java.io.OutputStream;
import java.util.List;

import org.codehaus.groovy.grails.test.junit3.JUnit3ListenerEventPublisherAdapter
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;
import org.codehaus.groovy.grails.test.report.junit.JUnitReports;

class JUnitGrailsStoryTestTypeRunner {
    private SystemOutAndErrSwapper outAndErrSwapper;
    private JUnitReportsFactory reportsFactory;
    private GrailsTestEventPublisher eventPublisher;
    
    public JUnitGrailsStoryTestTypeRunner(JUnitReportsFactory reportsFactory, GrailsTestEventPublisher eventPublisher, SystemOutAndErrSwapper outAndErrSwapper) {
        this.reportsFactory = reportsFactory;
        this.eventPublisher = eventPublisher;
        this.outAndErrSwapper = outAndErrSwapper;
    }

    public TestResult runTests(TestSuite suite) {
        TestResult result = new TestResult();

        JUnit3ListenerEventPublisherAdapter eventPublisherAdapter = new JUnit3ListenerEventPublisherAdapter(eventPublisher);
        result.addListener(eventPublisherAdapter);

        suite.tests().each { test ->

            JUnitTest junitTest = new JUnitTest(test.getName());
            JUnitReports reports = reportsFactory.createReports(test.getName());
            
            try {
                outAndErrSwapper.swapIn();

                result.addListener(reports);

                reports.startTestSuite(junitTest);
                eventPublisherAdapter.startTestSuite(junitTest);

                // Starting...now!
                long start = System.currentTimeMillis();
                int runCount = result.runCount();
                int failureCount = result.failureCount();
                int errorCount = result.errorCount();

                test.tests().each { t ->
                    System.out.println("--Output from " + t.getName() + "--");
                    System.err.println("--Output from " + t.getName() + "--");

                    test.runTest(t, result)
                }
                junitTest.setCounts(
                        result.runCount() - runCount,
                        result.failureCount() - failureCount,
                        result.errorCount() - errorCount);
                junitTest.setRunTime(System.currentTimeMillis() - start);
            }
            finally {
                List<OutputStream> outAndErr = outAndErrSwapper.swapOut();
                String out = outAndErr.get(0).toString();
                String err = outAndErr.get(1).toString();

                reports.setSystemOutput(out);
                reports.setSystemError(err);
                reports.endTestSuite(junitTest);

                eventPublisherAdapter.endTestSuite(junitTest, out, err);
            }

            result.removeListener(reports);
        }

        return result;
    }

}
