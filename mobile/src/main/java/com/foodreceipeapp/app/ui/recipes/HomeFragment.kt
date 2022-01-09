package com.foodreceipeapp.app.ui.recipes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.foodreceipeapp.app.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import com.foodreceipeapp.app.ui.recipes.graph.HomeNavGraph
import com.foodreceipeapp.app.ui.search.SearchType
import com.google.accompanist.insets.ProvideWindowInsets

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    @SuppressLint("VisibleForTests")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                DelishComposeTheme {
                    ProvideWindowInsets {
                        HomeNavGraph(
                            onCuisineSearch = { cuisine ->
                                val action = HomeFragmentDirections
                                    .goToSearchScreen(cuisine, SearchType.CUISINE)
                                findNavController().navigate(action)
                            },
                            onIngredientSearch = { query ->
                                val action =
                                    HomeFragmentDirections.goToSearchScreen(query, SearchType.QUERY)
                                findNavController().navigate(action)
                            },
                            onExplore = {
                                findNavController().navigate(HomeFragmentDirections.goToMapScreen())
                            }
                        ) { recipeId ->
                            val action = HomeFragmentDirections.goToRecipesDetails(recipeId)
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }
}
