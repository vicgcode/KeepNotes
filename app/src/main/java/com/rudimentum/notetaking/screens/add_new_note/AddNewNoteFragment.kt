package com.rudimentum.notetaking.screens.add_new_note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rudimentum.notetaking.R
import com.rudimentum.notetaking.databinding.FragmentAddNewNoteBinding
import com.rudimentum.notetaking.models.AppNote

class AddNewNoteFragment : Fragment() {

    private var _binding: FragmentAddNewNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)
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
                        handleClickOfAddNewNote()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initialization() {
        // initialize view model
        mViewModel = ViewModelProvider(this).get(AddNewNoteFragmentViewModel::class.java)
    }

    private fun handleClickOfAddNewNote() {
        // get input data
        val name = mBinding.inputNameNote.text.toString()
        val text = mBinding.inputTextNote.text.toString()

        if (name.isEmpty()) {
            Toast.makeText(activity, getString(R.string.toast_message_empty_name_note), Toast.LENGTH_SHORT).show()
        } else {
            mViewModel.insert(AppNote(name = name, text = text)) {
                findNavController().navigate(R.id.action_addNewNoteFragment_to_mainFragment)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}