package com.example.customalertkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customalertlibrary.CustomAlert


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomAlertExampleScreen()
        }
    }
}

@Composable
fun CustomAlertExampleScreen() {
    // State to control dialog visibility and store selected value
    val showDialog = remember { mutableStateOf(false) }
    val selectedButtonText = remember { mutableStateOf("") }

    // Main screen layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button to trigger the dialog
        Button(
            onClick = { showDialog.value = true },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Show Custom Alert Dialog")
        }

        // Display selected button text
        if (selectedButtonText.value.isNotEmpty()) {
            Text(
                text = "Selected Button: ${selectedButtonText.value}",
                fontSize = 24.sp
            )
        }
    }

    // Show the custom alert dialog if showDialog is true
    if (showDialog.value) {
        CustomAlert(
            title = "My Alert",
            message = "This is a custom alert dialog.",
            buttons = listOf(
                Pair("First Button") {  },
                Pair("Second Button") {  },
            ),
            onButtonClicked = { text ->
                selectedButtonText.value = text // Update state with button text
                showDialog.value = false // Dismiss the dialog
            },
            onDismissRequest = { showDialog.value = false },

            )
    }
}

