package cat.kiwi.simple.context2.context

interface HttpRoutingContext {

    fun getHeader(key: String): String?
    fun write(data: String)
    fun writeln(data: String)
    fun end()
    fun end(data: String)
}