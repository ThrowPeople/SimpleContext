package cat.kiwi.simple.context.context.impl

import cat.kiwi.simple.context.context.HttpGetContext
import java.io.BufferedReader
import java.io.PrintWriter

class HttpGetContextImpl(bIn: BufferedReader, bOut: PrintWriter): HttpRoutingContextImpl(bIn, bOut), HttpGetContext {

}