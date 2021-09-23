package cat.kiwi.simple.context.handler

import cat.kiwi.simple.context.context.SimpleContext
import cat.kiwi.simple.context.logger.Logger
import cat.kiwi.simple.context.logger.info
import cat.kiwi.simple.context.router.SimpleRouter
import cat.kiwi.simple.context.template.SimpleTemplate
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class SocketClientHandler(var clientSocket: Socket, var router: SimpleRouter) : Runnable {
    val bOut = PrintWriter(clientSocket.getOutputStream(), true)
    private val bIn = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
    val req: String = bIn.readLine()

    override fun run() {
        when (req.reqType) {
            "GET" -> getHandler()
            "POST" -> todoHandler()

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
    val path = req.path
    val ctx = this.router.httpGet[path]

    if (ctx != null) {
        ctx(SimpleContext(req, bOut))
    } else {
        bOut.println(SimpleTemplate.renderNotFound())
    }
}

fun SocketClientHandler.todoHandler() {
    bOut.println(SimpleTemplate.renderOK("TODO"))
}


fun SocketClientHandler.unknownHandler() {
    bOut.println(SimpleTemplate.renderOK("TODO"))
}

val String.isGet: Boolean
    get() {
        if (this.isEmpty()) return false

        val payloadHeader = this.split(" ")[0]
        if (payloadHeader == "GET") return true

        return false
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
        if (this.isGet) return "GET"
        // TODO: implement other http methods
        return "UNKNOWN"
    }