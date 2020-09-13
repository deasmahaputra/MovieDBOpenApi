package com.test.axiata.apps.network.response

data class GenreResponse(
	val genres: MutableList<GenresItem>
){
	data class GenresItem(
		val name: String? = null,
		val id: Int? = null
	)
}
