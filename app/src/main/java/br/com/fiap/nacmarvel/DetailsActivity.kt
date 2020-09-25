package br.com.fiap.nacmarvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import br.com.fiap.nacmarvel.characters.CharactersViewModel
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModel()


    private var idCharacter: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val textViewNome = findViewById<TextView>(R.id.textViewNome)
        val textViewDescricao = findViewById<TextView>(R.id.textViewDescricao)
        val textViewComics = findViewById<TextView>(R.id.textViewComics)
        val imageView = findViewById<ImageView>(R.id.imageView)

        idCharacter = intent.getIntExtra(
            "ID_CHARACTER",
            -1
        )

        viewModel.getCharacterById(idCharacter)

        viewModel.characterLiveDate.observe(this, Observer {
            val url = it.first().thumbnail?.path +'.'+ it.first().thumbnail?.extension
            Picasso.get().load(url).into(imageView);

            textViewNome.text = it.first().name
            textViewDescricao.text = it.first().description

            var comicsString = ""
            it.first().comics?.items?.forEach { comicSummary ->
                comicsString += "• ${comicSummary.name}\n\n"
            }
            textViewComics.text = comicsString
        })

        //Toast.makeText(this, "ID: ${idCharacter}", Toast.LENGTH_SHORT).show()

    }


}
