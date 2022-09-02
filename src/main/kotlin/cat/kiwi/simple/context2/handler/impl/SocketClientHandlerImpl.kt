package cat.kiwi.simple.context2.handler.impl

import cat.kiwi.simple.context2.consts.HttpProtocol
import cat.kiwi.simple.context2.consts.HttpRequestMethod
import cat.kiwi.simple.context2.context.HttpGetContext
import cat.kiwi.simple.context2.context.HttpPostContext
import cat.kiwi.simple.context2.context.impl.HttpGetContextImpl
import cat.kiwi.simple.context2.context.impl.HttpPostContextImpl
import cat.kiwi.simple.context2.handler.SocketClientHandler
import cat.kiwi.simple.context2.impl.SimpleHttpServerImpl
import cat.kiwi.simple.context2.utils.ext.ByteSize
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.nio.CharBuffer
import javax.xml.ws.spi.http.HttpExchange


class SocketClientHandlerImpl(private val clientSocket: Socket, val simpleContext: SimpleHttpServerImpl) :
    SocketClientHandler {

    val bIn = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
    val bOut = PrintWriter(clientSocket.getOutputStream(), true)
    val interlRouter = simpleContext.internalRouter


    init {
        readALl()

    }

    fun readALl(): String {
        val (method, path, protocol) = getMPP(bIn.readLine())
        var contentLength = 0

        try {

            when (method) {
            HttpRequestMethod.GET -> {
                interlRouter.httpGet[path]!!.invoke(HttpGetContextImpl(bIn, bOut))

                bOut.close()
                clientSocket.close()
            }

            HttpRequestMethod.POST -> {
                interlRouter.httpPost[path]!!.invoke(HttpPostContextImpl(bIn, bOut))

                bOut.close()
                clientSocket.close()
            }

            else -> {
                bOut.println("HTTP/1.1 405 Method Not Allowed\n")
            }
        }


        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            clientSocket.close()
        }

        return ""
    }

    private fun BufferedReader.getBodyAsString(header: Map<String, String>): String {
        var contentLength = header["Content-Length"]?.toInt() ?: 0
        val body = StringBuilder()
        while (contentLength > 0) {
            val c = read()
            body.append(c.toChar())
            contentLength -= c.ByteSize
        }
        return body.toString()
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

private fun <K, V> Map<K, V>.display() {
    println(this.map {
        "${it.key}: ${it.value}"
    }.joinToString("\r\n"))
}
