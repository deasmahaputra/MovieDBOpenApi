package com.test.axiata.apps.network.response

data class DetailsMovieResponse(
    val originalLanguage: String? = null,
    val imdbId: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val backdropPath: String? = null,
    val revenue: Int? = null,
    val genres: List<GenresItem?>? = null,
    val popularity: Double? = null,
    val productionCountries: List<ProductionCountriesItem?>? = null,
    val id: Int? = null,
    val voteCount: Int? = null,
    val budget: Int? = null,
    val overview: String? = null,
    val originalTitle: String? = null,
    val runtime: Int? = null,
    val poster_path: String? = null,
    val spoken_languages: List<SpokenLanguagesItem?>? = null,
    val productionCompanies: List<ProductionCompaniesItem?>? = null,
    val releaseDate: String? = null,
    val vote_average: Double? = null,
    val belongsToCollection: Any? = null,
    val tagline: String? = null,
    val adult: Boolean? = null,
    val homepage: String? = null,
    val status: String? = null
)

data class ProductionCountriesItem(
    val iso31661: String? = null,
    val name: String? = null
)

data class GenresItem(
    val name: String? = null,
    val id: Int? = null
)

data class ProductionCompaniesItem(
    val logoPath: String? = null,
    val name: String? = null,
    val id: Int? = null,
    val originCountry: String? = null
)

data class SpokenLanguagesItem(
    val name: String? = null,
    val iso6391: String? = null
)

