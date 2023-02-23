package com.example.tiltsensor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()
            viewModel.state.observe(
                this,
                Observer { Log.d("test", "observe 성공, state: ${viewModel.state.value}") })
            Column() {
                Button(onClick = {
                    viewModel.setState(viewModel.state.value ?: false)
                }) {
                    Text(text = "${viewModel.state.value}")
                }
            }
        }
    }
}

class MainViewModel : ViewModel() {
    private val _state = MutableLiveData<Boolean>(false)
    val state: LiveData<Boolean> = _state
    fun setState(param: Boolean) {
        _state.value = !param
    }
}