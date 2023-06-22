package alex.example.movies.ui.adapters

import alex.example.movies.data.model.People
import alex.example.movies.databinding.PeopleItemBinding
import alex.example.movies.utils.Const
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

class FilmDetailsCastAdapter(private val items: List<People>): RecyclerView.Adapter<FilmDetailsCastAdapter.FilmDetailsCastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmDetailsCastViewHolder {
        val binding = PeopleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.layoutParams = ViewGroup.LayoutParams((parent.measuredWidth * 0.45).toInt(),
            ViewGroup.LayoutParams.MATCH_PARENT)
        return FilmDetailsCastViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FilmDetailsCastViewHolder, position: Int) {
        val item = items[position]
        with (holder.binding) {
            nameTv.text = item.name
            knownForTv.text = item.character
            peopleIv.load("${Const.POSTER_PATH_BASE_URL}${item.profile_path}") {
                crossfade(true)
            }
        }
    }

    inner class FilmDetailsCastViewHolder(val binding: PeopleItemBinding): RecyclerView.ViewHolder(binding.root)
}