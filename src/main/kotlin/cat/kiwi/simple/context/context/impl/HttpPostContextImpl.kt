package cat.kiwi.simple.context.context.impl

import cat.kiwi.simple.context.context.HttpPostContext
import java.io.BufferedReader
import java.io.PrintWriter

class HttpPostContextImpl(bIn: BufferedReader, bOut: PrintWriter): MethodWithBodyImpl(bIn, bOut), HttpPostContext {

}