package cat.kiwi.simple.context.logger

val enable_debug = false
object Logger {
}

fun Logger.info(obj: Any?) {
    println("[INFO] $obj")
}

fun Logger.warn(obj: Any?) {
    if (obj is Exception && enable_debug) {
        print("[WARN] ")
        obj.printStackTrace()
    } else {
        println("[WARN] $obj")
    }
}

fun Logger.debug(obj: Any?) {
    if (enable_debug) {
        println("[DEBU] $obj")
    }
}