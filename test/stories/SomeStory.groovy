story "This is a story", {
    scenario "A scenario in the story", {
        twitter = new groovyx.net.http.RESTClient('https://twitter.com/statuses/')
    }

    scenario "Another scenario in the story", {
        assert 1 == 0
    }
}
