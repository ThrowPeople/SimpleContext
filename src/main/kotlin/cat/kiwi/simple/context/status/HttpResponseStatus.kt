package cat.kiwi.simple.context.status

object HttpResponseStatus {
    val OK_200 = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n"
    val NOT_FOUND_404 = "HTTP/1.1 404 Not Found\r\n" + "Content-Type: text/html\r\n" + "\r\n"
    val INTERNAL_SERVER_ERROR_500 = "HTTP/1.1 500 Internal Server Error\r\n" + "Content-Type: text/html\r\n" + "\r\n"
    val BAD_REQUEST_400 = "HTTP/1.1 400 Bad Request\r\n" + "Content-Type: text/html\r\n" + "\r\n"
    val FORBIDDEN_403 = "HTTP/1.1 403 Forbidden\r\n" + "Content-Type: text/html\r\n" + "\r\n"
    val METHOD_NOT_ALLOWED_405 = "HTTP/1.1 405 Method Not Allowed\r\n" + "Content-Type: text/html\r\n" + "\r\n"
    val NOT_IMPLEMENTED_501 = "HTTP/1.1 501 Not Implemented\r\n" + "Content-Type: text/html\r\n" + "\r\n"
    val HTTP_VERSION_NOT_SUPPORTED_505 = "HTTP/1.1 505 HTTP Version Not Supported\r\n" + "Content-Type: text/html\r\n" + "\r\n"
}