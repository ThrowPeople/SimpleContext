package cat.kiwi.simple.application.controller

import cat.kiwi.simple.application.service.ExampleService
import cat.kiwi.simple.application.service.impl.ExampleServiceImpl
import cat.kiwi.simple.context.context.HttpGetContext
import cat.kiwi.simple.context.controller.BaseController
import cat.kiwi.simple.context.router.SimpleRouter

class ExamplePageController : BaseController {
    private val greetingService: ExampleService = ExampleServiceImpl()
    override fun register(router: SimpleRouter) {
        router.get("/getCalendar", ::getCalender)
    }

    private fun getCalender(ctx: HttpGetContext) {
        val resultMap = HashMap<String, Any>()

        val dateTime = greetingService.getCalender()

        resultMap["time"] = dateTime
        ctx.end(resultMap.toString())
    }
}
