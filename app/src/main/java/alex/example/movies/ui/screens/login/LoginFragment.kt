package alex.example.movies.ui.screens.login

import alex.example.movies.R
import android.os.Bundle
import android.view.View
import alex.example.movies.databinding.FragmentLoginBinding

import alex.example.movies.ui.viewmodels.login.LoginFragmentViewModel
import alex.example.movies.utils.BaseFragment
import android.util.Patterns
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginFragmentViewModel>(
    FragmentLoginBinding::inflate, LoginFragmentViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            emailInput.addTextChangedListener {
                if (checkEmailInputValid()) disableFieldError(emailAddressTextField)
            }

            passwordInput.addTextChangedListener {
                if (checkPasswordInputValid()) disableFieldError(passwordTextField)
            }

            loginBtn.setOnClickListener {

                if (checkPasswordInputValid() && checkEmailInputValid()) {
                    findNavController().navigate(R.id.action_loginFragment_to_newOnboardingFragment)
                }

                if (!checkEmailInputValid()) emailAddressTextField.error =
                    getString(R.string.email_format_error)

                if (!checkPasswordInputValid()) passwordTextField.error =
                    getString(R.string.password_too_short)
            }

        }
    }

    private fun checkEmailInputValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(binding.emailInput.text.toString().trim()).matches()
    }

    private fun checkPasswordInputValid(): Boolean {
        return binding.passwordInput.text.toString().length > 7
    }

    private fun disableFieldError(inputLayout: TextInputLayout) {
        inputLayout.isErrorEnabled = false
        inputLayout.error = null
    }

}
