package cat.kiwi.simple.context.handler

import cat.kiwi.simple.context.context.SimpleGetContext
import cat.kiwi.simple.context.context.SimplePostContext
import cat.kiwi.simple.context.logger.Logger
import cat.kiwi.simple.context.logger.warn
import cat.kiwi.simple.context.router.SimpleRouter
import cat.kiwi.simple.context.template.SimpleTemplate
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class SocketClientHandler(var clientSocket: Socket, var router: SimpleRouter) : Runnable {
    val bOut = PrintWriter(clientSocket.getOutputStream(), true)
    private val bIn = BufferedReader(InputStreamReader(clientSocket.getInputStream()))

    val req: String = bIn.readALl()

    override fun run() {
//        println(req)
        when (req.reqType) {
            "GET" -> getHandler()
            "POST" -> postHandler()

            "OPTIONS" -> todoHandler()
            "HEAD" -> todoHandler()
            "PATCH" -> todoHandler()
            "DELETE" -> todoHandler()
            "PUT" -> todoHandler()

            else -> unknownHandler()
        }
    }
}


fun SocketClientHandler.getHandler() {
    try {
        val path = req.path
        val ctx = this.router.httpGet[path]

        if (ctx != null) {
            ctx(SimpleGetContext(req, bOut))

        } else {
            bOut.println(SimpleTemplate.renderNotFound())
        }

    } catch (e: Exception) {
        bOut.println(SimpleTemplate.renderBadRequest())
    }

}

fun SocketClientHandler.postHandler() {
    try {
        val path = req.path
        val ctx = this.router.httpPost[path]

        if (ctx != null) {
            ctx(SimplePostContext(req, bOut))

        } else {
            bOut.println(SimpleTemplate.renderNotFound())
        }

    } catch (e: Exception) {
        bOut.println(SimpleTemplate.renderBadRequest())
    }
}

class Body(val content: String) {


    override fun toString(): String {
        return content
    }
}


fun SocketClientHandler.todoHandler() {
    bOut.println(SimpleTemplate.renderOK("TODO"))
}


fun SocketClientHandler.unknownHandler() {
    bOut.println(SimpleTemplate.renderOK("TODO"))
}

val String.firstLine: String?
    get() {
        val lines = this.split("\n")
        return if (lines.isEmpty()) {
            null
        } else {
            this.split("\n")[0]
        }
    }

val String.path: String?
    get() {
        val payloads = this.firstLine!!.split(" ")
        return if (payloads.size >= 3) {
            payloads[1].split("?")[0]
        } else {
            null
        }
    }

val String.reqType: String
    get() {
        if (this.isEmpty()) return "UNKNOWN"
        return this.split(" ")[0]
    }

fun BufferedReader.readALl(): String {
    val tmp = arrayListOf<String>()
    var contentLength = 0
    while (true) {
        val line = this.readLine()
        if (line.contains("Content-Length: ")) {
            try {
                contentLength = line.split("Content-Length: ")[1].toInt()
            } catch (e: Exception) {
                Logger.warn("Invalid content length")
                return ""
            }
        }
        if (line == "") {
            break
        }
        tmp.add(line)
    }
    if (contentLength != 0) {
        val bodyArray = arrayListOf<Byte>()
        for (i in 0 until contentLength) {
            bodyArray.add(this.read().toByte())
        }
        tmp.add(String(bodyArray.toByteArray()))
    }
    return tmp.joinToString("\n")
}
