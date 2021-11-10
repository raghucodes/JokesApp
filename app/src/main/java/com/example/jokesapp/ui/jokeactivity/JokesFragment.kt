package com.example.jokesapp.ui.jokeactivity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.R
import com.example.jokesapp.ui.jokeactivity.JokesListAdapter
import com.example.jokesapp.ui.jokeactivity.JokesViewModel
import org.koin.android.ext.android.inject

class JokesFragment : Fragment() {
    private lateinit var jokesRecyclerView : RecyclerView
    private lateinit var jokesListAdapter : JokesListAdapter
    private val jokesViewModel : JokesViewModel by inject()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jokes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jokesRecyclerView = view.findViewById(R.id.jokes_list)
        jokesListAdapter = JokesListAdapter(requireContext(),jokesViewModel)
        jokesRecyclerView.adapter = jokesListAdapter
        jokesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        jokesRecyclerView.itemAnimator = DefaultItemAnimator()

        val type  = arguments?.getString("type")
        if (type != null) { Log.e("jokesFragmentType",type) }
        val number  = arguments?.getString("number")
        if (number != null) { Log.e("jokesFragmentNumber",number) }
        if (number != null) {
            if (type != null) {
                jokesViewModel.getRandomJoke(type,number).observe(viewLifecycleOwner, Observer {
                    jokesListAdapter.updateJokeList(it)
                })
            }
        }
    }
}