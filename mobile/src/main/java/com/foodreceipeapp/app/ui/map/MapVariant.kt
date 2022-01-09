package com.foodreceipeapp.app.ui.map

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.foodreceipeapp.app.R

/**
 * A variant of the map UI. Depending on the variant, Map UI may show different markers, tile
 * overlays, etc.
 */
enum class MapVariant(
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int,
    @RawRes val styleResId: Int,
) {
    Standard(
        R.string.map_variant_standard,
        R.drawable.ic_standard_map,
        R.raw.map_style_default
    ),
    Dark(
        R.string.map_variant_after_dark,
        R.drawable.ic_map_after_dark,
        R.raw.map_style_night
    ),
    DAY(
        R.string.map_variant_daytime,
        R.drawable.ic_map_daytime,
        R.raw.map_style_day
    );
}
