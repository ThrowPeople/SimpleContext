package cat.kiwi.simple.context.impl

import cat.kiwi.simple.context.SimpleContext

class SimpleContextBuilder(val simpleContextOption: SimpleContextOption) {
    companion object {
        fun build(simpleContextOption: SimpleContextOption): SimpleContext {
            return SimpleContextImpl(simpleContextOption)
        }
    }
}