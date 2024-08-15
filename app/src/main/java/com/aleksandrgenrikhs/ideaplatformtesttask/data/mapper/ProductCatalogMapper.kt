package com.aleksandrgenrikhs.ideaplatformtesttask.data.mapper

import android.icu.text.SimpleDateFormat
import android.os.Build
import com.aleksandrgenrikhs.ideaplatformtesttask.data.database.ProductEntry
import com.aleksandrgenrikhs.ideaplatformtesttask.domain.ProductModel
import java.sql.Date
import java.util.Locale
import javax.inject.Inject

class ProductCatalogMapper
@Inject constructor() {

    fun map(product: List<ProductEntry>): List<ProductModel> = product.map {
        ProductModel(
            id = it.id,
            time = convertTimestampToDate(it.time),
            name = it.name,
            tags = it.tags,
            amount = it.amount,
        )
    }

    private fun convertTimestampToDate(timestamp: Long): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val date = Date(timestamp)
            val formatter = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            return formatter.format(date)
        } else {
            return ""
        }
    }
}
