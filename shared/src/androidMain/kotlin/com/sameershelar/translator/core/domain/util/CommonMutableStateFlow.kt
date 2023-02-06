package com.sameershelar.translator.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

actual open class CommonMutableStateFlow<T> actual constructor(flow: MutableStateFlow<T>) :
    MutableStateFlow<T> by flow