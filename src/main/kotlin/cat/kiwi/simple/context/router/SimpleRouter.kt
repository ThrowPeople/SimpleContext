package cat.kiwi.simple.context.router

import cat.kiwi.simple.context.context.SimpleContext

class SimpleRouter() {
    val httpGet = HashMap<String, (SimpleContext) -> Unit>()
}

fun SimpleRouter.get(path: String, resp: (SimpleContext) -> Unit): SimpleRouter {
    httpGet[path] = resp
    return this
}
