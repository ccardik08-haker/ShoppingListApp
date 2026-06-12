package com.example.shoppinglist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.core.models.ShoppingItem
import com.example.shoppinglist.data.ShoppingRepository
import com.example.shoppinglist.data.ThemeDataStore
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShoppingViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = ShoppingRepository()
    private val themeDataStore = ThemeDataStore(application)

    val items = repository.items

    val isDarkTheme = themeDataStore.isDarkTheme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun addItem(name: String, quantity: Int) {
        viewModelScope.launch {
            val newItem = ShoppingItem(
                id = 0,
                name = name,
                quantity = quantity
            )
            repository.addItem(newItem)
        }
    }

    fun toggleItemCheck(id: Int) {
        viewModelScope.launch {
            repository.toggleItemCheck(id)
        }
    }

    fun deleteItem(item: ShoppingItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch {
            themeDataStore.setDarkTheme(isDark)
        }
    }
}