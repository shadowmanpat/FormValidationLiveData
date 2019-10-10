package com.nickagas.formvalidationlivedata

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.graphics.ColorSpace.Model
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import java.util.*


class FormValidationViewModel: ViewModel() {
    val emailAddress = MutableLiveData<String>("")
    var _list = MutableLiveData<List<Item>>()


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
    fun remove(){
        _list.value!![0].firstName = "nikos"
        _list.value!![0].id = "nikos"
    }

    fun removeFirstItem(view: View,model: Item){
//        list.value?.get(0)!!.firstName= "nikos"
        Toast.makeText(view.context,"onClickLamdaWithObject ${model.firstName}",Toast.LENGTH_LONG).show()
        _list.value?.forEachIndexed { index, item ->
            if(item.firstName == model.firstName){
                _list.value!![index].firstName = "nikos"
                _list.value = _list.value

            }
        }
    }
    fun initList(){
        _list.value = listOf<Item>(
            Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"),
            Item(UUID.randomUUID().toString(), "Kate", "Whitewalker", 2, "Team Gnome"),
            Item(UUID.randomUUID().toString(), "Edward", "Baker", 3, "Team KDE"),
            Item(MainActivity.myUserId, "Tony", "Ashenberg", 4, "Team xfce"),
            Item(UUID.randomUUID().toString(), "Roman", "Antysoman", 5, "Team xfce"),
            Item(UUID.randomUUID().toString(), "Mike", "The Shroom", 6, "Team Gnome"),
            Item(UUID.randomUUID().toString(), "Dolan", "Dolański", 7, "Team KDE")
        )
    }


    fun onClickItem(view: View,model: Item) {
        Toast.makeText(view.context,"onClickLamdaWithObject ${model.firstName}",Toast.LENGTH_LONG).show()

    }



}
class Handler {
    fun onClickMethodReference(view: View) {
        Toast.makeText(view.context,"onClickMethodReference",Toast.LENGTH_LONG).show()

    }
    fun remove(view: View) {
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
    fun onClickItem(view: View,model: Item) {
        Toast.makeText(view.context,"onClickLamdaWithObject ${model.firstName}",Toast.LENGTH_LONG).show()

    }
}