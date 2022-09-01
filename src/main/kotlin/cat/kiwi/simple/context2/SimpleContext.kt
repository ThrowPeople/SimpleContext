package cat.kiwi.simple.context2

import cat.kiwi.simple.context2.impl.SimpleContextBuilder
import cat.kiwi.simple.context2.impl.SimpleContextOption

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