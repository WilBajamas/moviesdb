package alex.example.movies.ui.screens.filter

import alex.example.movies.R
import alex.example.movies.databinding.FragmentFilterBinding
import alex.example.movies.domain.model.Genres
import alex.example.movies.domain.model.Languages
import alex.example.movies.domain.model.ListType
import alex.example.movies.ui.model.FilterRequest
import alex.example.movies.ui.viewmodels.filter.FilterFragmentViewModel
import alex.example.movies.utils.FilterDialogListener
import android.app.Dialog
import android.content.Context
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
    private val viewModel: FilterFragmentViewModel by viewModels()
    private var filterDialogListener: FilterDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            filterDialogListener = parentFragment?.context as FilterDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement MyDialogListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                genresChipGroup.addView(chip)
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
                filterDialogListener?.onFilterCallback(
                    FilterRequest(
                        viewModel.listType.value,
                        viewModel.genres.value,
                        viewModel.language.value?.iso639Id,
                        viewModel.userScoreMin.value!!,
                        viewModel.userScoreMax.value!!
                    )
                )
            }
        }
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
