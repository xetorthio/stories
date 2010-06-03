package stories

import grails.converters.JSON

class RestTestController {

    def index = {
        render([ok: "1"] as JSON)
    }
}
