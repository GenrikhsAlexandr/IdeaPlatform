package com.aleksandrgenrikhs.ideaplatformtesttask.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aleksandrgenrikhs.ideaplatformtesttask.R
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.light_blue
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = light_blue,
                    titleContentColor = text,
                ),
                title = {
                    Text(stringResource(id = R.string.listProducts))
                }
            )
        },
    ) { innerPadding ->
        ProductScreen(
            modifier = modifier
            .padding(innerPadding))
    }
}