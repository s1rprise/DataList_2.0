package com.example.datalist.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.datalist.R

data class Product(
    @StringRes val name: Int,
    @DrawableRes val image: Int,
    val price: Float,
    @StringRes val brand: Int,
    @StringRes val description: Int
)

val products = listOf(
    Product(R.string.name1, R.drawable.icon_products, 10.79f, R.string.brand1, R.string.description1),
    Product(R.string.name2, R.drawable.icon_products, 14.69f, R.string.brand2, R.string.description2),
    Product(R.string.name3, R.drawable.icon_products, 23.80f, R.string.brand3, R.string.description3),
    Product(R.string.name4, R.drawable.icon_products, 3.35f, R.string.brand4, R.string.description4),
    Product(R.string.name5, R.drawable.icon_products, 12.50f, R.string.brand5, R.string.description5),
    Product(R.string.name6, R.drawable.icon_products, 14.45f, R.string.brand6, R.string.description6)
)
