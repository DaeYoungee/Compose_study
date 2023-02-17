package com.example.stopwatch

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

fun main() {
    val arr1:MutableList<String> = mutableListOf()

    arr1.add("바나나우유")
    arr1.add("딸기우유")
    arr1.add("키위우유")
    arr1.forEach{println(it)}

    val arr2:List<String> = listOf()
//    arr2.add("초코우유")

}

class CustomViewModel: ViewModel() {
    private val _testArr = mutableStateOf(mutableListOf<String>())
    val testArr: State<List<String>> = _testArr
}