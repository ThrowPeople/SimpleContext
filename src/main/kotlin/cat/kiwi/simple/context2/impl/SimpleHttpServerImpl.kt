package cat.kiwi.simple.context2.impl

import cat.kiwi.simple.context2.SimpleHttpServer
import cat.kiwi.simple.context2.SimpleRouter
import cat.kiwi.simple.context2.handler.impl.SocketClientHandlerImpl
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

    override fun route(simpleRouter: SimpleRouter): SimpleHttpServer {
        TODO("Not yet implemented")
    }

    override fun start(): SimpleHttpServer {
        while (true) {
            try {
                serverSocket = ServerSocket(port, 1000, InetAddress.getByName(address))
                serverSocket.reuseAddress = true

                println("Server started at ${serverSocket.localSocketAddress}")

                val clientSocket = serverSocket.accept()
                Thread(SocketClientHandlerImpl(clientSocket, this)).start()


            } catch (e:Exception) {
                e.printStackTrace()
            } finally {
                try {
                    serverSocket.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
