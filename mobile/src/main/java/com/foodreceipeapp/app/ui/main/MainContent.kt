package com.foodreceipeapp.app.ui.main

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.primarySurface
import androidx.compose.material.IconButton
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Explore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodreceipeapp.app.R
import com.foodreceipeapp.app.ui.bookmark.BookMark
import com.foodreceipeapp.app.ui.bookmark.BookmarkViewModel
import com.foodreceipeapp.app.ui.recipes.HomeContent
import com.foodreceipeapp.app.ui.plan.MealPlan
import com.foodreceipeapp.app.ui.plan.MealPlanViewModel
import com.foodreceipeapp.app.ui.recipes.RecipesViewModel
import com.google.accompanist.insets.navigationBarsPadding

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainContent(
    viewModel: RecipesViewModel,
    bookmarkViewModel: BookmarkViewModel,
    mealPlanViewModel: MealPlanViewModel,
    onIngredientContent: () -> Unit,
    onCuisineSearch: (String) -> Unit,
    onDetails: (Int) -> Unit,
    onExploreClicked: () -> Unit,
    onIngredientSearch: (String) -> Unit
) {

    val (selectedTab, setSelectedTab) = remember { mutableStateOf(DelishHomeTabs.Home) }
    val tabs = DelishHomeTabs.values()
    Scaffold(
        backgroundColor = MaterialTheme.colors.primarySurface,
        topBar = {
            HomeTopBar {
                onExploreClicked()
            }
        },
        bottomBar = {
            BottomNavigation {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = stringResource(id = tab.title)) },
                        selected = tab == selectedTab,
                        onClick = { setSelectedTab(tab) },
                        alwaysShowLabel = false,
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = LocalContentColor.current,
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }
    ) {
        when (selectedTab) {
            DelishHomeTabs.Home -> HomeContent(
                viewModel,
                onIngredientContent,
                onCuisineSearch,
                onDetails,
                onIngredientSearch
            )
            DelishHomeTabs.BookMark -> BookMark(bookmarkViewModel, onDetails)
            DelishHomeTabs.MealPlan -> MealPlan(mealPlanViewModel, onDetails)
        }
    }
}

@Composable
fun HomeTopBar(onExploreClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .padding(8.dp)
            )
        },
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = {
                        onExploreClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Explore,
                        contentDescription = stringResource(R.string.map)
                    )
                }
            }
        },
        elevation = 6.dp
    )
}

enum class DelishHomeTabs(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    Home(R.string.recipes_tab, Icons.Filled.BlurOn),
    BookMark(R.string.book_mark, Icons.Filled.BookmarkBorder),
    MealPlan(R.string.meal_plan, Icons.Filled.EventNote)
}
