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
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

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

            viewLifecycleOwner.lifecycleScope.launch {
                // TODO: Improve implementation
                viewModel.fetchFilmDetails(
                    filmId, filmType!!
                )

                launch {
                    val itemSpacingDecoration = ItemSpacingDecoration(24)
                    castRv.addItemDecoration(itemSpacingDecoration)
                    viewModel.filmCreditsStateFlow.collectLatest {
                        when (it) {
                            is Resource.Success -> {
                                castRv.layoutManager = LinearLayoutManager(
                                    requireContext(), LinearLayoutManager.HORIZONTAL, false
                                )
                                castAdapter = FilmDetailsCastAdapter(it.data!!.cast)
                                castRv.adapter = castAdapter
                            }
                            is Resource.Error -> {

                            }
                            is Resource.Loading -> {

                            }
                        }
                    }
                }

                launch {
                    viewModel.filmDetailsStateFlow.collectLatest { resource ->
                        when (resource) {
                            is Resource.Success -> {
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
                                        R.string.percentage,
                                        ((it.vote_average * 10).toInt()).toString()
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
                            is Resource.Error -> Log.e("Film Detail Error: ", resource.message!!)
                            is Resource.Loading -> {

                            }
                        }
                    }
                }

            }

        }
    }

}