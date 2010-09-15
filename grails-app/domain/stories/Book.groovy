package stories

class Book {
    String title

    static constraints = {
        title(unique: true)
    }
}
