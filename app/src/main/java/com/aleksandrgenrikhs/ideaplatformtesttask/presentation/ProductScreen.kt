package com.aleksandrgenrikhs.ideaplatformtesttask.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var dialogAction by rememberSaveable { mutableStateOf("") }
    var dialogProductId by rememberSaveable { mutableIntStateOf(0) }
    var dialogProductAmount by rememberSaveable { mutableIntStateOf(0) }

    with(uiState) {
        ProductLayout(
            modifier = modifier,
            productIds = id,
            productNames = name,
            productDates = time,
            productTags = tags,
            productAmounts = amount,
            onChangeAmountClick = { action, id, amount ->
                showDialog = true
                dialogAction = action
                dialogProductId = id
                dialogProductAmount = amount
            },
            onDeleteClick = { action, id ->
                showDialog = true
                dialogAction = action
                dialogProductId = id
            },
            onUserSearch = (viewModel::onSearchQuery),
        )
    }
    if (showDialog) {
        Dialog(
            action = dialogAction,
            productId = dialogProductId,
            productAmount = dialogProductAmount,
            onCancel = {},
            onDelete = (viewModel::deleteProduct),
            onDismissRequest = { showDialog = false },
            onAcceptAmount = (viewModel::updateAmount)
        )
    }
}