package alex.example.movies.ui.screens.maincontent

import alex.example.movies.R
import alex.example.movies.ui.viewmodels.maincontent.HomeFragmentViewModel
import alex.example.movies.databinding.FragmentHomeBinding
import alex.example.movies.domain.model.FilmType
import alex.example.movies.ui.adapters.TrendingFilmsAdapter
import alex.example.movies.ui.adapters.TrendingPeopleAdapter
import alex.example.movies.utils.*
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Random


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(
    FragmentHomeBinding::inflate, HomeFragmentViewModel::class.java
) {

    private lateinit var trendingMoviesAdapter: TrendingFilmsAdapter
    private lateinit var trendingTvShowsAdapter: TrendingFilmsAdapter
    private lateinit var trendingPeopleAdapter: TrendingPeopleAdapter
    private lateinit var offsetChangedListener: OnOffsetChangedListener


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()
        val random = Random()

        with(binding) {

            offsetChangedListener = OnOffsetChangedListener { _, verticalOffset ->
                swipeRefreshLayout.isEnabled = verticalOffset == 0
            }.apply {
                appbar.addOnOffsetChangedListener(this)
            }

            swipeRefreshLayout.setOnRefreshListener {
                with(viewModel.backdropData.value) {
                    this?.let {
                        collapsingIv.load("${Const.POSTER_PATH_BASE_URL}${it[random.nextInt(it.size)].file_path}") {
                            crossfade(true)
                        }

                        swipeRefreshLayout.isRefreshing = false
                    }

                }
            }

            val itemSpacingDecoration = ItemSpacingDecoration(24)
            trendingMoviesRv.addItemDecoration(itemSpacingDecoration)
            trendingTvshowsRv.addItemDecoration(itemSpacingDecoration)
            trendingPeopleRv.addItemDecoration(itemSpacingDecoration)

            viewLifecycleOwner.lifecycleScope.launch {

                viewModel.backdropData.observe(viewLifecycleOwner) {
                    if (!it.isNullOrEmpty()) {
                        // TODO: Create glide module for reusing
                        collapsingIv.load("${Const.POSTER_PATH_BASE_URL}${it[random.nextInt(it.size)].file_path}") {
                            crossfade(true)
                        }
                    } else {
                        collapsingIv.load(R.drawable.sample_placeholder) {
                            placeholder(R.drawable.list_placeholder_img)
                        }
                    }
                }

                launch {
                    viewModel.trendingTvShows.collectLatest {

                        loadSectionListView(
                            trendingTvshowsRv, tvshowsShimmer, it is Resource.Loading
                        )

                        if (it is Resource.Success) {
                            it.data?.let { tvShowsPageResult ->
                                trendingTvshowsRv.layoutManager = LinearLayoutManager(
                                    requireContext(), LinearLayoutManager.HORIZONTAL, false
                                )
                                trendingTvShowsAdapter =
                                    TrendingFilmsAdapter(tvShowsPageResult.results)
                                trendingTvshowsRv.adapter = trendingTvShowsAdapter

                                trendingTvShowsAdapter.setOnClickListener(object :
                                    FilmItemClickListener {
                                    override fun filmItemClick(id: Int, filmType: FilmType) {
                                        val bundle =
                                            bundleOf("id" to id, "filmType" to filmType.name)
                                        requireParentFragment().requireParentFragment()
                                            .findNavController().navigate(
                                                R.id.action_mainContentFragment_to_filmDetailsFragment,
                                                bundle
                                            )
                                    }
                                })
                            }
                        }
                    }
                }

                launch {
                    viewModel.trendingMovies.collectLatest {

                        loadSectionListView(trendingMoviesRv, moviesShimmer, it is Resource.Loading)

                        if (it is Resource.Success) {
                            it.data?.let { moviePageResult ->
                                trendingMoviesRv.layoutManager = LinearLayoutManager(
                                    requireContext(), LinearLayoutManager.HORIZONTAL, false
                                )
                                trendingMoviesAdapter =
                                    TrendingFilmsAdapter(moviePageResult.results)
                                trendingMoviesRv.adapter = trendingMoviesAdapter
                                // TODO: Improve on click implementation
                                trendingMoviesAdapter.setOnClickListener(object :
                                    FilmItemClickListener {
                                    override fun filmItemClick(id: Int, filmType: FilmType) {
                                        val bundle =
                                            bundleOf("id" to id, "filmType" to filmType.name)
                                        requireParentFragment().requireParentFragment()
                                            .findNavController().navigate(
                                                R.id.action_mainContentFragment_to_filmDetailsFragment,
                                                bundle
                                            )
                                    }
                                })
                            }
                        }
                    }
                }

                launch {
                    viewModel.trendingPeople.collectLatest {

                        loadSectionListView(trendingPeopleRv, peopleShimmer, it is Resource.Loading)

                        if (it is Resource.Success) {
                            it.data?.let { peopleResponse ->
                                trendingPeopleRv.layoutManager = LinearLayoutManager(
                                    requireContext(), LinearLayoutManager.HORIZONTAL, false
                                )
                                trendingPeopleAdapter =
                                    TrendingPeopleAdapter(peopleResponse.results)
                                trendingPeopleRv.adapter = trendingPeopleAdapter

                                trendingPeopleAdapter.setOnClickListener(object :
                                    PeopleItemClickListener {
                                    override fun peopleItemClick(id: Int) {
                                        val bundle = bundleOf("id" to id)
                                        requireParentFragment().requireParentFragment()
                                            .findNavController().navigate(
                                                R.id.action_mainContentFragment_to_peopleDetailFragment,
                                                bundle
                                            )
                                    }
                                })
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadSectionListView(
        recyclerView: RecyclerView, shimmerLayout: ShimmerFrameLayout, showShimmer: Boolean
    ) {
        shimmerLayout.isVisible = showShimmer
        recyclerView.isVisible = !showShimmer
        shimmerLayout.apply {
            if (showShimmer) startShimmer() else stopShimmer()
        }
    }

    override fun onPause() {
        super.onPause()
        binding.appbar.removeOnOffsetChangedListener(offsetChangedListener)
    }

}