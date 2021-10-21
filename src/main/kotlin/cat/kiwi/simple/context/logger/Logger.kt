package cat.kiwi.simple.context.logger

val enable_debug = false

object Logger

fun Logger.info(obj: Any?) {
    Thread {
        println("[INFO] $obj")
    }.start()
}

fun Logger.warn(obj: Any?) {
    Thread {
        if (obj is Exception && enable_debug) {
            print("[WARN] ")
            obj.printStackTrace()
        } else {
            println("[WARN] $obj")
        }
    }.start()

}

fun Logger.debug(obj: Any?) {
    Thread {
        if (enable_debug) {
            println("[DEBU] $obj")
        }
    }.start()
}