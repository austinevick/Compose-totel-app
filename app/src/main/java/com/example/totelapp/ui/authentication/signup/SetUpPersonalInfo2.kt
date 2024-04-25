package com.example.totelapp.ui.authentication.signup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.totelapp.common.Utils
import com.example.totelapp.theme.LightGrey
import com.example.totelapp.theme.PrimaryColor
import com.example.totelapp.widget.CustomAppbar

class SetUpPersonalInfo2 : Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val dateState = rememberDatePickerState()
        val millisToLocalDate = dateState.selectedDateMillis?.let {
            Utils().convertMillisToLocalDate(it)
        }
        val dateToString = millisToLocalDate?.let {
            Utils().dateToString(millisToLocalDate)
        } ?: "Choose Date"
        val showDialog = remember { mutableStateOf(false) }
        val gender = remember {
            mutableStateListOf("Male", "Female", "Prefer not to say")
        }
        val selectedGender = remember {
            mutableStateOf("")
        }
        val dateOfBirth = remember {
            mutableStateOf(dateToString)
        }


        Scaffold(
            topBar = {
                CustomAppbar("Set Up Basics")
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Kindly tell us about your self for better connection.")
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Date of birth")
               Button(
                   shape = RoundedCornerShape(8.dp),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = LightGrey
                   ),
                   onClick = {showDialog.value=true }) {
                   Text(text = dateOfBirth.value, color = Color.Black)
               }

                if (showDialog.value) {
                    DatePickerDialog(
                        onDismissRequest = { showDialog.value = false },
                        confirmButton = {
                            Button(
                                onClick = { showDialog.value = false }
                            ) {
                                Text(text = "OK")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDialog.value = false }
                            ) {
                                Text(text = "Cancel")
                            }
                        }
                    ) {
                        DatePicker(
                            state = dateState,
                            modifier = Modifier.weight(1f),
                            showModeToggle = true)

                    }}

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Select Gender")
                Row {
                    List(gender.size) { i ->
                        val isSelected = selectedGender.value == gender[i]
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) PrimaryColor else LightGrey
                            ),
                            modifier = Modifier
                                .padding(end = 5.dp),
                            onClick = {
                                selectedGender.value = gender[i]
                            }) {
                            Text(
                                text = gender[i],
                                fontSize = 12.sp,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        }
                    }
                }

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    onClick = { navigator?.push(SetupProfilePic()) }) {
                    Text(text = "Continue")
                }

            }
        }
    }
}