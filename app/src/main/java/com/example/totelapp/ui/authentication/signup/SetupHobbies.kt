package com.example.totelapp.ui.authentication.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.totelapp.theme.PrimaryColor
import com.example.totelapp.widget.CustomAppbar
import com.example.totelapp.widget.FlowRow

class SetupHobbies : Screen {
    @Composable
    override fun Content() {
        val hobbies = remember {
            mutableStateListOf(
                "Cooking", "Learning Languages",
                "Music", "Gym", "Photography", "Travel", "Art", "Swimming",
                "Playing Games", "Sports", "Movie", "Programming", "Technology", "Dance"
            )
        }
        val selectedHobbies = remember {
            mutableStateListOf<String>()
        }

        Scaffold(
            topBar = { CustomAppbar(title = "Hobbies $ Interest") }
        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "We want to know you better, Choose maximum of 5.")
                Spacer(modifier = Modifier.height(20.dp))

                FlowRow {
                    List(hobbies.size) { i ->
                        val isSelected = selectedHobbies.contains(hobbies[i])
                        FilterChip(selected = isSelected,
                            colors = InputChipDefaults.inputChipColors(
                                selectedContainerColor = if (isSelected) PrimaryColor else Color.White
                            ),
                            onClick = {
                                if (selectedHobbies.size ==5){
                                    selectedHobbies.remove(hobbies[i])
                                    return@FilterChip
                                }
                                if (selectedHobbies.contains(hobbies[i])) {
                                    selectedHobbies.remove(hobbies[i])
                                } else {
                                    selectedHobbies.add(hobbies[i])
                                }
                            },
                            label = {
                                Text(
                                    text = hobbies[i],
                                    color = if (isSelected) Color.White else Color.Black
                                )
                            })
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    enabled = selectedHobbies.size ==5,
                    onClick = { }) {
                    Text(text = "Continue")
                }
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { }) {
                    Text(text = "Skip", color = Color.Black)
                }
            }
        }

    }
}
