package com.foodreceipeapp.app.ui.bookmark

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.foodreceipeapp.app.R
import com.foodreceipeapp.app.ui.recipes.InspirationItem
import com.foodreceipeapp.app.ui.widget.EmptyView

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun BookMark(
    viewModel: BookmarkViewModel,
    onDetails: (Int) -> Unit
) {
    val recipes by viewModel.state.collectAsState()
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(recipes.recipes.distinct()) { recipe ->
            InspirationItem(recipe, onDetails = onDetails) {
                viewModel.deleteRecipe(recipe)
            }
        }
    }

    AnimatedVisibility(visible = recipes.isEmpty) {
        EmptyView(
            descText = stringResource(id = R.string.book_mark_empty)
        )
    }
}
