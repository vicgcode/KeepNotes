package com.rudimentum.notetaking.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rudimentum.notetaking.R
import com.rudimentum.notetaking.databinding.FragmentMainBinding
import com.rudimentum.notetaking.models.AppNote
import com.rudimentum.notetaking.utilities.AppPreference
import com.rudimentum.notetaking.utilities.TYPE_ROOM
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: MainFragmentViewModel
    private lateinit var mRecyclerViewNotesList: RecyclerView
    private lateinit var mNotesListAdapter: MainNotesListAdapter
    private lateinit var mObserverList: Observer<List<AppNote>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        // initialize view model
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        if (AppPreference.getInitUser()) {
            mViewModel.initDatabase(AppPreference.getTypeDatabase()) {}
        } else {
            initDatabase()
        }

        addRecyclerViewAdapter()
        addObserver()
        // set click listener for fab
        mBinding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    private fun initDatabase() {
        mViewModel.initDatabase(TYPE_ROOM) {
            AppPreference.setInitUser(true)
            AppPreference.setTypeDatabase(TYPE_ROOM)
        }
    }

    private fun addRecyclerViewAdapter() {
        // initialize recyclerview
        mRecyclerViewNotesList = mBinding.rvNotesList
        // initialize adapter
        mNotesListAdapter = MainNotesListAdapter(
            onClick = { note -> openNote(note)}
        )
        mRecyclerViewNotesList.adapter = mNotesListAdapter
    }

    private fun addObserver() {
        // initialize observer
        mObserverList = Observer {
            // create list and add notes to up of list
            val listOfNotes = it.asReversed()
            mNotesListAdapter.setList(listOfNotes)
        }

        mViewModel.getAllNotes().observe(this, mObserverList)
    }

    // create bundle of serializable AppNote and open in NoteFragment
    private fun openNote(note: AppNote) {
        val bundle = Bundle()
        bundle.putSerializable(R.string.bundle_key_note.toString(), note)
        findNavController().navigate(R.id.action_mainFragment_to_noteFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mViewModel.getAllNotes().removeObserver(mObserverList)
        mRecyclerViewNotesList.adapter = null
    }
}