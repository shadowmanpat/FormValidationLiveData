package com.nickagas.formvalidationlivedata

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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