package alex.example.movies.data.mapper

import alex.example.movies.data.local.entities.FilmDetailEntity
import alex.example.movies.data.local.entities.FilmEntity
import alex.example.movies.data.model.Film
import alex.example.movies.data.model.FilmDetail

fun Film.mapToEntity(filmType: String): FilmEntity = FilmEntity(
    id = id,
    poster_path = poster_path,
    release_date = release_date,
    genre_ids = genre_ids,
    title = title,
    vote_average = vote_average,
    first_air_date = first_air_date,
    name = name,
    filmType = filmType
)

fun FilmDetail.mapToEntity(filmType: String) : FilmDetailEntity = FilmDetailEntity(
    id = id,
    backdrop_path = backdrop_path,
    budget = budget,
    original_language = original_language,
    title = title,
    name = name,
    overview = overview,
    poster_path = poster_path,
    release_date = release_date,
    first_air_date = first_air_date,
    revenue = revenue,
    status = status,
    tagline = tagline,
    type = type,
    vote_average = vote_average,
    networks = networks,
    created_by = created_by,
    genres = genres,
    filmType = filmType
)

fun FilmDetailEntity.mapToModel(): FilmDetail = FilmDetail(
    id = id,
    backdrop_path = backdrop_path,
    budget = budget,
    original_language = original_language,
    title = title,
    name = name,
    overview = overview,
    poster_path = poster_path,
    release_date = release_date,
    first_air_date = first_air_date,
    revenue = revenue,
    status = status,
    tagline = tagline,
    type = type,
    vote_average = vote_average,
    networks = networks,
    created_by = created_by,
    genres = genres,
)