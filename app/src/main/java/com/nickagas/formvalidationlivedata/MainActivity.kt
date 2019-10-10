package com.nickagas.formvalidationlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.nickagas.formvalidationlivedata.databinding.ActivityMainBinding
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    // 1. Create the view model
    val viewModel: FormValidationViewModel by lazy {
        ViewModelProviders.of(this).get(FormValidationViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "My Cool Form"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        val handler = Handler()
        binding.handler = handler
    }
}
