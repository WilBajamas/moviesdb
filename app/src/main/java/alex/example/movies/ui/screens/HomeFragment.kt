package alex.example.movies.ui.screens

import alex.example.movies.ui.viewmodels.HomeFragmentViewModel
import alex.example.movies.databinding.FragmentHomeBinding
import alex.example.movies.utils.BaseFragment
import android.os.Bundle
import android.view.View

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(
    FragmentHomeBinding::inflate,
    HomeFragmentViewModel::class.java
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}