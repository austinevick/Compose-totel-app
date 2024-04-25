package com.example.totelapp.ui.onboarding

import android.app.Activity
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.totelapp.R
import com.example.totelapp.ui.authentication.SignInActivity
import com.example.totelapp.ui.authentication.signup.EnterPhoneNumber

class OnboardingActivity : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val pagerState = rememberPagerState(pageCount = { onboardingData.size })

       // (LocalView.current.context as Activity).window.sta

        Scaffold {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(it)
            ) {
                HorizontalPager(state = pagerState) { page ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = onboardingData[page].image),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = onboardingData[page].title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = onboardingData[page].desc,
                            fontSize = 14.sp, color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.DarkGray
                            else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .background(color, CircleShape)
                                .size(8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    onClick = { navigator?.push(EnterPhoneNumber()) }) {
                    Text(text = "Create an account", color = Color.Black)
                }
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = { navigator?.push(SignInActivity()) }) {
                    Text(text = "Sign In")
                }
            }
        }
    }
}

val onboardingData = listOf(
    OnboardingPage(
        title = "Shared living space",
        desc = "What fun could we have more than having roommate with similar interest.",
        R.drawable.img3
    ),
    OnboardingPage(
        title = "Find places around you",
        desc = "You can find places and stay with hotels and home-stays ranked by travellers.",
        R.drawable.img1
    ),
    OnboardingPage(
        title = "Shared living space",
        desc = "What fun could we have more than having roommate with similar interest.",
        R.drawable.img2
    )
)


data class OnboardingPage(
    val title: String,
    val desc: String,
    @DrawableRes val image: Int
)