package com.example.mygallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            SideEffect()

        }
    }
}

//@Composable
//fun SideEffect() {
//    val (text, setText) = remember {
//        mutableStateOf("initial")
//    }
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)) {
//        TextField(
//            value = text,
//            onValueChange = setText,
//            modifier = Modifier
//                .fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//        RememberUpdateTestText(text)
//    }
//}
//
//@Composable
//fun RememberUpdateTestText(text: String) {
//    val rememberText by remember {
//        mutableStateOf(text)
//    }
//    val rememberUpdatedText by rememberUpdatedState(newValue = text)
//    Text(text = "Text : $text")
//    Text(text = "RememberText : $rememberText")
//    Text(text = "UpdatedText : ${rememberUpdatedText}")
//}