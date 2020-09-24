package br.com.fiap.nacmarvel.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.nacmarvel.database.CharacterDAO
import br.com.fiap.nacmarvel.service.MarvelService
import br.com.fiap.nacmarvel.service.PUBLIC_API_KEY
import br.com.fiap.nacmarvel.service.getApiHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CharactersViewModel(
    private val service: MarvelService,
    private val characterDAO: CharacterDAO
) : ViewModel() {

    val characterLiveDate = MutableLiveData<List<Character>>()

    val error = MutableLiveData<Boolean>()

    fun getCharacters() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                //Busca no Banco de dados Local
                val cacheData = characterDAO.selectAll()
                characterLiveDate.postValue(cacheData)

                //Busca na API
                val ts = Date().time.toString()
                val hash = getApiHash(ts)

                val characterDataWrapper =
                    service.getCharacters(PUBLIC_API_KEY, ts, hash, "-modified")

                val result: List<Character>? = characterDataWrapper.data?.results
                characterLiveDate.postValue(characterDataWrapper.data?.results)

                //Atualizar banco local
                /*
                result?.let {
                    characterDAO.deleteAll()
                    characterDAO.insert(*result.toTypedArray())
                }*/
            } catch (t: Throwable) {
                error.postValue(true)
            }
        }


    }

    fun getCharacterById(idCharacter: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            val ts = Date().time.toString()
            val hash = getApiHash(ts)

            val characterDataWrapper =
                service.getCharacterById(idCharacter, PUBLIC_API_KEY, ts, hash)

            characterLiveDate.postValue(characterDataWrapper.data?.results)
        }
    }
}