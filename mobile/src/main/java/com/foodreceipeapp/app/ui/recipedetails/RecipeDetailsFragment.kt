package com.foodreceipeapp.app.ui.recipedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.foodreceipeapp.app.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModels()
    private val detailArgs: RecipeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipeDetailsViewModel.getRecipeDetails(detailArgs.id)

        return ComposeView(requireContext()).apply {
            setContent {
                DelishComposeTheme {
                    RecipeDetails(recipeDetailsViewModel, findNavController())
                }
            }
        }
    }
}
