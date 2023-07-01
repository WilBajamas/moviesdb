package alex.example.movies.ui.extension

fun String?.toDefaultBlank(): String {
    return this ?: "-"
}