package cat.kiwi.simple.examples.mvc


import cat.kiwi.simple.examples.mvc.controller.ExamplePageController
import cat.kiwi.simple.context.SimpleContext
import cat.kiwi.simple.context.SimpleHttpServer
import cat.kiwi.simple.context.router.SimpleRouter

class Application {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val simpleContext: SimpleContext = SimpleContext.simpleContext()
            val server: SimpleHttpServer = simpleContext.createHttpServer()
            val simpleRouter = SimpleRouter.build()

            simpleRouter.bindController(ExamplePageController())

            server.route(simpleRouter).listen("127.0.0.1", 31021).start()

        }
    }
}