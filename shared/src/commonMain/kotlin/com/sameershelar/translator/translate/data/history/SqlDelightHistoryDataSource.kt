package com.sameershelar.translator.translate.data.history

import com.sameershelar.translator.core.domain.util.CommonFlow
import com.sameershelar.translator.core.domain.util.toCommonFlow
import com.sameershelar.translator.database.TranslateDatabase
import com.sameershelar.translator.translate.domain.history.HistoryDataSource
import com.sameershelar.translator.translate.domain.history.HistoryItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    db: TranslateDatabase
) : HistoryDataSource {

    private val queries = db.translateQueries

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries
            .getHistory()
            .asFlow()
            .mapToList()
            .map { historyEntity ->
                historyEntity.map { it.toHistoryItem() }
            }.toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}