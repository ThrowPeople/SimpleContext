package cat.kiwi.simple.context.context.impl

import cat.kiwi.simple.context.context.HttpRoutingContext
import cat.kiwi.simple.context.utils.ext.readHeaders
import java.io.BufferedReader
import java.io.PrintWriter

open class HttpRoutingContextImpl(
    val bIn: BufferedReader,
    val bOut: PrintWriter
): HttpRoutingContext {
    val internalHeader: Map<String, String> = bIn.readHeaders()

    var response = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n"

    override fun getHeader(key: String): String? {
        return internalHeader[key]
    }

    override fun write(data: String) {
        response+= data
    }

    override fun writeln(data: String) {
        response+=data
        response+="\r\n"
    }

    override fun end() {
        bOut.print(response)
        bOut.close()
    }

    override fun end(data: String) {
        response+=data
        bOut.print(response)
        bOut.close()
    }
}