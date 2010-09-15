import stories.Book

story "This tests session rollback", {

    before {
        def b = new Book(title: "Title")
        b.save(flush: true)
    }

    scenario "Test scenario", {
        assert 1==1
    }

    scenario "Second scenario to check making before twice", {
        assert 2==2
    }


}