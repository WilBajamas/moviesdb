package alex.example.movies.utils

import alex.example.movies.domain.model.FilmType

interface FilmItemClickListener {
    fun filmItemClick(id: Int, filmType: FilmType)
}