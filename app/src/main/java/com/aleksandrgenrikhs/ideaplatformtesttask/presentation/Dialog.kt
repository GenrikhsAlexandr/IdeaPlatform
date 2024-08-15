package com.aleksandrgenrikhs.ideaplatformtesttask.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.aleksandrgenrikhs.ideaplatformtesttask.R
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.background
import com.aleksandrgenrikhs.ideaplatformtesttask.ui.theme.purple_500

@Composable
fun Dialog(
    action: String,
    productId: Int,
    productAmount: Int,
    onCancel: () -> Unit,
    onAcceptAmount: (id: Int, amount: Int) -> Unit,
    onDelete: (id: Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    var newAmount by remember { mutableIntStateOf(productAmount) }

    Dialog(
        onDismissRequest = {}
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(ShapeDefaults.Medium)
                .background(background)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = (
                            if (action == UPDATE_AMOUNT) Icons.Default.Settings
                            else Icons.Default.Warning),
                    contentDescription = ""
                )
                Text(
                    text = if (action == UPDATE_AMOUNT) stringResource(id = R.string.titleUpdateAmount)
                    else stringResource(id = R.string.titleDelete),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                if (action == DELETE) {
                    Text(
                        text = stringResource(id = R.string.textDelete),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                } else
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        IconButton(
                            onClick = { newAmount-- },
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(8.dp)
                                .border(3.dp, purple_500, shape = CircleShape)
                                .weight(1f),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_minus),
                                contentDescription = ""
                            )
                        }
                        Text(
                            text = newAmount.toString(),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterVertically),
                            color = purple_500

                        )
                        IconButton(
                            onClick = { newAmount++ },
                            modifier = Modifier
                                .padding(8.dp)
                                .wrapContentSize()
                                .border(3.dp, purple_500, shape = CircleShape)
                                .weight(1f)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_plus),
                                contentDescription = ""
                            )
                        }
                    }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = {
                            onCancel()
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .wrapContentSize()
                    ) {
                        Text(
                            text = if (action == UPDATE_AMOUNT) stringResource(id = R.string.cancel)
                            else stringResource(id = R.string.no),
                            style = MaterialTheme.typography.bodyLarge,
                            color = purple_500,
                        )
                    }
                    TextButton(
                        onClick = {
                            if (action == UPDATE_AMOUNT)
                                onAcceptAmount(productId, newAmount)
                            else onDelete(productId)
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .wrapContentSize()
                    ) {
                        Text(
                            text = if (action == UPDATE_AMOUNT) stringResource(id = R.string.accept)
                            else stringResource(id = R.string.yes),
                            style = MaterialTheme.typography.bodyLarge,
                            color = purple_500,
                        )
                    }
                }
            }
        }
    }
}