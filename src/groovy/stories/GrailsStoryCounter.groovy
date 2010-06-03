package stories

class GrailsStoryCounter {
    def tests = 0

    def beforeStory(yield){
    }

    def afterStory(yield){
    }

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