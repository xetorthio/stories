class StoriesGrailsPlugin {
    def version = 0.14
    def title = "A BDD plugin for grails"
    def grailsVersion = "1.2.2 > *"
    def author = "Jonathan Leibiusky"
    def authorEmail = "ionathan@gmail.com"
    def description = "Stories is a BDD plugin for Grails that aims to make you life easier with functional testing. Stories does't depend on any testing framework out there. It tries to use them all, offerind a way to organize your tests as Stories and Scenarios. What you do inside each scenario is your problem :)... you can use either Selenium, WebTest, HtmlUnit, etc."
    def documentation = "http://github.com/xetorthio/stories"
    def loadAfter = ['core', 'hibernate']
    def scopes = [excludes:"war"]
    def pluginExcludes = [
        'grails-app/**/*',
        'lib/**/*',
        'web-app/**/*',
        'target/**/*',
        'test/**/*'
    ]
    def dependsOn = [:]
}
