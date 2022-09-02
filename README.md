It's just a project like a toy, it's not very well implementation, bu to be taken seriously

# SimpleContext

A Simple Web Framework written in kotlin

## Usage

### [Create a minimal web server](https://github.com/ThrowPeople/SimpleContext/tree/main/src/main/kotlin/cat/kiwi/simple/examples/minimal)

```kotlin
val simpleContext: SimpleContext = SimpleContext.simpleContext()
val simpleRouter = SimpleRouter.build()

simpleRouter.get("/ping") { ctx ->
    ctx.end()
}
simpleContext
    .createHttpServer()
    .route(simpleRouter)
    .listen("127.0.0.1", 8080)
    .start()
```

### [SimpleMVC](https://github.com/ThrowPeople/SimpleContext/tree/main/src/main/kotlin/cat/kiwi/simple/examples/mvc)

### Controller

```kotlin
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
```

### Service

```kotlin
interface ExampleService {
    fun getCalender(): String
}

class ExampleServiceImpl : ExampleService {
    private val exampleMapper = ExampleMapper()

    override fun getCalender(): String {
        return exampleMapper.timeString
    }
}
```

### Mapper

```kotlin
class ExampleMapper {
    // Mock Mapper
    var timeString: String = Calendar.getInstance().toString()
}
```