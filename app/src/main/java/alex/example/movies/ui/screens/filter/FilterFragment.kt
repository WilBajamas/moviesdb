package alex.example.movies.ui.screens.filter

import alex.example.movies.R
import alex.example.movies.databinding.FragmentFilterBinding
import alex.example.movies.domain.model.Genres
import alex.example.movies.domain.model.Languages
import alex.example.movies.domain.model.ListType
import alex.example.movies.ui.viewmodels.maincontent.SharedMoviesFilterFragmentViewModel
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class FilterFragment : DialogFragment() {

    private lateinit var binding: FragmentFilterBinding
    private val viewModel: SharedMoviesFilterFragmentViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewOnInputChange()

        with(binding) {

            (sortByTv as? MaterialAutoCompleteTextView)?.setSimpleItems(
                viewModel.displayNames.map { getString(it) }.toTypedArray()
            )

            (languageTv as? MaterialAutoCompleteTextView)?.setSimpleItems(
                viewModel.displayLanguages.map { getString(it) }.toTypedArray()
            )

            for (genre in Genres.values()) {
                val chip = Chip(requireContext())
                chip.text = getString(genre.nameResource)
                chip.id = genre.id
                chip.isChecked = viewModel.genresData.value?.contains(genre.id) == true
                genresChipGroup.addView(chip)

                viewModel.chipMap[genre.id] = chip
            }

            closeBtn.setOnClickListener {
                this@FilterFragment.dismiss()
            }

            sortByTv.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                viewModel.setListType(ListType.values()[position])
            }

            genresChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
                viewModel.setGenres(checkedIds)
            }

            languageTv.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                viewModel.setLanguage(Languages.values()[position])
            }

            userScoreSlider.addOnChangeListener { slider, _, _ ->
                viewModel.setUserScoreMin(slider.values[0])
                viewModel.setUserScoreMax(slider.values[1])
            }

            filterBtn.setOnClickListener {
                // Initiate api call here
                this@FilterFragment.dismiss()
                viewModel.fetchMovies()
            }
        }
    }

    private fun setViewOnInputChange() {
        val listTypes = ListType.values()
        val typeIndex = listTypes.indexOf(viewModel.listTypeData.value)
        binding.sortByTv.setText(listTypes[typeIndex].displayName)

        val languages = Languages.values()
        val langIndex = languages.indexOf(viewModel.languageData.value)
        binding.languageTv.setText(languages[langIndex].displayName)

        binding.userScoreSlider.values =
            listOf(viewModel.userScoreMinData.value, viewModel.userScoreMaxData.value)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        return dialog
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialogTheme
    }
}
