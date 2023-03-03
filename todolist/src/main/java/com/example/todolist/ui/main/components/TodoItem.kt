package com.example.todolist.ui.main.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.domain.model.Todo
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TodoItem(todo: Todo, onClick: (Int) -> Unit, onDelete: (Int) -> Unit) {

    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    Row(
        modifier = Modifier.clickable { onClick(todo.uid) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .clickable { onDelete(todo.uid) },
            painter = painterResource(id = R.drawable.baseline_delete_24),
            contentDescription = "delete",
            tint = Color(0xFFA51212),
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = format.format(Date(todo.date)),
                color = if (todo.isDone) Color.Gray else Color.Black,
                style = TextStyle(textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None)
            )
            Text(
                text = todo.title,
                color = if (todo.isDone) Color.Gray else Color.Black,
                style = TextStyle(textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None)
            )
        }

        if (todo.isDone) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_done_24),
                contentDescription = "isDone",
                tint = Color(0xFF00BCD4)
            )
        }
    }

}