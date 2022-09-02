package cat.kiwi.simple.context.handler.impl

import cat.kiwi.simple.context.consts.HttpProtocol
import cat.kiwi.simple.context.consts.HttpRequestMethod
import cat.kiwi.simple.context.context.HttpGetContext
import cat.kiwi.simple.context.context.HttpRoutingContext
import cat.kiwi.simple.context.context.impl.HttpGetContextImpl
import cat.kiwi.simple.context.context.impl.HttpPostContextImpl
import cat.kiwi.simple.context.handler.SocketClientHandler
import cat.kiwi.simple.context.impl.SimpleHttpServerImpl
import cat.kiwi.simple.context.status.HttpResponseStatus
import cat.kiwi.simple.context.utils.ext.ByteSize
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket


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
                val httpRoutingContext: ((HttpGetContext) -> Unit)? = interlRouter.httpGet[path]
                if (httpRoutingContext == null) {
                    bOut.println(HttpResponseStatus.NOT_FOUND_404)
                } else {
                    interlRouter.httpGet[path]!!.invoke(HttpGetContextImpl(bIn, bOut))
                }

                clientSocket.close()
            }

            HttpRequestMethod.POST -> {
                val httpRoutingContext: ((HttpPostContextImpl) -> Unit)? = interlRouter.httpPost[path]
                if (httpRoutingContext == null) {
                    bOut.println(HttpResponseStatus.NOT_FOUND_404)
                } else {
                    interlRouter.httpPost[path]!!.invoke(HttpPostContextImpl(bIn, bOut))
                }

                clientSocket.close()
            }

            else -> {
                bOut.println(HttpResponseStatus.NOT_IMPLEMENTED_501)
            }
        }



        } catch (e: Exception) {
            bOut.println(HttpResponseStatus.INTERNAL_SERVER_ERROR_500)
            e.printStackTrace()
        } finally {
            bOut.close()
            if (!clientSocket.isClosed) clientSocket.close()
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
