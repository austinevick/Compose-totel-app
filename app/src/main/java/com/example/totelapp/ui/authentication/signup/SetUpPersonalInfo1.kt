package com.example.totelapp.ui.authentication.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.totelapp.theme.PrimaryColor
import com.example.totelapp.widget.CustomAppbar
import com.example.totelapp.widget.CustomTextField

class SetUpPersonalInfo1:Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val firstName = remember {
            mutableStateOf("")
        }
        val lastName = remember {
            mutableStateOf("")
        }

        Scaffold(
            topBar = {
                CustomAppbar("Set Up Basics")
            }
        ) {paddingValues->
            Column( modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)) {
                Text(text = "Kindly tell us about your self for better connection.")
                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(value = firstName.value,
                    onValueChange = {firstName.value=it},
                    placeholder = "First name",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    )
                Spacer(modifier = Modifier.height(10.dp))
                CustomTextField(value = lastName.value,
                    onValueChange = {lastName.value=it},
                    placeholder = "Last name",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                )
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    onClick = { navigator?.push(SetUpPersonalInfo2()) }) {
                    Text(text = "Continue")
                }
            }
        }


    }

}