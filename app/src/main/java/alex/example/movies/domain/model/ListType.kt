package alex.example.movies.domain.model

import alex.example.movies.R

enum class ListType(val displayName: Int, val sortByListType: String) {
    MOST_POPULAR_DESC(R.string.popularity_descending, "popularity.desc"),
    MOST_POPULAR_ASC(R.string.popularity_ascending, "popularity.asc"),
    TOP_RATED_DESC(R.string.rating_descending, "vote_average.desc"),
    TOP_RATED_ASC(R.string.rating_ascending, "vote_average.asc"),
    RELEASE_DATE_DESC(R.string.release_date_descending, "primary_release_date.desc"),
    RELEASE_DATE_ASC(R.string.release_date_ascending, "primary_release_date.asc")
}

enum class Genres(val id: Int, val nameResource: Int) {
    ACTION(28, R.string.action),
    ADVENTURE(12, R.string.adventure),
    ANIMATION(16, R.string.animation),
    COMEDY(35, R.string.comedy),
    CRIME(80, R.string.crime),
    DOCUMENTARY(99, R.string.documentary),
    DRAMA(18, R.string.drama),
    FANTASY(14, R.string.fantasy),
    HISTORY(36, R.string.history),
    HORROR(27, R.string.horror),
    MUSIC(10402, R.string.music),
    MYSTERY(9648, R.string.mystery),
    ROMANCE(10749, R.string.romance),
    SCIENCE_FICTION(878, R.string.science_fiction),
    TV_(10770, R.string.tv_movie),
    THRILLER(53, R.string.thriller),
    WAR(10752, R.string.war),
    WESTERN(37, R.string.western),
    ACTION_ADVENTURE(10759, R.string.action_adventure),
    FAMILY(10751, R.string.family),
    KIDS(10762, R.string.kids),
    REALITY(10764, R.string.reality),
    SCI_FI_FANTASY(10765, R.string.sci_fi_fantasy),
    SOAP(10766, R.string.soap),
    TALK(10767, R.string.talk),
    WAR_POLITICS(10768, R.string.talk),
}


enum class Languages(val displayName: Int, val iso639Id: String) {
    FRENCH(R.string.french, "fr"),
    ENGLISH(R.string.english, "en"),
    MANDARIN(R.string.mandarin, "zh"),
    SPANISH(R.string.spanish, "es"),
    JAPANESE(R.string.japanese, "ja"),
    KOREAN(R.string.korean, "ko"),
}

enum class FilmType() {
    MOVIE,
    TV_SHOW
}
