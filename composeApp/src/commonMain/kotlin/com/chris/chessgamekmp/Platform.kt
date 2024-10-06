package com.chris.chessgamekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform