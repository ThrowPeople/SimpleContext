package cat.kiwi.simple.application

import cat.kiwi.simple.context.SimpleBuilder
import cat.kiwi.simple.context.context.*
import cat.kiwi.simple.context.logger.Logger
import cat.kiwi.simple.context.logger.info
import cat.kiwi.simple.context.router.SimpleRouter
import cat.kiwi.simple.context.router.get

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val simpleRouter = SimpleRouter()

            simpleRouter.get("/") { ctx ->
                if (ctx.params.size == 0){
                    ctx.end("No params get")
                } else {
                    Logger.info("Params: ${ctx.params}")
                    ctx.write("These are Params: ")
                    ctx.write(ctx.params.toString())
                    ctx.end()
                }
            }

            simpleRouter.get("/ping") { ctx ->
            }
            simpleRouter.get("/foo") { ctx ->
                ctx.badRequest()
            }

            SimpleBuilder.listen("127.0.0.1", 8080).route(simpleRouter).create().start()
        }
    }
}