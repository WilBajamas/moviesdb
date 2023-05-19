package alex.example.movies.ui.model

import alex.example.movies.domain.model.ListType

data class FilterRequest(
    val sortBy: ListType,
    val genres: List<Int>?,
    val languageId: String?,
    val userScoreMin: Float,
    val userScoreMax: Float
)
