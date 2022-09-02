package cat.kiwi.simple.context2.context.impl

import cat.kiwi.simple.context2.context.HttpRoutingContext
import cat.kiwi.simple.context2.utils.ext.getBodyAsString
import java.io.BufferedReader
import java.io.PrintWriter
import javax.xml.ws.spi.http.HttpContext

open class MethodWithBodyImpl(bIn:BufferedReader, bOut: PrintWriter): HttpRoutingContextImpl(bIn, bOut), HttpRoutingContext {
        var isBodyRead = false
        private val internalBody = ""
            get() {
                if (!isBodyRead) {
                    return bIn.getBodyAsString(internalHeader)
                }
                return field
            }


        fun getBodyAsString(): String = internalBody
}