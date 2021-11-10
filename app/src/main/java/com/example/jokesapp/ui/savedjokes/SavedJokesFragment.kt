package com.example.jokesapp.ui.savedjokes

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*


class SavedJokesFragment : Fragment() {
    private lateinit var savedJokesRecyclerView : RecyclerView
    private lateinit var savedJokesListAdapter : SavedJokesListAdapter
    private val savedJokesViewModel : SavedJokesViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_jokes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedJokesRecyclerView = view.findViewById(R.id.saved_jokes_list)
        savedJokesListAdapter = SavedJokesListAdapter(requireContext(),savedJokesViewModel)
        savedJokesRecyclerView.adapter = savedJokesListAdapter
        savedJokesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        savedJokesRecyclerView.itemAnimator = DefaultItemAnimator()

            Log.e("tag","in saved fragment")

            savedJokesViewModel.getSavedJokes().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                savedJokesListAdapter.updateJokeList(it)
            })

    }


}