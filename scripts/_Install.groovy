import org.codehaus.groovy.grails.plugins.GrailsPluginUtils

includeTargets << grailsScript("_PluginDependencies")

target(install: 'install stories for grails') {
    ant.mkdir(dir:"${basedir}/test/stories")
}

install()
