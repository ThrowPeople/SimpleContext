package cat.kiwi.simple.context.context

import cat.kiwi.simple.context.handler.firstLine
import cat.kiwi.simple.context.logger.Logger
import cat.kiwi.simple.context.logger.warn
import cat.kiwi.simple.context.template.SimpleTemplate
import cat.kiwi.simple.context.template.SimpleTemplate.renderBadRequest
import cat.kiwi.simple.context.template.SimpleTemplate.renderNotFound
import java.io.BufferedReader
import java.io.PrintWriter

class SimpleContext(var req: String, var bOut: PrintWriter) {
    val httpVersion = "HTTP/1.1"
    var respPayload = arrayListOf<String>("$httpVersion 200 OK", "", "")
}

val SimpleContext.body: String
    get() {
        return req
    }

fun SimpleContext.write(content: String) : SimpleContext{
    respPayload[respPayload.lastIndex] = respPayload[respPayload.lastIndex] + content
    return this
}

fun SimpleContext.end() {
    respPayload.add(1, "Content-Length: ${respPayload.last().length}")
    this.bOut.println(respPayload.joinToString("\n"))
}

fun SimpleContext.end(content: String) {
    this.write(content)

    respPayload.add(1, "Content-Length: ${respPayload.last().length}")
    this.bOut.println(respPayload.joinToString("\n"))
}

fun SimpleContext.badRequest() {
    this.bOut.println(renderBadRequest())
}
fun SimpleContext.notFound() {
    this.bOut.println(renderNotFound())
}

val SimpleContext.params: HashMap<String, String>
    get() {
        val result = HashMap<String, String>()
        try {
            val payload = this.req.firstLine!!.split(" ")
            if (!req.firstLine!!.contains("?")) {
                return result
            }

            val absPath = payload[1].split("?")

            val paramsPayload = absPath[1]

            paramsPayload.split("&").forEach {
                val pairs = it.split("=")
                if (pairs.size == 2) {
                    result[pairs[0]] = pairs[1]
                }
            }

        } catch (e: Exception) {
            renderBadRequest()
            Logger.warn(e)
        }

        return result
    }