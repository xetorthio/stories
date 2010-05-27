import stories.Book

story "This is a story", {
    before {
    }

    after {

    }

    scenario "A scenario in the story", {
        twitter = new groovyx.net.http.RESTClient('https://twitter.com/statuses/')
    }

    scenario "Another scenario in the story", {
        assert 1 == 0
    }

    scenario "domain", {
        def b = new Book()
    }
}
