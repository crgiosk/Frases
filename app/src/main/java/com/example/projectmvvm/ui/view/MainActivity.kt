package com.example.projectmvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.projectmvvm.core.UIState
import com.example.projectmvvm.databinding.ActivityMainBinding
import com.example.projectmvvm.ui.viewmodel.QuotViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val quotViewModel: QuotViewModel by viewModels()

    private var intervalInSeconds = 10

    private lateinit var mainActivity: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)

        quotViewModel.onCreate()
        quotViewModel.getAllQuotsLiveData().observe(this) {

            when (it) {
                is UIState.OnLoading -> {
                    mainActivity.progressBar.visibility = View.VISIBLE
                }
                is UIState.OnSuccess -> {
                    mainActivity.progressBar.visibility = View.GONE
                    mainActivity.cMeter.start()
                    with(it.data) {
                        mainActivity.qouteTextView.text = this.quote
                        mainActivity.autorTextView.text = this.author
                    }
                }
                is UIState.OnError -> {
                    mainActivity.cMeter.stop()
                    mainActivity.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                }
            }
        }

        quotViewModel.getQuotLiveData().observe(this) {

            when (it) {
                is UIState.OnLoading -> {
                    mainActivity.progressBar.visibility = View.VISIBLE
                }
                is UIState.OnSuccess -> {
                    mainActivity.cMeter.start()
                    mainActivity.progressBar.visibility = View.GONE
                    with(it.data) {
                        mainActivity.qouteTextView.text = this.quote
                        mainActivity.autorTextView.text = this.author
                    }
                }
                is UIState.OnError -> {
                    mainActivity.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                }
            }
        }
        mainActivity.cMeter.setOnChronometerTickListener {
            val currentTimer = SystemClock.elapsedRealtime() - it.base
            if (currentTimer > intervalInSeconds * 1100){
                clearAndResetChronometer()
            }
            println(currentTimer)
        }

        mainActivity.viewContainer.setOnClickListener {
            clearAndResetChronometer()
            quotViewModel.randomQuot()
        }
    }

    private fun clearAndResetChronometer(){
        mainActivity.cMeter.base = SystemClock.elapsedRealtime();
        mainActivity.cMeter.stop()
        quotViewModel.randomQuot()
    }
}