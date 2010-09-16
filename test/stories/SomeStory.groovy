import stories.Book

story "This is a story", {
    def ranBefore = false;
    def ranAfter = false;

    before {
        ranBefore = true;
        assert Book.findByTitle("Test") == null //This tests that every story is complete independant
        def b = new Book()
        b.title = "Test"
        b.save()
    }

    after {
        ranAfter = true;
        Book.executeUpdate("delete Book")
    }

    scenario "It is possible to test RESTful services", {
        get(resource: "restTest/index"){
            assert status == 200
            assert data.ok == "1"            
        }
    }

    scenario "It is possible to make regular assertions", {
        assert 1 == 1
    }

    scenario "It is possible to use domains", {
        def b = new Book()
        b.title = "TestTitle"
        b.save()
        assert b != null;
    }

    scenario "It runs the before closure previous to the scenario", {
        assert ranBefore
    }

    scenario "It runs the after closure next to the scenario", {
        assert ranAfter
    }

    scenario "It is transactional", {
        assert Book.findByTitle("TestTitle") == null
    }
}
