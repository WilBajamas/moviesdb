package alex.example.movies.ui.screens.maincontent

import alex.example.movies.databinding.FragmentTVShowsBinding
import alex.example.movies.ui.viewmodels.maincontent.TVShowsFragmentViewModel
import alex.example.movies.utils.BaseFragment
import android.os.Bundle
import android.view.View

class TVShowsFragment : BaseFragment<FragmentTVShowsBinding, TVShowsFragmentViewModel>(FragmentTVShowsBinding::inflate, TVShowsFragmentViewModel::class.java) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

        }
    }

}