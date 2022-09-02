package cat.kiwi.simple.context.context

interface MethodWithBody: HttpRoutingContext{
    fun getBodyAsString(): String
}