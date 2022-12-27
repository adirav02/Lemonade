package com.adir.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adir.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp()
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeTheme() {
        TitleAndImage(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun TitleAndImage(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var clickCounts = 1
    var maxClicksToSqueeze = (2..4).random()
    var image = R.drawable.lemon_tree
    var title = stringResource(id = R.string.step_1)
    when (step) {
        1 -> {
            image = R.drawable.lemon_tree
            title = stringResource(id = R.string.step_1)
        }
        2 -> {
            image = R.drawable.lemon_squeeze
            title = stringResource(id = R.string.step_2)
        }
        3 -> {
            image = R.drawable.lemon_drink
            title = stringResource(id = R.string.step_3)
        }
        else -> {
            image = R.drawable.lemon_restart
            title = stringResource(id = R.string.step_4)
        }
    }

    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.size(12.dp))
        Image(
            painter = painterResource(image),
            contentDescription = step.toString(),
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.Blue
                )
                .clickable(
                    onClick = {
                        when (step) {
                            1 -> {
                                step++
                            }
                            2 -> {
                                clickCounts++
                                if (clickCounts == maxClicksToSqueeze)
                                    step++
                            }
                            3 -> {
                                step++
                            }
                            else -> {
                                step = 1
                            }
                        }
                    },
                )
        )

    }
}