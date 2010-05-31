import stories.Book
import groovyx.net.http.RESTClient

story "This is a story", {
    before {
    }

    after {

    }

    scenario "Another scenario in the story", {
        assert 1 == 0
    }

    scenario "domain", {
        def b = new Book()
    }
}
