package cat.kiwi.simple.context2.impl

import cat.kiwi.simple.context2.SimpleContext
import cat.kiwi.simple.context2.SimpleHttpServer

class SimpleContextImpl(val simpleContextOption: SimpleContextOption) : SimpleContext {
    override fun createHttpServer(): SimpleHttpServer {
        return SimpleHttpServerImpl()
    }


}