package com.example.task.base

import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class CustomMessage(
    val message: String, val positiveActionName: String, val postiveAction:
    DialogInterface.OnClickListener
)

open class BaseViewModel<N> : ViewModel() {
    var navigator: N? = null
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var message: MutableLiveData<String> = MutableLiveData()
    var customMessage: MutableLiveData<CustomMessage> = MutableLiveData()

}