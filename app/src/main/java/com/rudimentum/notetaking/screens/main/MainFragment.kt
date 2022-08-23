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
        initialization()
    }

    private fun initialization() {
        // initialize recyclerview
        mRecyclerViewNotesList = mBinding.rvNotesList
        // initialize adapter
        mNotesListAdapter = MainNotesListAdapter(
            onClick = { note -> openNote(note)}
        )
        mRecyclerViewNotesList.adapter = mNotesListAdapter
        // initialize observer
        mObserverList = Observer {
            // create list and add notes to up of list
            val listOfNotes = it.asReversed()
            mNotesListAdapter.setList(listOfNotes)
        }
        // initialize view model
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        mViewModel.allNotes.observe(this, mObserverList)

        // set click listener for fab
        mBinding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
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
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerViewNotesList.adapter = null
    }
}