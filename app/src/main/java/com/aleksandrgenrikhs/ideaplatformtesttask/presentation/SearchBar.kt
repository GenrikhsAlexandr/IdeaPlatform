package com.aleksandrgenrikhs.ideaplatformtesttask.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aleksandrgenrikhs.ideaplatformtesttask.R
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.ic_launcher_background


@Composable
fun SearchBar(
    onValueChange: (text: String) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        label = { Text(text = stringResource(R.string.search)) },
        value = text,
        onValueChange = { newText ->
            text = newText
            onValueChange(text)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(
                    onClick = {
                        text = ""
                        onValueChange(text)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                )
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = ic_launcher_background,
            focusedContainerColor = ic_launcher_background
        ),
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                if (!focusState.isFocused) {
                    focusManager.clearFocus()
                }
            },
    )
}