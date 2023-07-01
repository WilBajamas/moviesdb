package alex.example.movies.ui.screens.people

import alex.example.movies.R
import android.os.Bundle
import android.view.View
import alex.example.movies.databinding.FragmentPeopleDetailBinding
import alex.example.movies.ui.adapters.PeopleDetailFilmCreditsAdapter
import alex.example.movies.ui.viewmodels.people.PeopleDetailFragmentViewModel
import alex.example.movies.utils.BaseFragment
import alex.example.movies.utils.Const
import alex.example.movies.utils.ItemSpacingDecoration
import alex.example.movies.utils.Resource
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
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

                launch {
                    viewModel.peopleDetailStateFlow.collectLatest { resource ->
                        when (resource) {
                            is Resource.Success -> {
                                val details = resource.data
                                details?.let {
                                    peopleIv.load("${Const.POSTER_PATH_BASE_URL}${details.profile_path}") {
                                        placeholder(R.drawable.list_placeholder_img)
                                    }
                                    peopleNameTv.text = it.name
                                    birthdayTv.text = it.birthday
                                    knownForTv.text = it.known_for_department
                                    placeOfBirthTv.text = it.place_of_birth
                                    genderTv.text = when (it.gender) {
                                        1 -> getString(R.string.female)
                                        2 -> getString(R.string.male)
                                        3 -> getString(R.string.non_binary)
                                        else -> "-"
                                    }
                                    biographyTv.text = it.biography
                                }
                            }
                            is Resource.Error -> {

                            }
                            is Resource.Loading -> {

                            }
                        }
                    }
                }

                launch {
                    viewModel.peopleMovieCreditsStateFlow.collectLatest { resource ->
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
                    }
                }
            }
        }
    }

}
