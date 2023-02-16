package com.example.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stopwatch.ui.theme.ExamplesTheme
import java.util.*
import kotlin.concurrent.timer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()
            MainScreen(
                sec = viewModel.sec.value,
                milli = viewModel.milli.value,
                lapTimes = viewModel.lapTimes.value,
                isRunning = viewModel.isRunning.value,
                onReset = { viewModel.reset() },
                onToggle = { running ->
                    if (running) viewModel.pause()
                    else viewModel.start()
                },
                onRecord = { viewModel.recordLapTime() },
            )
        }
    }
}

class MainViewModel : ViewModel() {
    private var time = 0
    private var timeTask: Timer? = null

    private val _isRunning = mutableStateOf(false)
    val isRunning: State<Boolean> = _isRunning

    private val _sec = mutableStateOf(0)
    val sec: State<Int> = _sec

    private val _milli = mutableStateOf(0)
    val milli: State<Int> = _milli

    private val _lapTimes = mutableStateOf(mutableListOf<String>())
    val lapTimes: State<List<String>> = _lapTimes

    private var lapCount = 1

    fun start() {
        _isRunning.value = true
        timeTask = timer(period = 10) {
            time++
            _sec.value = time / 100
            _milli.value = time % 100
        }
    }

    fun pause() {
        timeTask?.cancel()
        _isRunning.value = false
    }

    fun reset() {
        timeTask?.cancel()
        _isRunning.value = false
        time = 0
        _sec.value = 0
        _milli.value = 0
        _lapTimes.value.clear()
        lapCount = 1
    }

    fun recordLapTime() {
        _lapTimes.value.add(0, "Lap $lapCount ${_sec.value}.${_milli.value} ")
        lapCount++
    }
}

@Composable
fun MainScreen(
    sec: Int,
    milli: Int,
    lapTimes: List<String>,
    isRunning: Boolean = false,
    onReset: () -> Unit,
    onToggle: (Boolean) -> Unit,
    onRecord: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "StopWatch") }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = "$sec", fontSize = 100.sp)
                Text(text = "$milli")
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
//                lapTimes.forEach( lapTime -> {Text(text = lapTime)})
                items(lapTimes.size) { item -> Text(text = "${lapTimes[item]}") }
            }

            BottomBtn(
                isRunning = isRunning,
                onReset = onReset,
                onToggle = onToggle,
                onRecord = onRecord
            )
        }
    }

}

@Composable
fun BottomBtn(
    isRunning: Boolean = false,
    onReset: () -> Unit,
    onToggle: (Boolean) -> Unit,
    onRecord: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = { onReset() },
            backgroundColor = Color.Red
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_refresh_24),
                contentDescription = "refresh",
                colorFilter = ColorFilter.tint(color = Color.White)
            )
        }
        FloatingActionButton(
            onClick = { onToggle(isRunning) },
            backgroundColor = Color.Green,
        ) {
            Image(
                painter = painterResource(
                    id =
                    if (isRunning) R.drawable.baseline_play_arrow_24
                    else R.drawable.baseline_pause_24
                ),
                contentDescription = "start/pause",
                colorFilter = ColorFilter.tint(color = Color.White)
            )
        }
        Button(onClick = { onRecord() }) {
            Text(text = "랩 타임")
        }
    }

}