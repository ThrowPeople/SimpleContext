package cat.kiwi.simple.context.router

import cat.kiwi.simple.context.context.SimpleContext
import cat.kiwi.simple.context.context.SimpleGetContext
import cat.kiwi.simple.context.context.SimplePostContext

class SimpleRouter() {
    val httpGet = HashMap<String, (SimpleGetContext) -> Unit>()
    val httpPost = HashMap<String, (SimplePostContext) -> Unit>()

}

fun SimpleRouter.get(path: String, resp: (SimpleGetContext) -> Unit): SimpleRouter {
    httpGet[path] = resp
    return this
}
fun SimpleRouter.post(path: String, resp: (SimplePostContext) -> Unit): SimpleRouter {
    httpPost[path] =  resp
    return this
}
