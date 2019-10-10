package com.nickagas.formvalidationlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.nickagas.formvalidationlivedata.databinding.ActivityMainBinding
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    // 1. Create the view model
    val viewModel: FormValidationViewModel by lazy {
        ViewModelProviders.of(this).get(FormValidationViewModel::class.java)
    }

    companion object {
        const val myUserId = "id_123"
    }

    var list = listOf<Item>(
        Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"),
        Item(UUID.randomUUID().toString(), "Kate", "Whitewalker", 2, "Team Gnome"),
        Item(UUID.randomUUID().toString(), "Edward", "Baker", 3, "Team KDE"),
        Item(myUserId, "Tony", "Ashenberg", 4, "Team xfce"),
        Item(UUID.randomUUID().toString(), "Roman", "Antysoman", 5, "Team xfce"),
        Item(UUID.randomUUID().toString(), "Mike", "The Shroom", 6, "Team Gnome"),
        Item(UUID.randomUUID().toString(), "Dolan", "Dolański", 7, "Team KDE")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "My Cool Form"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        val handler = Handler()
        binding.handler = handler
        setAdapter()

    }
    private fun setAdapter() {
        binding.activityMainRecycler.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        val itemAdapter = ItemAdapter()
        binding.activityMainRecycler.adapter = itemAdapter

        val viewModelList = list.map {
            ItemViewModel(it.position.toString(), "${it.firstName} ${it.lastName}", it.teamName, it.id.contentEquals(myUserId))
        }

        itemAdapter.items = viewModelList
    }
}
