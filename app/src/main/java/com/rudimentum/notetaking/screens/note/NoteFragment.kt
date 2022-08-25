package com.rudimentum.notetaking.screens.note

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rudimentum.notetaking.R
import com.rudimentum.notetaking.databinding.FragmentNoteBinding
import com.rudimentum.notetaking.models.AppNote

class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable(R.string.bundle_key_note.toString()) as AppNote

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // add menu items
                menuInflater.inflate(R.menu.note_action_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // handle the menu selection
                return when (menuItem.itemId) {
                    R.id.btnDelete -> {
                        mViewModel.delete(mCurrentNote) {
                            findNavController().navigate(R.id.action_noteFragment_to_mainFragment)
                        }
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        // fill textViews from current note
        mBinding.noteName.text = mCurrentNote.name
        mBinding.noteText.text = mCurrentNote.text
        // initialize view model
        mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}