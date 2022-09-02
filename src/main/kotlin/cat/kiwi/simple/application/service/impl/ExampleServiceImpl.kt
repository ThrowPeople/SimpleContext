package cat.kiwi.simple.application.service.impl

import cat.kiwi.simple.application.mapper.ExampleMapper
import cat.kiwi.simple.application.service.ExampleService

class ExampleServiceImpl : ExampleService {
    private val exampleMapper = ExampleMapper()

    override fun getCalender(): String {
        return exampleMapper.timeString
    }
}