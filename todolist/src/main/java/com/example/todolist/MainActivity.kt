package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.domain.util.TodoAndroidViewModelFactory
import com.example.todolist.ui.main.MainScreen
import com.example.todolist.ui.main.MainViewModel
import com.example.todolist.ui.theme.ExamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val viewModel by viewModels<MainViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
//            val viewModel: MainViewModel = viewModel(
//                factory = TodoAndroidViewModelFactory(application)
//            )
            val viewModel: MainViewModel =
                ViewModelProvider(this, TodoAndroidViewModelFactory(application = application)).get(
                    MainViewModel::class.java
                )
            MainScreen(viewModel = viewModel)
        }
    }
}

