package cat.kiwi.simple


import cat.kiwi.simple.context2.SimpleContext
import cat.kiwi.simple.context2.SimpleHttpServer
import cat.kiwi.simple.context2.router.SimpleRouter

class Application2 {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val simpleContext: SimpleContext = SimpleContext.simpleContext()
            val server: SimpleHttpServer = simpleContext.createHttpServer()
            val simpleRouter = SimpleRouter.build()
            simpleRouter.get("/hello") { ctx ->
                val host = ctx.getHeader("Host")!!
                ctx.writeln("It's Working!")
                ctx.write("Hello, $host")
                ctx.end("!")
            }
            simpleRouter.post("/post") { ctx ->
                val host = ctx.getHeader("Host")!!
                ctx.writeln("It's Working!")
                ctx.writeln("Body: ")
                ctx.end(ctx.getBodyAsString())
            }

            server.route(simpleRouter).listen("127.0.0.1", 31021).start()

        }
    }
}