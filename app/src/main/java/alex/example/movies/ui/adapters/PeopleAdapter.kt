package alex.example.movies.ui.adapters

import alex.example.movies.data.model.People
import alex.example.movies.databinding.PeopleItemBinding
import alex.example.movies.utils.Const
import alex.example.movies.utils.PeopleItemClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load

class PeopleAdapter(diffCallback: DiffUtil.ItemCallback<People>): PagingDataAdapter<People, PeopleAdapter.PeopleViewHolder>(diffCallback) {

    private var onClickListener: PeopleItemClickListener? = null

    fun setOnClickListener(onClickListener: PeopleItemClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding = PeopleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            with(holder.binding) {
                nameTv.text = item.name
                knownForTv.text = item.known_for_department

                peopleIv.load("${Const.POSTER_PATH_BASE_URL}${item.profile_path}") {
                    crossfade(true)
                }

                root.setOnClickListener {
                    onClickListener?.peopleItemClick(item.id)
                }
            }
        }
    }


    inner class PeopleViewHolder(val binding: PeopleItemBinding): RecyclerView.ViewHolder(binding.root)
}