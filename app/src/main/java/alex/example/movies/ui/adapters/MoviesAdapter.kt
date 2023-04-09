package alex.example.movies.ui.adapters

import alex.example.movies.databinding.ShowItemBinding
import alex.example.movies.domain.Movies
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(private val items: List<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleTv.text = item.title
        holder.binding.ratingTv.text = item.rating
        holder.binding.dateTv.text = item.date
        Glide.with(holder.binding.root).load(item.image_url).into(holder.binding.showImg)
    }

    inner class MoviesViewHolder(val binding: ShowItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}