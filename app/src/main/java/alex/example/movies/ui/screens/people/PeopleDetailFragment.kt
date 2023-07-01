package alex.example.movies.ui.screens.people

import alex.example.movies.R
import android.os.Bundle
import android.view.View
import alex.example.movies.databinding.FragmentPeopleDetailBinding
import alex.example.movies.ui.adapters.PeopleDetailFilmCreditsAdapter
import alex.example.movies.ui.extension.toDefaultBlank
import alex.example.movies.ui.viewmodels.people.PeopleDetailFragmentViewModel
import alex.example.movies.utils.BaseFragment
import alex.example.movies.utils.Const
import alex.example.movies.utils.ItemSpacingDecoration
import alex.example.movies.utils.Resource
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleDetailFragment :
    BaseFragment<FragmentPeopleDetailBinding, PeopleDetailFragmentViewModel>(
        FragmentPeopleDetailBinding::inflate, PeopleDetailFragmentViewModel::class.java
    ) {

    private lateinit var filmCreditsAdapter: PeopleDetailFilmCreditsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            val arguments = requireArguments()
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.fetchDetails(arguments.getInt("id"))
                viewModel.fetchMoviesCredits(arguments.getInt("id"))

                loadShimmer(
                    headerView, headerShimmerView, true
                )


                launch {
                    viewModel.peopleDetailStateFlow.collectLatest { resource ->
                        loadShimmer(
                            headerView, headerShimmerView, resource is Resource.Loading
                        )
                        loadDetailContent(resource is Resource.Loading)

                        if (resource is Resource.Success) {
                            val details = resource.data
                            details?.let {
                                peopleIv.load("${Const.POSTER_PATH_BASE_URL}${details.profile_path}") {
                                    placeholder(R.drawable.list_placeholder_img)
                                }
                                peopleNameTv.text = it.name.toDefaultBlank()
                                birthdayTv.text = it.birthday.toDefaultBlank()
                                knownForTv.text = it.known_for_department.toDefaultBlank()
                                placeOfBirthTv.text = it.place_of_birth.toDefaultBlank()
                                genderTv.text = when (it.gender) {
                                    1 -> getString(R.string.female)
                                    2 -> getString(R.string.male)
                                    3 -> getString(R.string.non_binary)
                                    else -> "-"
                                }
                                biographyTv.text = it.biography.toDefaultBlank()
                            }
                        } else if (resource is Resource.Error) {

                        }
                    }
                }

                launch {
                    viewModel.peopleMovieCreditsStateFlow.collectLatest { resource ->
                        loadRVContent(resource is Resource.Loading)
                        if (resource is Resource.Success) {
                            resource.data?.cast?.let {
                                val itemSpacingDecoration = ItemSpacingDecoration(24)
                                knownForRv.addItemDecoration(itemSpacingDecoration)

                                knownForRv.layoutManager = LinearLayoutManager(
                                    requireContext(), LinearLayoutManager.HORIZONTAL, false
                                )
                                filmCreditsAdapter =
                                    PeopleDetailFilmCreditsAdapter(it)
                                knownForRv.adapter = filmCreditsAdapter
                            }
                        } else if (resource is Resource.Error) {

                        }
                    }
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
            biographyTitleTv.isVisible = !load
            biographyTv.isVisible = !load

            progressBar.isVisible = load
        }
    }

    private fun loadRVContent(load: Boolean) {
        with(binding) {
            knownForTitle.isVisible = !load
            knownForRv.isVisible = !load

            progressBar.isVisible = load
        }
    }

}
