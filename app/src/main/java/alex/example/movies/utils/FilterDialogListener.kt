package alex.example.movies.utils

import alex.example.movies.ui.model.FilterRequest

interface FilterDialogListener {
    fun onFilterCallback(request: FilterRequest)
}