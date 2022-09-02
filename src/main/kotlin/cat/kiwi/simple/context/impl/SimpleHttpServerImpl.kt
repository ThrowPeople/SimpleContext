package cat.kiwi.simple.context.impl

import cat.kiwi.simple.context.SimpleHttpServer
import cat.kiwi.simple.context.handler.impl.SocketClientHandlerImpl
import cat.kiwi.simple.context.router.impl.SimpleRouterImpl
import java.net.BindException
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket

class SimpleHttpServerImpl : SimpleHttpServer {
    override var address = "127.0.0.1"
        private set
    override var port = 8080
        private set

    private lateinit var serverSocket: ServerSocket
    override fun listen(address: String): SimpleHttpServer {
        this.address = address
        return this
    }

    override fun listen(port: Int): SimpleHttpServer {
        this.port = port
        return this
    }

    override fun listen(address: String, port: Int): SimpleHttpServer {
        this.address = address
        this.port = port
        return this
    }


    var internalRouter = SimpleRouterImpl()
    override fun route(simpleRouter: SimpleRouterImpl): SimpleHttpServer {
        internalRouter = simpleRouter
        return this
    }

    override fun start(): SimpleHttpServer {
        try {
            serverSocket = ServerSocket(port, 1000, InetAddress.getByName(address))
            serverSocket.reuseAddress = true

            println("Server started at ${serverSocket.localSocketAddress}")
        } catch (e: BindException) {
            println("Port $port is already in use, trying next port")
        }

        while (true) {
            var clientSocket: Socket? = null

            try {
                clientSocket = serverSocket.accept()
                Thread(SocketClientHandlerImpl(clientSocket, this)).start()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    clientSocket?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
