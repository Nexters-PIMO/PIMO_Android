package com.nexters.pimo.data.provider

interface MutableProvider<T> : Provider<T> {
    override var value: T
}
