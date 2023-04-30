package alex.example.movies.ui.screens.login

import alex.example.movies.R
import android.os.Bundle
import android.view.View
import alex.example.movies.databinding.FragmentLoginBinding

import alex.example.movies.ui.viewmodels.login.LoginFragmentViewModel
import alex.example.movies.utils.BaseFragment
import alex.example.movies.utils.Resource
import alex.example.movies.utils.ViewAnimator
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginFragmentViewModel>(
    FragmentLoginBinding::inflate, LoginFragmentViewModel::class.java
) {

    private val viewAnimator by lazy { ViewAnimator() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            usernameInput.doOnTextChanged { text, _, _, _ ->
                viewModel.username = text.toString().trim()
                if (viewModel.checkEmailInputValid()) disableFieldError(usernameTextField)
            }

            passwordInput.doOnTextChanged { text, _, _, _ ->
                viewModel.password = text.toString()
                if (viewModel.checkPasswordInputValid()) disableFieldError(passwordTextField)
            }

            loginBtn.setOnClickListener {
                if (viewModel.checkPasswordInputValid() && viewModel.checkEmailInputValid()) {
                    viewModel.login()
                }

                if (!viewModel.checkEmailInputValid()) usernameTextField.error =
                    getString(R.string.username_empty)

                if (!viewModel.checkPasswordInputValid()) passwordTextField.error =
                    getString(R.string.password_too_short)
            }

            viewModel.loginResponse.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> findNavController().navigate(R.id.action_loginFragment_to_newOnboardingFragment)
                    is Resource.Error -> {
                        this.errorTv.text = it.message
                        this.errorTv.visibility = View.VISIBLE
                        viewAnimator.crossFadeView(binding.loginBtn, binding.progressIndicator) {
                            enableTextFields()
                        }
                    }
                    else -> {
                        viewAnimator.crossFadeView(binding.progressIndicator, binding.loginBtn) {
                            disableTextFields()
                        }
                    }
                }
            }
        }
    }

    private fun disableFieldError(inputLayout: TextInputLayout) {
        inputLayout.isErrorEnabled = false
        inputLayout.error = null
    }

    private fun disableTextFields() {
        binding.passwordTextField.isEnabled = false
        binding.usernameTextField.isEnabled = false
    }

    private fun enableTextFields() {
        binding.passwordTextField.isEnabled = true
        binding.usernameTextField.isEnabled = true
    }

}
