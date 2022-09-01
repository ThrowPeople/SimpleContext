package cat.kiwi.simple.context2.impl

import cat.kiwi.simple.context2.SimpleContext
import cat.kiwi.simple.context2.impl.SimpleContextOption

class SimpleContextBuilder(val simpleContextOption: SimpleContextOption) {
    companion object {
        fun build(simpleContextOption: SimpleContextOption): SimpleContext {
            return SimpleContextImpl(simpleContextOption)
        }
    }
}