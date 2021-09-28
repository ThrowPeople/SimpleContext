package cat.kiwi.simple.context.context

import java.io.PrintWriter

class SimplePostContext(req: String, bOut: PrintWriter) :SimpleContext(req, bOut) {
}
val SimplePostContext.body: String
    get() {
        return req.split("\n").last()
    }
