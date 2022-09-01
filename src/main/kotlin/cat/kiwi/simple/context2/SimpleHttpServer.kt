package cat.kiwi.simple.context2

import java.net.InetAddress

interface SimpleHttpServer {
    val address: String
    val port: Int

    fun listen(address: String): SimpleHttpServer
    fun listen(port:Int): SimpleHttpServer
    fun listen(address:String, port:Int): SimpleHttpServer

    fun route(simpleRouter: SimpleRouter): SimpleHttpServer
    fun start(): SimpleHttpServer
}
