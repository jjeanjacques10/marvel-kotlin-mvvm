package br.com.fiap.nacmarvel.characters

data class CharacterDataWrapper(
    val code: Int? = null,
    val status: String? = null,
    val data: CharacterDataContainer? = null
)

data class CharacterDataContainer(
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val count: Int? = null,
    val results: List<Character>? = null
)

data class ComicList (
    val available: Int? = null,
    val returned: Int? = null,
    val collectionURI: String? = null,
    val items: List<ComicSummary>? = null
)

data class ComicSummary (
    val resourceURI: String? = null,
    val name: String? = null
)

data class Image(
    val path: String? = null,
    val extension: String? = null
)