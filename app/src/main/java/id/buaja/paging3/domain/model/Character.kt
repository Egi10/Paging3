package id.buaja.paging3.domain.model

data class Character(
	val results: List<ResultsItem>,
	val info: Info
)

data class Info(
	val pages: Int?,
	val count: Int?
)

data class ResultsItem(
	val id: Int,
	val image: String,
	val gender: String,
	val name: String,
	val status: String
)

