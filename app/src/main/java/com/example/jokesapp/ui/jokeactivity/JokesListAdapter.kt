package com.example.jokesapp.ui.jokeactivity

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.R
import com.example.jokesapp.data.api.models.ResponseDataItem
import com.example.jokesapp.domain.entities.JokeEntity

class JokesListAdapter(private val context: Context,private val jokesViewModel: JokesViewModel) : RecyclerView.Adapter<JokesListAdapter.MyViewHolder>() {
    private var jokes : List<ResponseDataItem> = ArrayList()
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val setUp = itemView.findViewById<TextView>(R.id.setup)
        val punchLine = itemView.findViewById<TextView>(R.id.punchline)
        val saveJoke = itemView.findViewById<ImageButton>(R.id.save_joke)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.joke_row,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setUp.text = jokes[position].setup
        holder.punchLine.text = jokes[position].punchline
        holder.saveJoke.setOnClickListener{
            val jokeEntity = JokeEntity(holder.setUp.text.toString(),holder.punchLine.text.toString())
            Log.e("jokesaved",jokeEntity.punchLine)
            jokesViewModel.saveJoke(jokeEntity)
            holder.saveJoke.setBackgroundResource(R.drawable.ic_baseline_star_24)
        }
    }

    fun updateJokeList(jokesList : List<ResponseDataItem>){
        jokes = jokesList
        notifyDataSetChanged()
    }

}