package cat.kiwi.simple.context.template

object SimpleTemplate {
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
