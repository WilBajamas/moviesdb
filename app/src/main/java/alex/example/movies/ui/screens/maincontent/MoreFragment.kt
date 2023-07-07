package alex.example.movies.ui.screens.maincontent

import alex.example.movies.R
import alex.example.movies.ui.viewmodels.maincontent.MoreFragmentViewModel
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreFragment : PreferenceFragmentCompat() {

    private val viewModel: MoreFragmentViewModel by viewModels()


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val likedFilmsPreference = findPreference<Preference>(getString(R.string.favourite_films))
        val logoutPreference = findPreference<Preference>(getString(R.string.logout))

        val logoutDialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.logout))
            .setMessage(resources.getString(R.string.logout_description))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.yes)) { dialog, _ ->
                logout()
                dialog.dismiss()
            }

        likedFilmsPreference?.setOnPreferenceClickListener {
            true
        }

        logoutPreference?.setOnPreferenceClickListener {
            logoutDialog.show()
            true
        }


    }

    private fun logout() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.logout().collectLatest {
                requireParentFragment().requireParentFragment().findNavController()
                    .navigate(R.id.action_mainContentFragment_to_mainFragment)
            }
        }
    }
}
