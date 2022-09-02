package cat.kiwi.simple.context.controller

import cat.kiwi.simple.context.router.SimpleRouter

interface BaseController {
    fun register(router: SimpleRouter)
}