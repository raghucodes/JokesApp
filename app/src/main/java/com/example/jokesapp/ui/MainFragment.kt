package com.example.jokesapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import com.example.jokesapp.R
import com.example.jokesapp.ui.savedjokes.SavedJokesFragment

class MainFragment : Fragment() {

    private lateinit var programming : CardView
    private lateinit var general : CardView
    private lateinit var saved : CardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
               // savedJokesApplicationContext = applicationContext
        programming = view.findViewById(R.id.programming)
        general = view.findViewById(R.id.general)
        saved = view.findViewById(R.id.saved)

        programming.setOnClickListener{
            loadFragment(it,"programming")
        }
        general.setOnClickListener{
            loadFragment(it,"general")
        }
        saved.setOnClickListener {
            openFragment(it)
        }
    }

    fun loadFragment(view: View,type : String){
        val fragment : Fragment = NumberFragment()
        val args : Bundle = Bundle()
        args.putString("type",type)
        fragment.arguments = args
        val appCompatActivity : AppCompatActivity = view.context as AppCompatActivity
        appCompatActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.frame,fragment)
            .addToBackStack(null)
            .commit()
    }
    fun openFragment(view: View){
        val fragment : Fragment = SavedJokesFragment()
        val appCompatActivity : AppCompatActivity = view.context as AppCompatActivity
        appCompatActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.frame,fragment)
            .addToBackStack(null)
            .commit()
    }
}