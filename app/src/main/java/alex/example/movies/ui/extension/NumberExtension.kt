package alex.example.movies.ui.extension

import java.text.DecimalFormat

fun Long?.toMoneyValue(currency: String): String {

    this.takeIf { value -> value != 0L }?.let {
        val formatter = DecimalFormat("###,###,###")
        return "$currency${formatter.format(this)}"
    }

    return "-"
}