package com.example.projectmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.projectmvvm.databinding.ActivityMainBinding
import com.example.projectmvvm.viewmodel.QuotViewModel

class MainActivity : AppCompatActivity() {

    private val quotViewModel: QuotViewModel by viewModels()

    private lateinit var mainActivity: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)
        quotViewModel.quotLiveData().observe(this){
            mainActivity.qouteTextView.text = it.quot
            mainActivity.autorTextView.text = it.autor
        }
        mainActivity.viewContainer.setOnClickListener {
            quotViewModel.randomQuot()
        }
        quotViewModel.randomQuot()
    }
}