loadStoryTestTypeClass = { ->
    def doLoad = { -> classLoader.loadClass('stories.GrailsStoryTestType') }
    try {
        doLoad()
    } catch (ClassNotFoundException e) {
        includeTargets << grailsScript("_GrailsCompile") 
        compile()
        doLoad()
    }  
}

eventAllTestsStart = {
    loadStoryTestTypeClass()
    def cl = Thread.currentThread().contextClassLoader

    functionalTests << cl.loadClass("stories.GrailsStoryTestType").newInstance('stories', 'stories')
}


