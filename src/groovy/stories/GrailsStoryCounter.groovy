package stories

class GrailsStoryCounter {
    def tests = 0

    def story(name, yield) {
        yield()
    }

    def scenario(name, yield) {
        tests++
    }
}
