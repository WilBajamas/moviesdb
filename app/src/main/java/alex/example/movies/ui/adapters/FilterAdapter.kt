package alex.example.movies.ui.adapters

import alex.example.movies.databinding.FilterBinding
import alex.example.movies.domain.ShowFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(private val items: List<ShowFilter>) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val binding = FilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title
    }

    inner class FilterViewHolder(val binding: FilterBinding) : RecyclerView.ViewHolder(binding.root)
}