package com.foodreceipeapp.app.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.foodreceipeapp.app.ui.theme.DelishComposeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment() {
    private val mapViewModel: MapViewModel by viewModels()

    @ExperimentalPermissionsApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                DelishComposeTheme {
                    MapView(mapViewModel) {
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }
}
