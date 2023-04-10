package alex.example.movies.ui.login

import android.os.Bundle
import android.view.View
import alex.example.movies.databinding.FragmentLoginBinding

import alex.example.movies.ui.viewmodels.login.LoginFragmentViewModel
import alex.example.movies.utils.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginFragmentViewModel>(
    FragmentLoginBinding::inflate,
    LoginFragmentViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}