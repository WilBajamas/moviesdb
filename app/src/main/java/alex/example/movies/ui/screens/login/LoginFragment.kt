package alex.example.movies.ui.screens.login

import alex.example.movies.R
import android.os.Bundle
import android.view.View
import alex.example.movies.databinding.FragmentLoginBinding

import alex.example.movies.ui.viewmodels.login.LoginFragmentViewModel
import alex.example.movies.utils.BaseFragment
import androidx.navigation.fragment.findNavController

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginFragmentViewModel>(
    FragmentLoginBinding::inflate,
    LoginFragmentViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_newOnboardingFragment)
        }
    }

}