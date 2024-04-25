package com.example.totelapp.ui.authentication.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.totelapp.R
import com.example.totelapp.theme.PrimaryColor
import com.example.totelapp.widget.CustomAppbar
import com.example.totelapp.widget.CustomTextField

class CreatePassword : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val password = remember {
            mutableStateOf("")
        }
        val confirmPassword = remember {
            mutableStateOf("")
        }
        val showPassword = remember {
            mutableStateOf(false)
        }

        Scaffold(
            topBar = {
                CustomAppbar("Create Password")
            }
        ) { paddingValues ->

            val passwordVisualTransformation =
                if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation()


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Enter at least 8-digits")
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    placeholder = "Enter Password",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    keyboardType = KeyboardType.Password,
                    visualTransformation = passwordVisualTransformation,
                    trailingIcon = { IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        if (showPassword.value)
                            Icon(
                                painterResource(id = R.drawable.outline_visibility),
                                contentDescription = null
                            )
                        else
                            Icon(
                                painterResource(id = R.drawable.outline_visibility_off),
                                contentDescription = null
                            )
                    } },

                )
                Spacer(modifier = Modifier.height(10.dp))

                CustomTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    placeholder = "Confirm Password",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    keyboardType = KeyboardType.Password,
                    trailingIcon = { IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        if (showPassword.value)
                            Icon(
                                painterResource(id = R.drawable.outline_visibility),
                                contentDescription = null
                            )
                        else
                            Icon(
                                painterResource(id = R.drawable.outline_visibility_off),
                                contentDescription = null
                            )
                    } },
                    visualTransformation = passwordVisualTransformation
                )

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    enabled = true,
                    onClick = { navigator?.push(SetUpPersonalInfo1()) }) {
                    Text(text = "Continue")
                }
            }
        }

    }
}