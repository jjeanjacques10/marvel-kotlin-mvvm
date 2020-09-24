package br.com.fiap.nacmarvel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fiap.nacmarvel.characters.Character

@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao() : CharacterDAO
}
