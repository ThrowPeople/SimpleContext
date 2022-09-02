package cat.kiwi.simple.context.router

import cat.kiwi.simple.context.context.HttpGetContext
import cat.kiwi.simple.context.context.HttpPostContext
import cat.kiwi.simple.context.context.HttpRoutingContext
import cat.kiwi.simple.context.controller.BaseController
import cat.kiwi.simple.context.router.impl.SimpleRouterImpl

interface SimpleRouter {
    companion object {
        @JvmStatic
        fun build() = SimpleRouterImpl()
    }

    fun bindController(controller: BaseController)
    fun route(path: String, response: (HttpRoutingContext) -> Unit)
    fun get(path: String, response: (HttpGetContext) -> Unit)
    fun post(path: String, response: (HttpPostContext) -> Unit)
    fun put(path: String, response: (HttpRoutingContext) -> Unit)
    fun delete(path: String, response: (HttpRoutingContext) -> Unit)
    fun patch(path: String, response: (HttpRoutingContext) -> Unit)
    fun head(path: String, response: (HttpRoutingContext) -> Unit)
    fun options(path: String, response: (HttpRoutingContext) -> Unit)
    fun trace(path: String, response: (HttpRoutingContext) -> Unit)
    fun connect(path: String, response: (HttpRoutingContext) -> Unit)


}