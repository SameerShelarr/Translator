package com.sameershelar.translator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform