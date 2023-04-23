package alex.example.movies.ui.screens

import alex.example.movies.ui.viewmodels.MainFragmentViewModel
import alex.example.movies.R
import alex.example.movies.databinding.FragmentMainBinding
import alex.example.movies.data.state.AuthState
import alex.example.movies.utils.BaseFragment
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>(
    FragmentMainBinding::inflate, MainFragmentViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authState.collect { state ->
                when (state) {
                    is AuthState.ApiKeyBlank -> binding.authErrorTv.isVisible = true
                    is AuthState.Loading -> binding.progressBar.isVisible = true
                    // TODO: Remove from back stack
                    is AuthState.SessionAvailable -> findNavController().navigate(R.id.action_mainFragment_to_mainContentFragment)
                    is AuthState.SessionNull -> findNavController().navigate(R.id.action_mainFragment_to_onboarding_nav)
                    else -> {}
                }
            }
        }
    }

}