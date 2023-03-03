package com.example.todolist.data.data_source

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.todolist.domain.model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun todos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}