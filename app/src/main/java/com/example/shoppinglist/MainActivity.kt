package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppinglist.ui.ShoppingListScreen
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.viewmodel.ShoppingViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: ShoppingViewModel = viewModel()
            val isDarkTheme by viewModel.isDarkTheme.collectAsState()

            // Применяем тему здесь!
            ShoppingListTheme(darkTheme = isDarkTheme) {
                ShoppingListScreen(viewModel = viewModel)
            }
        }
    }
}