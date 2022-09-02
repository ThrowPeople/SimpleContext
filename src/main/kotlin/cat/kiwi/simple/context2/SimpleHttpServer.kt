package cat.kiwi.simple.context2

import cat.kiwi.simple.context2.router.SimpleRouter
import cat.kiwi.simple.context2.router.impl.SimpleRouterImpl

interface SimpleHttpServer {
    val address: String
    val port: Int

    fun listen(address: String): SimpleHttpServer
    fun listen(port:Int): SimpleHttpServer
    fun listen(address:String, port:Int): SimpleHttpServer

    fun route(simpleRouter: SimpleRouterImpl): SimpleHttpServer
    fun start(): SimpleHttpServer
}
