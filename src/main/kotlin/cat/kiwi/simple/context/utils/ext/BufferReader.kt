package cat.kiwi.simple.context.utils.ext

import java.io.BufferedReader

fun BufferedReader.getBodyAsString(header: Map<String, String>): String {
    var contentLength = header["Content-Length"]?.toInt() ?: 0
    val body = StringBuilder()
    while (contentLength > 0) {
        val c = read()
        body.append(c.toChar())
        contentLength -= c.ByteSize
    }
    return body.toString()
}

fun BufferedReader.readHeaders(): Map<String, String> {
    val headers = mutableMapOf<String, String>()
    while (true) {
        val line = this.readLine()
        if (line == null || line.isEmpty()) {
            break
        }
        val (key, value) = line.split(":")
        headers[key] = value.trimStart().trimEnd()
    }
    println(headers)
    return headers
}