package cat.kiwi.simple.context

import cat.kiwi.simple.context.impl.SimpleContextBuilder
import cat.kiwi.simple.context.impl.SimpleContextOption

interface SimpleContext {
    companion object {
        @JvmStatic
        fun simpleContext(): SimpleContext{
            return simpleContext(SimpleContextOption())
        }
        @JvmStatic
        fun simpleContext(simpleContextOption: SimpleContextOption): SimpleContext {
            return SimpleContextBuilder.build(simpleContextOption)
        }
    }

    fun createHttpServer(): SimpleHttpServer

}