package cat.kiwi.simple.context2.handler.impl

import cat.kiwi.simple.context2.consts.HttpRequestMethod
import cat.kiwi.simple.context2.consts.HttpProtocol
import cat.kiwi.simple.context2.handler.SocketClientHandler
import cat.kiwi.simple.context2.impl.SimpleHttpServerImpl
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class SocketClientHandlerImpl(private val clientSocket: Socket, val simpleContext: SimpleHttpServerImpl) :
    SocketClientHandler {

    val bIn = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
    val bOut = PrintWriter(clientSocket.getOutputStream(), true)

    init {
        readALl()

    }

    fun readALl(): String {
        val (method,path,protocol) = getMPP(bIn.readLine())
        println("method:$method,path:$path,protocol:$protocol")

        bOut.println("end")
        return ""
    }

    fun getMPP(s: String): Triple<HttpRequestMethod, String, HttpProtocol> {
        val retMethod: HttpRequestMethod
        val retHttpProtocol: HttpProtocol

        val split = s.split(" ")
        val method = split[0]
        val path = split[1]
        val version = split[2]
        retMethod = when (method) {
            "GET" -> HttpRequestMethod.GET
            "POST" -> HttpRequestMethod.POST
            "PUT" -> HttpRequestMethod.PUT
            "DELETE" -> HttpRequestMethod.DELETE
            "HEAD" -> HttpRequestMethod.HEAD
            "OPTIONS" -> HttpRequestMethod.OPTIONS
            "TRACE" -> HttpRequestMethod.TRACE
            "CONNECT" -> HttpRequestMethod.CONNECT
            else -> HttpRequestMethod.UNKNOWN
        }
        retHttpProtocol = when (version) {
            "HTTP/1.0" -> HttpProtocol.HTTP_1_0
            "HTTP/1.1" -> HttpProtocol.HTTP_1_1
            "HTTP/2.0" -> HttpProtocol.HTTP_2_0
            "HTTP/3.0" -> HttpProtocol.HTTP_3_0
            else -> HttpProtocol.UNKNOWN
        }
        return Triple(retMethod, path, retHttpProtocol)
    }

    fun close() {
        clientSocket.close()
    }

    override fun run() {
    }
}