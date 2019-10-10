package com.nickagas.formvalidationlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.nickagas.formvalidationlivedata.databinding.ActivityMainBinding
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    // 1. Create the view model
    val viewModel: FormValidationViewModel by lazy {
        ViewModelProviders.of(this).get(FormValidationViewModel::class.java)
    }

    companion object {
        const val myUserId = "id_123"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "My Cool Form"
        viewModel.initList()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel
        val handler = Handler()
        binding.handler = handler


        setAdapter()
        remove?.setOnClickListener {
            viewModel._list.value?.get(0)?.firstName  = "nikos"
            viewModel._list.value = viewModel._list.value
        }

        val listObserver = Observer<List<Item>> { newName ->
            // Update the UI, in this case, a TextView.
            Log.d("observe", newName.toString())

        }
        viewModel._list.observe(this, listObserver)
    }


    private fun setAdapter() {
        binding.activityMainRecycler.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        val itemAdapter = ItemAdapter()
        binding.activityMainRecycler.adapter = itemAdapter
        itemAdapter.viewModel = viewModel
        viewModel._list.observe(this, androidx.lifecycle.Observer {
            Log.d("observe", it.toString())

            itemAdapter.items = it

            itemAdapter.notifyDataSetChanged()
        })

    }
}
