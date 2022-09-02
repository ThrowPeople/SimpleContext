package cat.kiwi.simple.context2.context.impl

import cat.kiwi.simple.context2.context.HttpGetContext
import java.io.BufferedReader
import java.io.PrintWriter

class HttpGetContextImpl(bIn: BufferedReader, bOut: PrintWriter): HttpRoutingContextImpl(bIn, bOut), HttpGetContext {

}