package cat.kiwi.simple.context.context

interface HttpRoutingContext {

    fun getHeader(key: String): String?
    fun write(data: Any)
    fun writeln(data: Any)
    fun end()
    fun end(data: Any)
}