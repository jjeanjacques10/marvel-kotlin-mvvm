package br.com.fiap.nacmarvel.characters

import androidx.room.*

@Entity
data class Character(
    @PrimaryKey()
    var  id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    @Ignore val thumbnail: Image? = null,
    @Ignore val comics: ComicList? = null,
    @Ignore val onClick: ((Int?) -> Unit)? = null
)