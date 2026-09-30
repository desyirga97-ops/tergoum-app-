package com.tergoum.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.tergoum.app.ui.components.BottomNavBar
import com.tergoum.app.ui.components.ScreenTab
import com.tergoum.app.ui.screens.*
import com.tergoum.app.ui.theme.TergoumTheme
import com.tergoum.app.viewmodel.DictionaryViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: DictionaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TergoumTheme {
                TergoumMainApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun TergoumMainApp(viewModel: DictionaryViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var currentTab by remember { mutableStateOf(ScreenTab.SEARCH) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                currentTab = currentTab,
                onTabSelected = { currentTab = it }
            )
        }
    ) { innerPadding ->
        when (currentTab) {
            ScreenTab.SEARCH -> SearchScreen(
                query = uiState.searchQuery,
                onQueryChange = viewModel::onSearchQueryChanged,
                onClearQuery = viewModel::clearSearchQuery,
                direction = uiState.direction,
                onToggleDirection = viewModel::toggleDirection,
                selectedCategory = uiState.selectedCategory,
                categories = uiState.availableCategories,
                onSelectCategory = viewModel::selectCategory,
                searchResults = uiState.searchResults,
                expandedWordId = uiState.expandedWordId,
                onToggleExpand = viewModel::toggleExpandWord,
                onToggleFavorite = viewModel::toggleFavorite,
                modifier = Modifier.padding(innerPadding)
            )
            ScreenTab.FAVORITES -> FavoritesScreen(
                favoriteWords = uiState.favoriteWords,
                expandedWordId = uiState.expandedWordId,
                onToggleExpand = viewModel::toggleExpandWord,
                onToggleFavorite = viewModel::toggleFavorite,
                modifier = Modifier.padding(innerPadding)
            )
            ScreenTab.FIDEL -> FidelScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}