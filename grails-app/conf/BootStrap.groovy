import com.rest.RestTest

class BootStrap {

    def init = { servletContext ->
        new RestTest(name: "Jeremy").save(failOnError: true)
        new RestTest(name: "Newman").save(failOnError: true)
    }
    def destroy = {
    }
}
