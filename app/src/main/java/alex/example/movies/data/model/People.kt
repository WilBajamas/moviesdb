package alex.example.movies.data.model

data class People(
    val id: Int,
    val name: String,
    val known_for_department: String,
    val gender: Int,
    val profile_path: String,
    val character: String,
    val job: String
)

data class PeopleResponse(
    val page: Int,
    val results: List<People>,
    val total_pages: Int
)