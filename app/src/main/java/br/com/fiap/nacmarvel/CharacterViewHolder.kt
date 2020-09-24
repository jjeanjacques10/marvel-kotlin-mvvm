package br.com.fiap.nacmarvel

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.nacmarvel.characters.Character
import com.bumptech.glide.Glide


class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val textViewNome = itemView.findViewById<TextView>(R.id.textViewNome)
    private val textViewDescricao = itemView.findViewById<TextView>(R.id.textViewDescricao)
    private val linearLayout = itemView.findViewById<LinearLayout>(R.id.linearLayout)
    private val imageView = ImageView(itemView.context)

    fun bind(item: Character){
        textViewNome.text = item.name
        if(item.description != ""){
            textViewDescricao.text = item.description?.substring(0, 100) + "..."
        }else{
            textViewDescricao.text = "--"
        }

        val url = item.thumbnail?.path +'.'+ item.thumbnail?.extension
        Glide.with(itemView.context).load(url).into(imageView)

        //linearLayout.addView(imageView, 0)

        itemView.setOnClickListener {
            item.onClick?.invoke(item.id)
        }
    }

}