import stories.Book
import groovyx.net.http.RESTClient

story "This is a story", {
    before {
    }

    after {

    }

    scenario "A scenario in the story", {
        def item = new RESTClient('http://localhost:8080/syi/api/items/')
        def resp = item.get([path: "22050"])
        def data = resp.data
        // println "El item Id es ${data.id}"
        // println "La categ Id es ${data.categId}"
        assert 200 == resp.status
        assert 22050 == data.id
    }

    scenario "Another scenario in the story", {
        assert 1 == 0
    }

    scenario "domain", {
        def b = new Book()
    }
}
