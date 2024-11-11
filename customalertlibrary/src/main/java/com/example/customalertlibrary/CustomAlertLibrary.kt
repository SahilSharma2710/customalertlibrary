package com.example.customalertlibrary

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomAlert(
    title: String,
    message: String,
    buttons: List<Pair<String, () -> Unit>>,
    onDismissRequest: () -> Unit,
    onButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            buttons.forEach { button ->
                TextButton(onClick = {
                    button.second
                    onButtonClicked(button.first)
                }) {
                    Text(button.first)
                }
            }
        },
        modifier = modifier
    )

}