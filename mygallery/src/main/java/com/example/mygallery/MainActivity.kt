package com.example.mygallery

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            NavHost(navController = navController, startDestination = "main") {
                composable(route = "main") {
                    Scaffold(scaffoldState = scaffoldState) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {
                                navController.navigate("first")
                            }) {
                                Text(text = "change composable main")
                            }
                            Button(onClick = {
                                lifecycleScope.launch { scaffoldState.snackbarHostState.showSnackbar("main") }
                            }) {
                                Text(text = "show snackbar")
                            }
                        }
                    }
                }
                composable(route = "first") {
                    FirstScreen(navController = navController)
                }
                composable(route = "second") {
                    SecondScreen(navController = navController)
                }
            }

        }
    }
}

@Composable
fun RememberCorountione(onChange: () -> Unit, text: String) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                onChange()
                Log.d("change page", "$onChange")
            }) {
                Text(text = "change composable $text")
            }
            Button(onClick = {
                scope.launch { scaffoldState.snackbarHostState.showSnackbar(text) }
            }) {
                Text(text = "show snackbar")
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    val onChange = {
        navController.navigate("second")
    }
    RememberCorountione(onChange = onChange, text = "first page")
}

@Composable
fun SecondScreen(navController: NavController) {
    val onChange = {
        navController.navigate("main")
    }
    RememberCorountione(onChange = onChange, text = "second page")
}