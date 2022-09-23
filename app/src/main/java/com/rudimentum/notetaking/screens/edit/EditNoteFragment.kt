package com.rudimentum.notetaking.screens.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rudimentum.notetaking.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: EditFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}