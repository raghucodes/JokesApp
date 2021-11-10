package com.example.jokesapp.ui.savedjokes

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
import com.example.jokesapp.ui.jokeactivity.JokesListAdapter
import com.example.jokesapp.ui.jokeactivity.JokesViewModel

class SavedJokesListAdapter(private val context: Context, private val savedJokesViewModel: SavedJokesViewModel):RecyclerView.Adapter<SavedJokesListAdapter.MyViewHolder>() {
    private var jokes : List<JokeEntity> = ArrayList()

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
        Log.e("saved joke count",jokes.size.toString())
       return jokes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setUp.text = jokes[position].setUp
        holder.punchLine.text = jokes[position].punchLine
        holder.saveJoke.setBackgroundResource(R.drawable.ic_baseline_star_24)
        holder.saveJoke.setOnClickListener {
            val joke : JokeEntity = JokeEntity(jokes[position].setUp,jokes[position].punchLine)
            savedJokesViewModel.deleteJoke(joke)
        }
    }

    fun updateJokeList(jokesList : List<JokeEntity>){
        jokes = jokesList
        notifyDataSetChanged()
    }

}