package cat.kiwi.simple.context

import cat.kiwi.simple.context.context.const.ExitCode.BIND_EXCEPTION
import cat.kiwi.simple.context.handler.SocketClientHandler
import cat.kiwi.simple.context.logger.Logger
import cat.kiwi.simple.context.logger.info
import cat.kiwi.simple.context.logger.warn
import cat.kiwi.simple.context.router.SimpleRouter
import java.net.BindException
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import kotlin.system.exitProcess

class Simple(
    private var address: String = "127.0.0.1",
    private var port: Int = 8080,
    private var router: SimpleRouter
) {
    private lateinit var serverSocket: ServerSocket

    fun start() {
        Logger.info("Simple Context Started!")
        while (true) {
            try {
                serverSocket = ServerSocket(port, 1000, InetAddress.getByName(address))
                serverSocket.reuseAddress = true
                val clientSocket: Socket = serverSocket.accept()
                Thread(SocketClientHandler(clientSocket, router)).start()
            } catch (e: BindException) {
                Logger.warn(e)
                exitProcess(BIND_EXCEPTION)
            } catch (e: NullPointerException) {
                Logger.warn("Client send no data.")
            } catch (e: Exception) {
                Logger.warn(e)
            }
            finally {
                try {
                    serverSocket.close()
                } catch (e: Exception) {
                    Logger.warn(e)
                }
            }
        }
    }
}
