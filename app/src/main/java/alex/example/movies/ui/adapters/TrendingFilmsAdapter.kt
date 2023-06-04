package alex.example.movies.ui.adapters

import alex.example.movies.R
import alex.example.movies.data.model.Film
import alex.example.movies.databinding.ShowItemBinding
import alex.example.movies.utils.Const
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

class TrendingFilmsAdapter(private val items: List<Film>) :
    RecyclerView.Adapter<TrendingFilmsAdapter.TrendingMoviesItemViewHolder>() {

    inner class TrendingMoviesItemViewHolder(val binding: ShowItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMoviesItemViewHolder {
        val binding =
            ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.layoutParams = ViewGroup.LayoutParams((parent.measuredWidth * 0.45).toInt(),ViewGroup.LayoutParams.MATCH_PARENT)
        return TrendingMoviesItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TrendingFilmsAdapter.TrendingMoviesItemViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            titleTv.text = item.title ?: item.name
            dateTv.text = item.release_date ?: item.first_air_date
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