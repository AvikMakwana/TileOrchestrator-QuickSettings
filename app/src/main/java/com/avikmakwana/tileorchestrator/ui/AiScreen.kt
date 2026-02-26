package com.avikmakwana.tileorchestrator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Message(val text: String, val isUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiScreen() {

    var input by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(listOf<Message>()) }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quick AI") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(messages) { message ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = if (message.isUser)
                            CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        else CardDefaults.cardColors()
                    ) {
                        Text(
                            text = message.text,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = { Text("Ask something...") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (input.isNotBlank()) {

                        val userMessage = Message(input, true)
                        messages = messages + userMessage

                        val prompt = input
                        input = ""

                        coroutineScope.launch {
                            simulateStreamingResponse(prompt) { chunk ->
                                messages = messages.dropLast(1) +
                                        Message(chunk, false)
                            }
                        }
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Send")
            }
        }
    }
}

suspend fun simulateStreamingResponse(
    prompt: String,
    onUpdate: (String) -> Unit
) {
    var currentText = ""
    val fakeResponse = "This is a simulated AI response for: $prompt"

    for (char in fakeResponse) {
        currentText += char
        onUpdate(currentText)
        delay(30)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAi() {
    AiScreen()
}