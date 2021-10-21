package cat.kiwi.simple.context.template

import cat.kiwi.simple.context.logger.Logger
import cat.kiwi.simple.context.logger.warn
import java.io.InputStreamReader

val webroot = "webroot/"

object SimpleTemplate {
    fun renderDynamicPage(path: String, contents: HashMap<String, String>):String {
        try {
            val inputStream = javaClass.classLoader.getResourceAsStream("$webroot/$path")
            var payload = InputStreamReader(inputStream, Charsets.UTF_8).readText()
            contents.forEach { (t, u) ->
                payload = payload.replace("{{.$t}}", u)
            }
            return payload
        } catch (e: Exception) {
            Logger.warn(e)
        }

        return ""

    }
    fun renderStaticPage(path: String): String {
        try {
            val inputStream = javaClass.classLoader.getResourceAsStream("$webroot/$path")
            return InputStreamReader(inputStream, Charsets.UTF_8).readText()
        } catch (e: Exception) {
            Logger.warn(e)
        }

        return ""
    }

    fun renderOK(content: String = ""): String {
        return "HTTP/1.1 200 OK\n" +
                "Content-Length: ${content.length}\n" +
                "Content-Type: text/html\n" +
                "\n" +
                content
    }

    fun renderBadRequest(content: String = "<h1>400 Bad Request</h1>"): String {
        return "HTTP/1.1 400 Bad Request\n" +
                "Content-Length: ${content.length}\n" +
                "Content-Type: text/html\n" +
                "\n" +
                content
    }
    fun renderNotFound(content: String="<h1>404 Not Found</h1>"): String {
        return "HTTP/1.1 404 Not Found\n"+
                "Content-Length: ${content.length}\n" +
                "Content-Type: text/html\n" +
                "\n" +
                content
    }
}
