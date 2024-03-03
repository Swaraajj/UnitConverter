package com.example.unitconverter

import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inExpanded by remember { mutableStateOf(false) }
    var outExpanded by remember { mutableStateOf(false) }
    val convertFactor = remember { mutableStateOf(1.0) }
    val outConvertFactor = remember { mutableStateOf(1.0) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * convertFactor.value * 100 / outConvertFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
            )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
        },
            label = { Text(text = "Enter Value")}
            )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input Box
            Box {
                Button(onClick = { inExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                }
                DropdownMenu(expanded = inExpanded, onDismissRequest = { inExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters")},
                        onClick = {
                            inputUnit = "Centimeters"
                            convertFactor.value = 0.01
                            convertUnits()
                            inExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            inputUnit = "Meters"
                            convertFactor.value = 1.0
                            convertUnits()
                            inExpanded = false

                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            inputUnit = "Feet"
                            convertFactor.value = 0.3048
                            convertUnits()
                            inExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters")},
                        onClick = {
                            inputUnit = "Millimeters"
                            convertFactor.value = 0.001
                            convertUnits()
                            inExpanded = false
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                Button(onClick = { outExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = outExpanded, onDismissRequest = { outExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters")},
                        onClick = {
                            outputUnit = "Centimeters"
                            outConvertFactor.value = 0.01
                            convertUnits()
                            outExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            outputUnit = "Meters"
                            outConvertFactor.value = 1.0
                            convertUnits()
                            outExpanded = false

                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            outputUnit = "Feet"
                            outConvertFactor.value = 0.3048
                            convertUnits()
                            outExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters")},
                        onClick = {
                            outputUnit = "Millimeters"
                            outConvertFactor.value = 0.001
                            convertUnits()
                            outExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
            )
    }
}

@Preview(showSystemUi = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}