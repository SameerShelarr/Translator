package com.sameershelar.translator.core.domain.util

import kotlinx.coroutines.flow.StateFlow

expect open class CommonStateFlow<T>(flow: StateFlow<T>) : StateFlow<T>

fun <T> StateFlow<T>.toCommonStateFlow(flow: StateFlow<T>) = CommonStateFlow(flow)