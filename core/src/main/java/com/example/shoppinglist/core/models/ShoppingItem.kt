package com.example.shoppinglist.core.models

data class ShoppingItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    val isChecked: Boolean = false
)