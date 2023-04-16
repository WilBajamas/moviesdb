package alex.example.movies.ui.components

import alex.example.movies.R
import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat

class DefaultEdittext(context: Context, attrs: AttributeSet): androidx.appcompat.widget.AppCompatEditText(context, attrs) {
    private var background = ContextCompat.getDrawable(context, R.drawable.background_gradient)

}