import stories.Book
import groovyx.net.http.RESTClient

story "This is a story", {
    before {
    }

    after {

    }
    
    scenario "A scenario in the story", {
        get(resource: "restTest/"){
            assert status == 200
            assert data.ok == "1"            
        }
    }
    scenario "Another scenario in the story", {
        assert 1 == 0
    }

    scenario "domain", {
        def b = new Book()
    }
}
