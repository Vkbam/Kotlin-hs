package com.example.mandelbrotgui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import org.jetbrains.compose.resources.painterResource

import mandelbrotgui.shared.generated.resources.Res
import mandelbrotgui.shared.generated.resources.compose_multiplatform
import kotlin.system.exitProcess

@Composable
@Preview
fun App() {
    MaterialTheme (
        colorScheme = lightColorScheme(
            primaryContainer = Color.LightGray
        )
    ){
        var showContent by remember { mutableStateOf(false) }
        var text by remember {mutableStateOf("")}
        var showPopup by remember { mutableStateOf(false) }


        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = text,
                onValueChange = { newText -> text = newText },
                modifier = Modifier.background(color = Color.Gray),
                enabled = true,
                readOnly = false,
                placeholder = { Text("Enter Expression") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (text == "exit")
                        {
                            exitProcess(0)
                        } else {
                            showPopup = true
                        }
                    }
                )
            )

            if (showPopup){
                Popup(
                    alignment = Alignment.Center,
                    onDismissRequest = { showContent = false },
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shadowElevation = 6.dp
                    ){
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(text = parse(text).toString())
                            Spacer(modifier = Modifier.height(4.dp))
                            Button(onClick = {showPopup = false}) {
                                Text("Close")
                            }
                        }
                    }
                }
            }

        }
    }
}