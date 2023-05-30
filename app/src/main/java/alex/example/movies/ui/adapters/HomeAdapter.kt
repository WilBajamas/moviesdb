package alex.example.movies.ui.adapters

import alex.example.movies.databinding.HomeSectionListItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val items: List<String>) :
    RecyclerView.Adapter<HomeAdapter.HomeItemViewHolder>() {

    inner class HomeItemViewHolder(val binding: HomeSectionListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val binding =
            HomeSectionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.binding.tv.text = items[position]
    }

}
