package cat.kiwi.simple.context2.router

import cat.kiwi.simple.context2.context.HttpGetContext
import cat.kiwi.simple.context2.context.HttpPostContext
import cat.kiwi.simple.context2.context.HttpRoutingContext
import cat.kiwi.simple.context2.router.impl.SimpleRouterImpl

interface SimpleRouter {
    companion object {
        @JvmStatic
        fun build() = SimpleRouterImpl()
    }

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