package cat.kiwi.simple.examples.minimal

import cat.kiwi.simple.context.SimpleContext
import cat.kiwi.simple.context.router.SimpleRouter

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val simpleContext: SimpleContext = SimpleContext.simpleContext()
            val simpleRouter = SimpleRouter.build()

            simpleRouter.get("/ping") { ctx ->
                ctx.end()
            }
            simpleContext
                .createHttpServer()
                .route(simpleRouter)
                .listen("127.0.0.1", 8080)
                .start()
        }
    }
}