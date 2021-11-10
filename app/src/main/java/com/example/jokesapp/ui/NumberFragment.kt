package com.example.jokesapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.jokesapp.R
import com.example.jokesapp.ui.jokeactivity.JokesFragment

class NumberFragment : Fragment() {
    private lateinit var oneJoke : CardView
    private lateinit var tenJokes : CardView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        oneJoke = view.findViewById(R.id.one)
        tenJokes = view.findViewById(R.id.ten)

        val type  = arguments?.getString("type")
        Log.e("type",type.toString())

        oneJoke.setOnClickListener {
            if (type != null) {
                loadFragment(it,type,"random")
            }
        }
        tenJokes.setOnClickListener {
            if (type != null) {
                loadFragment(it,type,"ten")
            }
        }
    }
    fun loadFragment(view: View,type : String,number : String){
        val fragment : Fragment = JokesFragment()
        val args : Bundle = Bundle()
        args.putString("type",type)
        args.putString("number",number)
        fragment.arguments = args
        val appCompatActivity : AppCompatActivity = view.context as AppCompatActivity
        appCompatActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.frame,fragment)
            .addToBackStack(null)
            .commit()
    }
}
