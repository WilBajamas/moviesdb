package alex.example.movies.ui.extension

import java.text.DecimalFormat

fun Long?.toMoneyValue(currency: String): String? {
    this?.let {
        val formatter = DecimalFormat("###,###,###")
        return "$currency${formatter.format(this)}"
    }
    return null
}