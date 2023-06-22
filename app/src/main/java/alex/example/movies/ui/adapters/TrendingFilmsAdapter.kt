package alex.example.movies.ui.adapters

import alex.example.movies.R
import alex.example.movies.data.model.Film
import alex.example.movies.databinding.ShowItemBinding
import alex.example.movies.domain.model.FilmType
import alex.example.movies.utils.Const
import alex.example.movies.utils.FilmItemClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

class TrendingFilmsAdapter(
    private val items: List<Film>
) : RecyclerView.Adapter<TrendingFilmsAdapter.TrendingFilmsItemViewHolder>() {

    private var onClickListener: FilmItemClickListener? = null

    inner class TrendingFilmsItemViewHolder(
        val binding: ShowItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    fun setOnClickListener(onClickListener: FilmItemClickListener) {
        this.onClickListener = onClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingFilmsItemViewHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            (parent.measuredWidth * 0.45).toInt(), ViewGroup.LayoutParams.MATCH_PARENT
        )
        return TrendingFilmsItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: TrendingFilmsAdapter.TrendingFilmsItemViewHolder, position: Int
    ) {
        val item = items[position]
        with(holder.binding) {
            titleTv.text = item.title ?: item.name
            dateTv.text = item.release_date ?: item.first_air_date
            ratingView.progressBar.progress = (item.vote_average * 10).toInt()
            ratingView.textView.text = holder.binding.root.context.getString(
                R.string.percentage, ((item.vote_average * 10).toInt()).toString()
            )
            showImg.load("${Const.POSTER_PATH_BASE_URL}${item.poster_path}") {
                crossfade(true)
            }
            // TODO: Improve implementation, create domain mapper for film - include FilmType property

            val filmType = if (item.title != null) FilmType.MOVIE else FilmType.TV_SHOW
            root.setOnClickListener {
                onClickListener?.filmItemClick(item.id, filmType)
            }
        }
    }

}