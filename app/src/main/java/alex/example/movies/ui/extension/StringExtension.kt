package alex.example.movies.ui.extension

import java.text.SimpleDateFormat
import java.util.*

fun String?.toDefaultBlank(): String {
    if (this.isNullOrEmpty()) return "-"
    return this
}

fun String?.convertDateString(): String {
    if (this.isNullOrEmpty()) return "-"
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())

    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}