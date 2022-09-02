package cat.kiwi.simple.examples.mvc.controller

import cat.kiwi.simple.examples.mvc.service.ExampleService
import cat.kiwi.simple.examples.mvc.service.impl.ExampleServiceImpl
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
        ctx.getHeader("")

        resultMap["time"] = dateTime
        ctx.end(resultMap.toString())
    }
}
