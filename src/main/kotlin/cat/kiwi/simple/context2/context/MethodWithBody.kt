package cat.kiwi.simple.context2.context

interface MethodWithBody: HttpRoutingContext{
    fun getBodyAsString(): String
}