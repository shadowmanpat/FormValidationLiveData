package com.nickagas.formvalidationlivedata

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.graphics.ColorSpace.Model
import android.view.View
import android.widget.Toast


class FormValidationViewModel: ViewModel() {
    val emailAddress = MutableLiveData<String>("")

    val valid = MediatorLiveData<Boolean>().apply {
        addSource(emailAddress) {
            val valid = isFormValid(it)
            Log.d(it, valid.toString())
            value = valid
        }
    }

    fun isFormValid(emailAddress: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }
}

class Handler {
    fun onClickMethodReference(view: View) {
        Toast.makeText(view.context,"onClickMethodReference",Toast.LENGTH_LONG).show()

    }

    fun onClickLamda() {
        Log.d("asdf","onClickLamda")
    }

    fun onClickLamdaWithView(view: View) {
        Toast.makeText(view.context,"onClickLamdaWithView",Toast.LENGTH_LONG).show()
    }

    fun onClickLamdaWithObject(view: View,model: FormValidationViewModel) {
        Toast.makeText(view.context,"onClickLamdaWithObject ${model.emailAddress.value}",Toast.LENGTH_LONG).show()
    }
}