package cat.kiwi.simple.context.context

interface HttpRoutingContext {

    fun getHeader(key: String): String?
    fun write(data: String)
    fun writeln(data: String)
    fun end()
    fun end(data: String)
}