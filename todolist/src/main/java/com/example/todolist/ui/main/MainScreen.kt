package com.example.todolist.ui.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.ui.main.components.TodoItem
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current

    var input by rememberSaveable { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "오늘 할 일") })
    }, scaffoldState = scaffoldState) {
        Column(modifier = Modifier.padding(it)) {
            Row(modifier = Modifier.padding(8.dp)) {
                OutlinedTextField(
                    modifier = Modifier.weight(weight = 1f),
                    value = input,
                    onValueChange = { input = it },
                    shape = RoundedCornerShape(8.dp),
                    placeholder = { Text(text = "할 일") },
                    maxLines = 1,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        viewModel.addTodo(input)
                        input = ""
                        focusManager.clearFocus()
                        Log.d("itemTest", "${viewModel.items}")
                    })
                )
            }
            Divider()
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(viewModel.items.value) { todoItem ->
                    Column {
                        TodoItem(todo = todoItem,
                            onClick = { index ->
                                viewModel.toggle(index)
                            },
                            onDelete = { index ->
                                viewModel.delete(index)
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(message = "할 일 삭제됨", actionLabel = "최소")
                                    if (result == SnackbarResult.ActionPerformed) { viewModel.restoreTodo()}
                                }
                            })
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color.Black, thickness = 1.dp)
                }
            }
        }
    }
}