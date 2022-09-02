package cat.kiwi.simple.context.context.impl

import cat.kiwi.simple.context.context.HttpRoutingContext
import cat.kiwi.simple.context.utils.ext.getBodyAsString
import java.io.BufferedReader
import java.io.PrintWriter

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