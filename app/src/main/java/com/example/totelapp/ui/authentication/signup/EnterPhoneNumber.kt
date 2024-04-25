package com.example.totelapp.ui.authentication.signup

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.totelapp.R
import com.example.totelapp.theme.LightGrey
import com.example.totelapp.theme.PrimaryColor
import com.example.totelapp.ui.authentication.SignInActivity
import com.example.totelapp.widget.CustomAppbar
import com.example.totelapp.widget.CustomTextField

class EnterPhoneNumber : Screen {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val ctx = LocalContext.current
        val navigator = LocalNavigator.current
        val phoneNumber = remember {
            mutableStateOf("")
        }
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current


        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }


        Scaffold(
            topBar = {
                CustomAppbar("Let’s start with phone")
            },
            bottomBar = {
                OutlinedButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                    onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        modifier = Modifier.size(22.dp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Continue with Google", color = Color.Black)
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Row {
                    Button(shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(55.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LightGrey
                        ),
                        onClick = { }) {
                        Image(
                            painter = painterResource(id = R.drawable.flag),
                            modifier = Modifier.size(22.dp),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "(+1)", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(8.dp))

                    CustomTextField(
                        value = phoneNumber.value,
                        onValueChange = { phoneNumber.value = it },
                        placeholder =  "Enter your mobile phone",
                        keyboardType = KeyboardType.Phone,
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                if (it.isFocused) {
                                    keyboardController?.show()
                                }
                            })
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(modifier = Modifier
                    .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    enabled = phoneNumber.value.isNotEmpty(),
                    onClick = {
                        if (Patterns.PHONE.matcher(phoneNumber.value).matches()) {
                            navigator?.push(VerifyPhoneNumber(phoneNumber.value))
                        } else {
                            Toast.makeText(ctx, "Phone Number is invalid", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {

                    Text(text = "Continue")
                }
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    "By continuing, you acknowledge that you have read and understood, and agree to Totel’s Terms of Service and Privacy Policy.",
                    fontSize = 14.sp
                )
            }
        }


    }

}