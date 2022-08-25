package com.rudimentum.notetaking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rudimentum.notetaking.databinding.ActivityMainBinding
import com.rudimentum.notetaking.utilities.AppPreference

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mToolbar = mBinding.toolbar
        navController = Navigation.findNavController(this, R.id.navHostFragment)

        setSupportActionBar(mToolbar)
        title = getString(R.string.toolbar_title)

        AppPreference.getPreference(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // saving memory
        _binding = null
    }
}