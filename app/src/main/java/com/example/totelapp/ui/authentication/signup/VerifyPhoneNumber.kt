package com.example.totelapp.ui.authentication.signup

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.totelapp.R
import com.example.totelapp.theme.LightGrey
import com.example.totelapp.theme.PrimaryColor
import com.example.totelapp.widget.CustomAppbar
import com.example.totelapp.widget.OtpInputField
import kotlinx.coroutines.delay

data class VerifyPhoneNumber(val phoneNumber: String) : Screen {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current
        val otpValue = remember { mutableStateOf("") }
        val isOtpFilled = remember { mutableStateOf(false) }
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current
        val timer = remember { mutableIntStateOf(60) }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        Scaffold(
            topBar = {
               CustomAppbar("Phone Verification")
            }) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                
                Row {
                    Text(text = "Enter the 6-digits sent to")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = phoneNumber, color = PrimaryColor)
                }
                OtpInputField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 48.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            if (it.isFocused) {
                                keyboardController?.show()
                            }
                        },
                    otpText = otpValue.value,
                    shouldCursorBlink = true,
                    onOtpModified = { value, otpFilled ->
                        otpValue.value = value
                        isOtpFilled.value = otpFilled
                        if (otpFilled) {
                            keyboardController?.hide()
                        }
                    })
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(modifier = Modifier.height(36.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Resend Code",
                        fontSize = 12.sp,
                        color = Color.Black)
                }
                Button(modifier = Modifier
                    .fillMaxWidth().padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    enabled = isOtpFilled.value,
                    onClick = {navigator?.push(CreatePassword())}) {
                    Text(text = "Continue")
                }
            }
        }
    }
}