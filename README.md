# CustomAlert Library #

A simple, customizable alert dialog library for Android using Jetpack Compose. This library provides a reusable and flexible alert dialog component with customizable title, message, and button configurations.

## Features ##

- **Customizable Title and Message**: Set a custom title and message for the alert dialog.
- **Multiple Button Support**: Configure multiple buttons with custom text and actions.
- **Callback Handling**: Retrieve the selected button text from the dialog.

Note: This library is fully documented to provide guidance on usage and customization.


## Requirements ##

- **Kotlin**: 1.7 or above
- **Jetpack Compose**: Compatible with Compose-based projects
- **Minimum SDK**: Android 8.0 (API level 26)

## Installation

To include this library in your Android project, use JitPack to add it as a dependency:

### Step 1. Add the JitPack repository to your settings.gradle file : ###
    ```gradle
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
      maven { url = uri("https://jitpack.io") }
		}
	}
    ```

### Step 2. Add the dependency :
    ```gradle
    dependencies {
       implementation 'com.github.SahilSharma2710:customalertlibrary:Tag'
    }
    ```
    

## Parameters

| Parameter           | Type                             | Description                                                                                              |
|---------------------|----------------------------------|----------------------------------------------------------------------------------------------------------|
| `title`             | `String`                         | The title text to display at the top of the alert dialog.                                                |
| `message`           | `String`                         | The main message content of the alert dialog.                                                            |
| `buttons`           | `List<Pair<String, () -> Unit>>` | A list of button labels and their corresponding actions. Each button is defined as a pair of a label (`String`) and an action (`() -> Unit`). |
| `onDismissRequest`  | `() -> Unit`                     | Callback to execute when the dialog is dismissed without button interaction, such as tapping outside.     |
| `onButtonClicked`   | `(String) -> Unit`               | Callback invoked with the label of the button clicked, allowing you to capture which button was pressed. |
| `modifier`          | `Modifier`                       | Optional modifier to adjust the layout or appearance of the dialog.                                      |

## Usage


Here's an example of how to use the `CustomAlert` component in a Jetpack Compose project.

1. **Setup Button Configurations**:
    ```kotlin
    val buttonConfigs = listOf(
        Pair("First Button") { /* Action for First Button */ },
        Pair("Second Button") { /* Action for Second Button */ }
    )
    ```

2. **Implement the Custom Alert Dialog**:
    ```kotlin
    var showDialog = remember { mutableStateOf(false) }
    var selectedButtonText = remember { mutableStateOf("") }

    if (showDialog.value) {
        CustomAlert(
            title = "My Alert",
            message = "This is a customizable alert dialog message.",
            buttons = buttonConfigs,
            onDismissRequest = { showDialog.value = false },
            onButtonClicked = { text ->
                selectedButtonText.value = text // Capture selected button text
                showDialog.value = false // Dismiss dialog
            }
        )
    }
    ```

3. **Trigger the Dialog and Display Selected Text**:
    ```kotlin
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showDialog.value = true }) {
            Text("Show Custom Alert Dialog")
        }

        if (selectedButtonText.value.isNotEmpty()) {
            Text("Selected Button: ${selectedButtonText.value}")
        }
    }
    ```

### Full Example

```kotlin
@Composable
fun CustomAlertExampleScreen() {
    val showDialog = remember { mutableStateOf(false) }
    val selectedButtonText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { showDialog.value = true },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Show Custom Alert Dialog")
        }

        if (selectedButtonText.value.isNotEmpty()) {
            Text(
                text = "Selected Button: ${selectedButtonText.value}",
                fontSize = 24.sp
            )
        }
    }

    if (showDialog.value) {
        CustomAlert(
            title = "My Alert",
            message = "This is a custom alert dialog.",
            buttons = listOf(
                Pair("First Button") { /* Action for First Button */ },
                Pair("Second Button") { /* Action for Second Button */ }
            ),
            onButtonClicked = { text ->
                selectedButtonText.value = text
                showDialog.value = false
            },
            onDismissRequest = { showDialog.value = false }
        )
    }
}
