package com.example.customalertlibrary

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * CustomAlert is a composable function that displays an alert dialog with a customizable title,
 * message, and a dynamic list of buttons.
 *
 * @param title The title text to display at the top of the dialog.
 * @param message The message text to display in the body of the dialog.
 * @param buttons A list of pairs, each containing the button text and the action to execute
 *        when the button is clicked.
 *        - The first value in each pair is the button label.
 *        - The second value in each pair is a lambda function to be executed on button click.
 * @param onDismissRequest A lambda function triggered when the dialog is dismissed.
 *        - Typically used to hide or close the dialog when the user clicks outside it.
 * @param onButtonClicked A callback triggered when a button is clicked, passing the button's label text.
 *        - Allows the calling composable to take specific action based on which button was clicked.
 * @param modifier Optional modifier to adjust the dialog's appearance and layout properties.
 */
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
                Button(onClick = {
                    button.second() // Execute the action associated with the button
                    onButtonClicked(button.first) // Trigger the onButtonClicked callback with button label
                }) {
                    Text(button.first) // Display the button label
                }
            }
        },
        modifier = modifier
    )
}
