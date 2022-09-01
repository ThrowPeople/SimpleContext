package cat.kiwi.simple.context2.impl

import cat.kiwi.simple.context2.SimpleCtx
import cat.kiwi.simple.context2.SimpleRouter

class SimpleRouterImpl: SimpleRouter {
    private val httpGet: HashMap<String, SimpleCtx> = HashMap()



    override fun route(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun get(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun post(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun put(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun delete(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun patch(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun head(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun options(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun trace(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connect(path: String, response: (SimpleCtx) -> Unit) {
        TODO("Not yet implemented")
    }

}
