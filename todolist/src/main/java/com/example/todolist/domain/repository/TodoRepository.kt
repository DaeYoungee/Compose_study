package com.example.todolist.domain.repository

import com.example.todolist.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository { // 뷰모델이 데이터를 조작할
    fun observeTodos(): Flow<List<Todo>>

    suspend fun addTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)


}