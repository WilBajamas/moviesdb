package alex.example.movies.ui.adapters.pagingloadstate

import alex.example.movies.databinding.PagingLoadStateBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class PagingLoadingStateAdapter(val retry: () -> Unit) :
    LoadStateAdapter<PagingLoadingStateAdapter.PagingLoadingStateViewHolder>() {

    override fun onBindViewHolder(holder: PagingLoadingStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup, loadState: LoadState
    ): PagingLoadingStateViewHolder {
        val binding: PagingLoadStateBinding =
            PagingLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PagingLoadingStateViewHolder(binding)
    }

    inner class PagingLoadingStateViewHolder(
        private val binding: PagingLoadStateBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.loadErrorLayout.isVisible = loadState is LoadState.Error
            binding.errorView.retryBtn.setOnClickListener { retry() }
        }
    }
}
