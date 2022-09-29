package com.rudimentum.notetaking.screens.edit

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rudimentum.notetaking.R
import com.rudimentum.notetaking.databinding.FragmentEditNoteBinding
import com.rudimentum.notetaking.models.AppNote

class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: EditFragmentViewModel
    private lateinit var mCurrentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable(R.string.bundle_key_note.toString()) as AppNote

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
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
                menuInflater.inflate(R.menu.add_new_note_action_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // handle the menu selection
                return when (menuItem.itemId) {
                    R.id.btnSave -> {
                        handleClickForSaveEditingNote()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun handleClickForSaveEditingNote() {
        // get input data
        val name = mBinding.editNameNote.text.toString()
        val text = mBinding.editTextNote.text.toString()

        val bundle = Bundle()
        bundle.putSerializable(R.string.bundle_key_note.toString(), AppNote(name = name, text = text))
        mViewModel.update(name, text, mCurrentNote.id) {
            findNavController().navigate(R.id.action_editNoteFragment_to_noteFragment, bundle)
        }
    }

    private fun initialization() {
        // fill textViews from current note
        mBinding.editNameNote.setText(mCurrentNote.name)
        mBinding.editTextNote.setText(mCurrentNote.text)
        mBinding.editTextNote.movementMethod = ScrollingMovementMethod()
        // initialize view model
        mViewModel = ViewModelProvider(this).get(EditFragmentViewModel::class.java)
    }
}