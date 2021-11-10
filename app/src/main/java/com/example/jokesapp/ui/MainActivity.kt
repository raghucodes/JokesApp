package com.example.jokesapp.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.jokesapp.R
import com.example.jokesapp.domain.di.savedJokesApplicationContext
import com.example.jokesapp.ui.networkstate.Event
import com.example.jokesapp.ui.networkstate.NetworkEvents
import com.example.jokesapp.ui.savedjokes.SavedJokesFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(MainFragment())

        NetworkEvents.observe(this, Observer {
            if(it is Event.ConnectivityAvailable){
                Toast.makeText(applicationContext,"connected",Toast.LENGTH_SHORT).show()
               // dialog?.hide()
            }
            if(it is Event.ConnectivityLost){
                Toast.makeText(applicationContext,"disconnected",Toast.LENGTH_LONG).show()
                displayDialog()
            }
        })

        savedJokesApplicationContext = applicationContext
    }

    fun loadFragment(fragment: Fragment){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun displayDialog(){

        val builder: AlertDialog.Builder? = this?.let { AlertDialog.Builder(it) }
        builder?.setMessage("No Internet Detected")?.setTitle("Connection Lost")
        builder?.apply {
            setPositiveButton("open settings",DialogInterface.OnClickListener{dialog, id ->
                val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                startActivityForResult(panelIntent, 545)
            })
            setNegativeButton("cancel",DialogInterface.OnClickListener{dialog, id ->

            })
        }
        val dialog:AlertDialog? = builder?.create()
        dialog?.show()
    }

}