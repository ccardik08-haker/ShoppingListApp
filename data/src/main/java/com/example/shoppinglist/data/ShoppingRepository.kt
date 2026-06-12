package com.example.shoppinglist.data

import com.example.shoppinglist.core.models.ShoppingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShoppingRepository {

    private val _items = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val items: Flow<List<ShoppingItem>> = _items.asStateFlow()

    private var nextId = 1

    suspend fun addItem(item: ShoppingItem) {
        val newItem = item.copy(id = nextId++)
        _items.value = _items.value + newItem
    }

    suspend fun updateItem(item: ShoppingItem) {
        _items.value = _items.value.map {
            if (it.id == item.id) item else it
        }
    }

    suspend fun deleteItem(item: ShoppingItem) {
        _items.value = _items.value.filter { it.id != item.id }
    }

    suspend fun toggleItemCheck(id: Int) {
        _items.value = _items.value.map { item ->
            if (item.id == id) item.copy(isChecked = !item.isChecked)
            else item
        }
    }
}