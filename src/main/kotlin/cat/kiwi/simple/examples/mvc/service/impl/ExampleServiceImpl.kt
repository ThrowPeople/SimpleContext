package cat.kiwi.simple.examples.mvc.service.impl

import cat.kiwi.simple.examples.mvc.mapper.ExampleMapper
import cat.kiwi.simple.examples.mvc.service.ExampleService

class ExampleServiceImpl : ExampleService {
    private val exampleMapper = ExampleMapper()

    override fun getCalender(): String {
        return exampleMapper.timeString
    }
}