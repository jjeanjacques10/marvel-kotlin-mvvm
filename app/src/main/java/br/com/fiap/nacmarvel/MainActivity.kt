package br.com.fiap.nacmarvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.nacmarvel.characters.CharactersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private val characterAdapter = CharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this,1)
        recyclerView.adapter = characterAdapter

        viewModel.getCharacters()

        viewModel.characterLiveDate.observe(this, Observer {
            characterAdapter.updateItems(it.map { character ->
                character.copy(onClick = ::openDetails )
            })
        })

    }

    private fun openDetails(idCharacter: Int?){
        val intent = Intent(this, DetailsActivity::class.java)

        intent.putExtra("ID_CHARACTER", idCharacter)

        startActivity(intent)
    }



}