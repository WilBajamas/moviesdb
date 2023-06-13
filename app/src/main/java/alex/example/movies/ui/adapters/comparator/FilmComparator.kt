package alex.example.movies.ui.adapters.comparator

import alex.example.movies.data.model.Film
import androidx.recyclerview.widget.DiffUtil

object FilmComparator : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}