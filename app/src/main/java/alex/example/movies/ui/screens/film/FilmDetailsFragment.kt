package alex.example.movies.ui.screens.film

import alex.example.movies.R
import android.os.Bundle
import android.view.View
import alex.example.movies.databinding.FragmentFilmDetailsBinding
import alex.example.movies.domain.model.Languages
import alex.example.movies.ui.adapters.FilmDetailsCastAdapter
import alex.example.movies.ui.viewmodels.film.FilmDetailsFragmentViewModel
import alex.example.movies.utils.BaseFragment
import alex.example.movies.utils.Const
import alex.example.movies.utils.Resource
import androidx.lifecycle.lifecycleScope
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import alex.example.movies.ui.extension.toMoneyValue
import alex.example.movies.utils.ItemSpacingDecoration
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout

@AndroidEntryPoint
class FilmDetailsFragment : BaseFragment<FragmentFilmDetailsBinding, FilmDetailsFragmentViewModel>(
    FragmentFilmDetailsBinding::inflate, FilmDetailsFragmentViewModel::class.java
) {

    private lateinit var castAdapter: FilmDetailsCastAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val arguments = requireArguments()
            val filmId = arguments.getInt(Const.DETAIL_ARGUMENTS_ID_TAG)
            val filmType = arguments.getString(Const.DETAIL_ARGUMENTS_FILM_TYPE_TAG)

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            headerErrorView.retryBtn.setOnClickListener {
                retryDetails(filmId, filmType!!)
            }

            infoErrorView.retryBtn.setOnClickListener {
                retryDetails(filmId, filmType!!)
            }

            rvErrorView.retryBtn.setOnClickListener {
                retryCredits(filmId, filmType!!)
            }

            toolbar.menu.findItem(R.id.action_favourite).setOnMenuItemClickListener {
                favouriteClick(filmType!!)
                true
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.fetchFilmDetails(
                    filmId, filmType!!
                )

                viewModel.fetchFilmCredits(
                    filmId, filmType
                )

                launch {
                    val itemSpacingDecoration = ItemSpacingDecoration(24)
                    castRv.addItemDecoration(itemSpacingDecoration)
                    viewModel.filmCreditsStateFlow.collectLatest {
                        loadRVContent(it is Resource.Loading)
                        showErrorCredits(it is Resource.Error)

                        if (it is Resource.Success) {
                            castRv.layoutManager = LinearLayoutManager(
                                requireContext(), LinearLayoutManager.HORIZONTAL, false
                            )
                            castAdapter = FilmDetailsCastAdapter(it.data!!.cast)
                            castRv.adapter = castAdapter
                        }
                    }
                }

                launch {
                    viewModel.filmDetailsStateFlow.collectLatest { resource ->
                        loadShimmer(
                            headerView, headerShimmerView, resource is Resource.Loading
                        )
                        showErrorHeaderContent(resource is Resource.Error)
                        showErrorDetailContent(resource is Resource.Error)
                        loadDetailContent(resource is Resource.Loading)

                        if (resource is Resource.Success) {

                            val details = resource.data
                            details?.let {
                                filmIv.load("${Const.POSTER_PATH_BASE_URL}${details.poster_path}") {
                                    placeholder(R.drawable.list_placeholder_img)
                                }
                                filmBackdropIv.load("${Const.POSTER_PATH_BASE_URL}${details.backdrop_path}") {
                                    crossfade(true)
                                }
                                filmTitleTv.text = it.title ?: it.name
                                releaseDateTv.text = it.release_date ?: it.first_air_date
                                filmGenresTv.text =
                                    it.genres.joinToString(", ") { genre -> genre.name }
                                ratingView.progressBar.progress = (it.vote_average * 10).toInt()
                                ratingView.textView.text = getString(
                                    R.string.percentage, ((it.vote_average * 10).toInt()).toString()
                                )
                                taglineTv.text = it.tagline
                                overviewTv.text = it.overview
                                statusTextTv.text = it.status
                                languageTextTv.text = Languages.values()
                                    .find { language -> language.iso639Id == it.original_language }?.displayName?.let { string ->
                                        getString(
                                            string
                                        )
                                    }

                                if (it.budget != null && it.type == null) {
                                    budgetTypeTv.text = getString(R.string.budget)
                                    budgetTypeTextTv.text = it.budget.toMoneyValue("$")
                                } else {
                                    budgetTypeTv.text = getString(R.string.type)
                                    budgetTypeTextTv.text = it.type
                                }

                                if (it.networks != null && it.revenue == null) {
                                    networkRevenueTv.text = getString(R.string.network)
                                    networkRevenueTextTv.text =
                                        if (it.networks.isNotEmpty()) it.networks[0].name else "-"
                                } else {
                                    networkRevenueTv.text = getString(R.string.revenue)
                                    networkRevenueTextTv.text = it.revenue.toMoneyValue("$")
                                }
                            }
                        }
                    }
                }

                launch {
                    viewModel.filmFavouriteStatusStateFlow.collectLatest {
                        when (it) {
                            true -> toolbar.menu.findItem(R.id.action_favourite)
                                .setIcon(R.drawable.ic_favourite_filled)

                            false -> toolbar.menu.findItem(R.id.action_favourite)
                                .setIcon(R.drawable.ic_favourite)

                        }
                    }
                }

                launch {
                    viewModel.filmFavouriteStatusSharedFlow.collectLatest {
                        when (it) {
                            true -> showFavouriteStatusToast(R.string.added_to_favourites)
                            false -> showFavouriteStatusToast(R.string.removed_from_favourites)
                        }
                    }
                }

            }

        }
    }

    private fun favouriteClick(filmType: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.filmDetailsStateFlow.collectLatest { resource ->
                if (resource.data == null || resource is Resource.Loading) {
                    showFavouriteStatusToast(R.string.favourite_error)
                } else {
                    viewModel.favouriteFilm(resource.data, filmType)
                }
            }
        }
    }

    private fun loadShimmer(
        view: View, shimmerLayout: ShimmerFrameLayout, showShimmer: Boolean
    ) {
        when (showShimmer) {
            true -> view.visibility = View.INVISIBLE
            false -> view.visibility = View.VISIBLE
        }
        shimmerLayout.isVisible = showShimmer
        shimmerLayout.apply {
            if (showShimmer) startShimmer() else stopShimmer()
        }
    }

    private fun loadDetailContent(load: Boolean) {
        with(binding) {
            infoView.isVisible = !load
            infoProgressBar.isVisible = load
        }
    }

    private fun loadRVContent(load: Boolean) {
        with(binding) {
            castRv.isVisible = !load
            rvProgressBar.isVisible = load
        }
    }

    private fun showErrorDetailContent(show: Boolean) {
        with(binding) {
            infoView.isVisible = !show
            infoErrorView.root.isVisible = show
        }
    }

    private fun showErrorHeaderContent(show: Boolean) {
        with(binding) {
            filmBackdropIv.isVisible = !show
            filmIvView.isVisible = !show
            filmTitleTv.isVisible = !show
            releaseDateTv.isVisible = !show
            filmGenresTv.isVisible = !show
            userScoreTv.isVisible = !show
            ratingView.root.isVisible = !show
            taglineTv.isVisible = !show
            overviewTv.isVisible = !show
            headerErrorView.root.isVisible = show
        }
    }

    private fun showErrorCredits(show: Boolean) {
        with(binding) {
            castRv.isVisible = !show
            rvErrorView.root.isVisible = show
        }
    }

    private fun retryDetails(id: Int, filmType: String) = viewModel.fetchFilmDetails(id, filmType)

    private fun retryCredits(id: Int, filmType: String) = viewModel.fetchFilmCredits(id, filmType)

    private fun showFavouriteStatusToast(stringResource: Int) {
        Toast.makeText(requireContext(), getString(stringResource), Toast.LENGTH_SHORT).show()
    }
}
