package com.example.totelapp.ui.authentication.signup

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil.compose.AsyncImage
import com.example.totelapp.R
import com.example.totelapp.theme.PrimaryColor
import com.example.totelapp.widget.CustomAppbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration

class SetupProfilePic : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val showBottomSheet = remember {
            mutableStateOf(false)
        }
        val selectedImages = remember {
            mutableStateOf<Uri?>(Uri.EMPTY)
        }
        val photoLauncher = rememberLauncherForActivityResult(contract =
        ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImages.value = uri })

        val cameraLauncher = rememberLauncherForActivityResult(contract =
        ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImages.value = uri })
val scope = rememberCoroutineScope()

        Scaffold(
            topBar = { CustomAppbar(title = "Set up profile picture") }
        ) { padding ->
            val isNotEmpty = selectedImages.value?.path?.isNotEmpty() == true
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Kindly tell us about your self for better connection.")
                Spacer(modifier = Modifier.height(20.dp))
                if (isNotEmpty) AsyncImage(
                    model = selectedImages.value,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally)
                ) else Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    onClick = { showBottomSheet.value = true }) {
                    Text(text = "Choose a photo")
                }
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navigator?.push(SetupHobbies()) }) {
                    Text(text =if (isNotEmpty) "Continue" else "Skip", color = Color.Black)
                }
                if (showBottomSheet.value) ModalBottomSheet(
                    containerColor = Color.White,
                    onDismissRequest = { showBottomSheet.value = false }) {
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        onClick = {

                        }) {
                        Text(text = "Take Photo", color = Color.Black)
                    }
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        onClick = {
                            photoLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                ))
                            scope.launch {
                            delay(2000)
                            showBottomSheet.value=false
                            }

                        }) {
                        Text(text = "Add from Gallery", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                }
            }
        }
    }
}