package cat.kiwi.simple.context

import cat.kiwi.simple.context.handler.SocketClientHandler
import cat.kiwi.simple.context.router.SimpleRouter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket




object SimpleBuilder {
    private var address = "127.0.0.1"
    private var port = 8080
    lateinit var simpleRouter: SimpleRouter

    fun route(simpleRouter: SimpleRouter): SimpleBuilder {
        SimpleBuilder.simpleRouter = simpleRouter
        return this
    }

    fun listen(address: String, port: Int): SimpleBuilder {
        SimpleBuilder.address = address
        SimpleBuilder.port = port
        return this
    }

    fun listen(address: String): SimpleBuilder {
        SimpleBuilder.address = address
        return this
    }

    fun listen(port: Int): SimpleBuilder {
        SimpleBuilder.port = port
        return this
    }

    fun create(): Simple {
        return Simple(address, port, simpleRouter)
    }
}


