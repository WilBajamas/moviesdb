package alex.example.movies.ui.adapters

import alex.example.movies.R
import alex.example.movies.databinding.ShowItemBinding
import alex.example.movies.data.model.Film
import alex.example.movies.utils.Const
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load

class MoviesAdapter(diffCallback: DiffUtil.ItemCallback<Film>) :
    PagingDataAdapter<Film, MoviesAdapter.MoviesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            with(holder.binding) {
                titleTv.text = item.title
                dateTv.text = item.release_date
                ratingView.progressBar.progress = (item.vote_average * 10).toInt()
                ratingView.textView.text =
                    holder.binding.root.context.getString(
                        R.string.percentage,
                        ((item.vote_average * 10).toInt()).toString()
                    )
                showImg.load("${Const.POSTER_PATH_BASE_URL}${item.poster_path}") {
                    crossfade(true)
                }
            }

        }
    }

    inner class MoviesViewHolder(val binding: ShowItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}

object UserComparator : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}