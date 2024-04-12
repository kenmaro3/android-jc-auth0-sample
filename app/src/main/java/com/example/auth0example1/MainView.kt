package com.example.auth0example1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.auth0example1.ViewModel.MainViewModel

@Composable
fun MainView(
    viewModel: MainViewModel
) {

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val title = if (viewModel.userIsAuthenticated) {
            "User Logged in"
        } else {
            if (viewModel.appJustLaunched) {
                "Welcome, please login"
            } else {
                "User Logged out"
            }
        }

        Text(
            text = title,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            )
        )
        Spacer(
            modifier = Modifier.width(10.dp),
        )

        if (viewModel.userIsAuthenticated){

            UserInfoRow(
                label = "user_info_row_name_label",
                //value = "Name goes here",
                value = viewModel.user.name,
            )
            UserInfoRow(
                label = "user_info_row_email_label",
                //value = "Email goes here",
                value = viewModel.user.email,
            )

            UserPicture(
                //url = "https://images.ctfassets.net/23aumh6u8s0i/5hHkO5DxWMPxDjc2QZLXYf/403128092dedc8eb3395314b1d3545ad/icon-user.png",
                url = viewModel.user.picture,
                description = "Description goes here",
            )
        }

        val buttonText: String
        val onClickAction: () -> Unit
        if (viewModel.userIsAuthenticated) {
            buttonText = "Log out"
            onClickAction = {
                viewModel.logout()
            }
        } else {
            buttonText = "Log in"
            onClickAction = {
                viewModel.login()
            }
        }

        LogButton(
            text = buttonText,
            onClick = onClickAction,
        )
    }

}


