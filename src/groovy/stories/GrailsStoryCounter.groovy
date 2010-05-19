package stories

class GrailsStoryCounter {
    def tests = 0

    def before(yield) {
    }

    def after(yield) {
    }

    def story(name, yield) {
        yield()
    }

    def scenario(name, yield) {
        tests++
    }
}
