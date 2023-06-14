package alex.example.movies.ui.adapters.comparator

import alex.example.movies.data.model.People
import androidx.recyclerview.widget.DiffUtil

object PeopleComparator : DiffUtil.ItemCallback<People>() {
    override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
        return oldItem == newItem
    }
}