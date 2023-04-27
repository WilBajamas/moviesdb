package alex.example.movies.ui.screens.login

import alex.example.movies.R
import android.os.Bundle
import android.view.View
import alex.example.movies.databinding.FragmentLoginBinding

import alex.example.movies.ui.viewmodels.login.LoginFragmentViewModel
import alex.example.movies.utils.BaseFragment
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginFragmentViewModel>(
    FragmentLoginBinding::inflate, LoginFragmentViewModel::class.java
) {

    private var shortAnimationDuration: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            emailInput.doOnTextChanged { text, _, _, _ ->
                viewModel.email = text.toString().trim()
                if (viewModel.checkEmailInputValid()) disableFieldError(emailAddressTextField)
            }

            passwordInput.doOnTextChanged { text, _, _, _ ->
                viewModel.password = text.toString()
                if (viewModel.checkPasswordInputValid()) disableFieldError(passwordTextField)
            }

            loginBtn.setOnClickListener {

                if (viewModel.checkPasswordInputValid() && viewModel.checkEmailInputValid()) {
//                    viewModel.login()
//                    findNavController().navigate(R.id.action_loginFragment_to_newOnboardingFragment)
                    fadeInLoading()
                }

                if (!viewModel.checkEmailInputValid()) emailAddressTextField.error =
                    getString(R.string.email_format_error)

                if (!viewModel.checkPasswordInputValid()) passwordTextField.error =
                    getString(R.string.password_too_short)
            }

        }
    }

    private fun disableFieldError(inputLayout: TextInputLayout) {
        inputLayout.isErrorEnabled = false
        inputLayout.error = null
    }

    private fun disableTextFields() {
        binding.passwordTextField.isEnabled = false
        binding.emailAddressTextField.isEnabled = false
    }

    private fun enableTextFields() {
        binding.passwordTextField.isEnabled = false
        binding.emailAddressTextField.isEnabled = false
    }

    private fun fadeInLoading() {
        disableTextFields()
        binding.progressIndicator.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }
        binding.loginBtn.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.loginBtn.visibility = View.GONE
                }
            })
    }

    private fun fadeInButton() {
        enableTextFields()
        binding.loginBtn.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }
        binding.progressIndicator.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.progressIndicator.visibility = View.GONE
                }
            })
    }


}
