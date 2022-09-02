package cat.kiwi.simple.context2.utils.ext

import java.io.BufferedReader

internal val Int.ByteSize: Int
    get() {
        return when (this) {
            in 0x00..0x7F -> 1
            in 0x80..0x7FF -> 2
            in 0x800..0xFFFF -> 3
            in 0x10000..0x10FFFF -> 4
            else -> 0
        }
    }
