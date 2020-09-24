package br.com.fiap.nacmarvel

import android.app.Application
import android.content.Context
import androidx.room.Room
import br.com.fiap.nacmarvel.characters.CharactersViewModel
import br.com.fiap.nacmarvel.database.CharacterDAO
import br.com.fiap.nacmarvel.database.CharacterDatabase
import br.com.fiap.nacmarvel.service.MarvelService
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersApplication :Application(){

    val charactersModule = module {
        single{ provideRetrofit() }
        single { provideMarvelService(get()) }

        //Database
        single { provideCharacterDatabase(get()) }
        single { provideCharacterkDao(get()) }

        viewModel { CharactersViewModel(get(), get()) }
    }

    private fun provideCharacterkDao(database: CharacterDatabase): CharacterDAO {
        return database.characterDao()
    }

    private fun provideCharacterDatabase(context: Context): CharacterDatabase {
        return Room
            .databaseBuilder(context, CharacterDatabase::class.java,"characterdatabase")
            .build()
    }

    private fun provideMarvelService(retrofit: Retrofit): MarvelService {
        return retrofit.create(MarvelService::class.java)
    }

    private fun provideRetrofit(): Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CharactersApplication)
            modules(charactersModule)
        }
    }
}