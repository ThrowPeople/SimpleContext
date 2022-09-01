package cat.kiwi.simple.context2

import cat.kiwi.simple.context2.impl.SimpleRouterImpl

interface SimpleRouter {
    companion object {
        @JvmStatic
        fun router() = SimpleRouterImpl()
    }

    fun route(path: String, response: (SimpleCtx) -> Unit)
    fun get(path: String, response: (SimpleCtx) -> Unit)
    fun post(path: String, response: (SimpleCtx) -> Unit)
    fun put(path: String, response: (SimpleCtx) -> Unit)
    fun delete(path: String, response: (SimpleCtx) -> Unit)
    fun patch(path: String, response: (SimpleCtx) -> Unit)
    fun head(path: String, response: (SimpleCtx) -> Unit)
    fun options(path: String, response: (SimpleCtx) -> Unit)
    fun trace(path: String, response: (SimpleCtx) -> Unit)
    fun connect(path: String, response: (SimpleCtx) -> Unit)


}