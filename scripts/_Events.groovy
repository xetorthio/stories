eventAllTestsStart = {
    def cl = Thread.currentThread().contextClassLoader

    functionalTests << cl.loadClass("stories.GrailsStoryTestType").newInstance('stories', 'stories')
}


