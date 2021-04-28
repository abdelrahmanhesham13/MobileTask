package com.example.notesapp.base

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<vm : ViewModel, db : ViewDataBinding> : AppCompatActivity() {
    lateinit var viewModel: vm
    lateinit var binding: db
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResource())
        viewModel = createViewModel()
    }
    abstract fun getLayoutResource(): Int
    abstract fun createViewModel(): vm
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    open fun showDialogMessage(
        message: String, posActionName: Int,
        posAction: DialogInterface.OnClickListener?, isCancelable: Boolean
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(posActionName, posAction)
        builder.setCancelable(isCancelable)
        builder.show()
    }
}