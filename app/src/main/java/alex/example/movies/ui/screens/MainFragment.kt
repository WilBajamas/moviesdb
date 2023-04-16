package alex.example.movies.ui.screens

import alex.example.movies.ui.viewmodels.MainFragmentViewModel
import alex.example.movies.R
import alex.example.movies.databinding.FragmentMainBinding
import alex.example.movies.utils.BaseFragment
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>(
    FragmentMainBinding::inflate,
    MainFragmentViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()

        lifecycleScope.launch {
            viewModel.loginState.collect {
                when (it) {
                    true -> findNavController().navigate(R.id.action_mainFragment_to_mainContentFragment)
                    false -> findNavController().navigate(R.id.action_mainFragment_to_onboarding_nav)
                }
            }
        }
    }

}