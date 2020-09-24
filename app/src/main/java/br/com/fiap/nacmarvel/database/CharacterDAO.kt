package br.com.fiap.nacmarvel.database

import androidx.room.*
import br.com.fiap.nacmarvel.characters.Character

@Dao
interface CharacterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg character: Character)

    @Query("SELECT * FROM Character")
    suspend fun selectAll() : List<Character>

    @Query("DELETE FROM Character")
    suspend fun deleteAll()
}