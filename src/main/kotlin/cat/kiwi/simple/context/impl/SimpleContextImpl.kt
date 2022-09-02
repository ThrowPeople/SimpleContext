package cat.kiwi.simple.context.impl

import cat.kiwi.simple.context.SimpleContext
import cat.kiwi.simple.context.SimpleHttpServer

class SimpleContextImpl(val simpleContextOption: SimpleContextOption) : SimpleContext {
    override fun createHttpServer(): SimpleHttpServer {
        return SimpleHttpServerImpl()
    }


}