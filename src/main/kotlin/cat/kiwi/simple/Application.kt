package cat.kiwi.simple


import cat.kiwi.simple.context2.SimpleContext
import cat.kiwi.simple.context2.SimpleHttpServer

class Application {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val simpleContext: SimpleContext = SimpleContext.simpleContext()
            val server: SimpleHttpServer = simpleContext.createHttpServer()

            server.listen("127.0.0.1", 31021).start()

        }
    }
}