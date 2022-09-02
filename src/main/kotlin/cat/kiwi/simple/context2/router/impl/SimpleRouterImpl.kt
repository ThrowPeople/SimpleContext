package cat.kiwi.simple.context2.router.impl

import cat.kiwi.simple.context2.context.HttpGetContext
import cat.kiwi.simple.context2.context.HttpPostContext
import cat.kiwi.simple.context2.context.HttpRoutingContext
import cat.kiwi.simple.context2.router.SimpleRouter

class SimpleRouterImpl: SimpleRouter {
    val httpGet: HashMap<String, (HttpGetContext) -> Unit> = HashMap()
    val httpPost: HashMap<String, (HttpPostContext) -> Unit> = HashMap()
    override fun route(path: String, response: (HttpRoutingContext) -> Unit) {
    }

    override fun get(path: String, response: (HttpGetContext) -> Unit) {
        httpGet[path] = response
    }

    override fun post(path: String, response: (HttpPostContext) -> Unit) {
        httpPost[path] = response
    }

    override fun put(path: String, response: (HttpRoutingContext) -> Unit) {
    }

    override fun delete(path: String, response: (HttpRoutingContext) -> Unit) {
    }

    override fun patch(path: String, response: (HttpRoutingContext) -> Unit) {
    }

    override fun head(path: String, response: (HttpRoutingContext) -> Unit) {
    }

    override fun options(path: String, response: (HttpRoutingContext) -> Unit) {
    }

    override fun trace(path: String, response: (HttpRoutingContext) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connect(path: String, response: (HttpRoutingContext) -> Unit) {
        TODO("Not yet implemented")
    }
}
