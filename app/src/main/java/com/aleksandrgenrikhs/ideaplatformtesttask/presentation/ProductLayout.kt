package com.aleksandrgenrikhs.ideaplatformtesttask.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleksandrgenrikhs.ideaplatformtesttask.R
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.IdeaPlatformTestTaskTheme
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.background
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.dark_red
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.ic_launcher_background
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.purple_500

const val DELETE = "Delete"
const val UPDATE_AMOUNT = "Amount"

@Composable
fun ProductLayout(
    modifier: Modifier = Modifier,
    productIds: List<Int>,
    productNames: List<String>,
    productDates: List<String>,
    productTags: Map<Int, List<String>>,
    productAmounts: List<Int>,
    onChangeAmountClick: (action: String, id: Int, amount: Int) -> Unit,
    onDeleteClick: (action: String, id: Int) -> Unit,
    onUserSearch: (text: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = modifier
            .background(background)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    )
    {
        item {
            SearchBar(
                onValueChange = onUserSearch,
                focusManager = focusManager
            )
        }
        itemsIndexed(productNames) { index, name ->
            CardItem(
                id = productIds[index],
                title = name,
                date = productDates[index],
                tags = productTags.getValue(productIds[index]),
                onChangeAmountClick = onChangeAmountClick,
                onDeleteClick = onDeleteClick,
                amount = productAmounts[index],
                onCardClick = { focusManager.clearFocus() }
            )

        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CardItem(
    modifier: Modifier = Modifier,
    id: Int,
    amount: Int,
    date: String,
    tags: List<String>,
    title: String,
    onChangeAmountClick: (action: String, id: Int, amount: Int) -> Unit,
    onDeleteClick: (action: String, id: Int) -> Unit,
    onCardClick: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onCardClick
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(ic_launcher_background),
    ) {
        Row(
            modifier = modifier
                .clip(ShapeDefaults.Small)
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Start)
                .padding(8.dp)
        ) {
            Text(
                modifier = modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleLarge,
                text = title
            )
            Spacer(modifier = modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Change Amount",
                tint = purple_500,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
                    .clickable(
                        onClick = {
                            onChangeAmountClick(UPDATE_AMOUNT, id, amount)
                        })
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = dark_red,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
                    .clickable(onClick = {
                        onDeleteClick(DELETE, id)
                    })
            )
        }
        FlowRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            tags.forEach { tag ->
                TagsChip(tags = tag)
            }
        }
        OtherInfo(
            amount = amount,
            date = date
        )
    }
}

@Composable
private fun TagsChip(
    tags: String
) {
    SuggestionChip(
        onClick = { },
        label = { Text(tags) },
    )
}

@Composable
private fun OtherInfo(
    amount: Int,
    date: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.amount),
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                text = amount.toString(),
                style = MaterialTheme.typography.labelSmall,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.date),
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                text = date,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}


@Preview(widthDp = 300, heightDp = 1010)
@Composable
private fun ProductLayoutPreview() {
    IdeaPlatformTestTaskTheme {
        ProductLayout(
            productAmounts = listOf(1, 2, 3),
            productIds = listOf(1, 2, 3),
            productNames = listOf("iPhone", "Samsung", "Honor"),
            productTags = mapOf(
                1 to listOf("Телефон", "Новый", "Распродажа"),
                2 to listOf("Телефон", "Новый"),
                3 to listOf("Телефон", "Распродажа")
            ),
            onChangeAmountClick = { _, _, _ -> },
            onDeleteClick = { _, _ -> },
            productDates = listOf("05.11.22", "05.11.22", "05.11.22"),
            onUserSearch = { },
        )
    }
}