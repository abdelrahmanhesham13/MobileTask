package com.example.task.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.base.BaseActivity
import com.example.task.R
import com.example.task.databinding.ActivityHomeBinding
import com.example.task.ui.documentDetails.DocumentDetailsActivity

class HomeActivity : BaseActivity<HomeViewModel,ActivityHomeBinding>(),HomeNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding.viewModel = viewModel
        viewModel.navigator = this
    }
    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }

    override fun createViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun goToDocumentDetails() {
        val intent = Intent(this,DocumentDetailsActivity::class.java)
        startActivity(intent)
    }
}