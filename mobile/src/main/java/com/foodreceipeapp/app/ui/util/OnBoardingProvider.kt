package com.foodreceipeapp.app.ui.util

import com.foodreceipeapp.model.OnBoardingItem
import com.foodreceipeapp.app.R

object OnBoardingProvider {
    val onBoardingDataList = listOf(
        OnBoardingItem(
            R.string.onBoarding_page_1_title,
            R.string.onBoarding_page_1_Description,
            R.drawable.dish_1
        ),
        OnBoardingItem(
            R.string.onBoarding_page_2_title,
            R.string.onBoarding_page_2_Description,
            R.drawable.dish_2
        ),
        OnBoardingItem(
            R.string.onBoarding_page_3_title,
            R.string.onBoarding_page_3_Description,
            R.drawable.dish_1
        )
    )
}
