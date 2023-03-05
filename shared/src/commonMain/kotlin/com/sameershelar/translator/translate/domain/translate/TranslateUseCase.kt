package com.sameershelar.translator.translate.domain.translate

import com.sameershelar.translator.core.domain.language.Language
import com.sameershelar.translator.core.domain.util.Resource
import com.sameershelar.translator.translate.domain.history.HistoryDataSource
import com.sameershelar.translator.translate.domain.history.HistoryItem

class TranslateUseCase(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource,
) {
    suspend fun execute(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language,
    ): Resource<String> {
        return try {
            val translatedText = client.translate(
                fromLanguage = fromLanguage,
                fromText = fromText,
                toLanguage = toLanguage,
            )
            historyDataSource.insertHistoryItem(
                item = HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translatedText,
                )
            )
            Resource.Success(translatedText)
        } catch (e: TranslateException) {
            e.printStackTrace()
            Resource.Error(throwable = e)
        }
    }
}