package cat.kiwi.simple

import cat.kiwi.simple.context.SimpleBuilder
import cat.kiwi.simple.context.context.*
import cat.kiwi.simple.context.logger.Logger
import cat.kiwi.simple.context.logger.info
import cat.kiwi.simple.context.router.SimpleRouter
import cat.kiwi.simple.context.router.get
import cat.kiwi.simple.context.router.post
import cat.kiwi.simple.context.template.SimpleTemplate
import java.sql.Timestamp
import java.util.*

class Application {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val simpleRouter = SimpleRouter()
            simpleRouter.get("/ok") { ctx ->
                ctx.end("${Date()}\n")
            }
            simpleRouter.get("/unicode") { ctx ->
                ctx.end("asdasd")
            }
            simpleRouter.get("/static_page") { ctx ->
                ctx.end(SimpleTemplate.renderStaticPage("index.html"))
            }
            simpleRouter.get("/dynamic_page") { ctx ->
                val contents = HashMap<String, String>()

                contents["time"] = Timestamp(Date().time).toString()

                ctx.end(SimpleTemplate.renderDynamicPage("template.html", contents))
            }

            simpleRouter.get("/") { ctx ->
                if (ctx.params.size == 0) {
                    ctx.end("No params get")
                } else {
                    Logger.info("Params: ${ctx.params}")
                    ctx.write("These are Params: ")
                    ctx.write(ctx.params.toString())
                    ctx.end()
                }
            }

            simpleRouter.get("/ping") { ctx ->
                ctx.end("pong")
            }
            simpleRouter.get("/foo") { ctx ->
                ctx.badRequest()
            }

            SimpleBuilder.listen("127.0.0.1", 8081).route(simpleRouter).create().start()
        }
    }
}