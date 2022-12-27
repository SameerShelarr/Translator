package com.sameershelar.translator.translate.domain.translate

import com.sameershelar.translator.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}