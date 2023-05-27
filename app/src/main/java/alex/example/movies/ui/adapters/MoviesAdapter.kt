package alex.example.movies.ui.adapters

import alex.example.movies.R
import alex.example.movies.databinding.ShowItemBinding
import alex.example.movies.data.model.Movie
import alex.example.movies.utils.Const
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(diffCallback: DiffUtil.ItemCallback<Movie>) :
    PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.binding.titleTv.text = item.title
            holder.binding.dateTv.text = item.release_date
            holder.binding.ratingView.progressBar.progress = (item.vote_average * 10).toInt()
            holder.binding.ratingView.textView.text =
                holder.binding.root.context.getString(
                    R.string.percentage,
                    ((item.vote_average * 10).toInt()).toString()
                )
            Glide.with(holder.binding.root).load("${Const.POSTER_PATH_BASE_URL}${item.poster_path}")
                .placeholder(R.drawable.list_placeholder_img).into(holder.binding.showImg)
        }
    }

    inner class MoviesViewHolder(val binding: ShowItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}

object UserComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}