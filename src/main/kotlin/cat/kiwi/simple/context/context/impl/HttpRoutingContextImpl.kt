package cat.kiwi.simple.context.context.impl

import cat.kiwi.simple.context.context.HttpRoutingContext
import cat.kiwi.simple.context.status.HttpResponseStatus
import cat.kiwi.simple.context.utils.ext.readHeaders
import java.io.BufferedReader
import java.io.PrintWriter

open class HttpRoutingContextImpl(
    val bIn: BufferedReader,
    val bOut: PrintWriter
): HttpRoutingContext {
    val internalHeader: Map<String, String> = bIn.readHeaders()

    private var response = HttpResponseStatus.OK_200

    override fun getHeader(key: String): String? {
        return internalHeader[key]
    }

    override fun write(data: Any) {
        response+= data.toString()
    }

    override fun writeln(data: Any) {
        response+=data.toString()
        response+="\r\n"
    }

    override fun end() {
        bOut.print(response)
        bOut.close()
    }

    override fun end(data: Any) {
        response+=data.toString()
        bOut.print(response)
        bOut.close()
    }
}