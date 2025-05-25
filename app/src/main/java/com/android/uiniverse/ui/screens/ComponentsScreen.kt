package com.android.uiniverse.ui.screens

import android.app.Activity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.uiniverse.R
import com.android.uiniverse.ui.MainActivity


@Composable
fun DisplayContent(onItemClicked: () -> Unit) {

    LazyColumn(
        modifier = Modifier.fillMaxSize() .padding(vertical = 180.dp) .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CardContent(modifier = Modifier, stringResource(R.string.dialogs), onClick = onItemClicked)
        }

        item {
            CardContent(
                modifier = Modifier,
                stringResource(R.string.toast),
                onClick = onItemClicked)
        }
    }
}


@Composable
fun CardContent(modifier: Modifier, componentName: String, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .width(240.dp)
            .height(80.dp)
            .fillMaxSize()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )

    ) {
        Column(
            modifier = modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = componentName, color = Color.Black)
        }
    }

}


@Preview
@Composable
fun AppBarPreview() {
    DisplayContent({})
}



@Composable
fun ScrollContent(innerPadding: PaddingValues, modifier: Modifier) {

    val context = Activity()
    val activity = MainActivity()
    val range = 1..2

    //todo -> How to create space between different items in the list ?
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(range.count()) { index ->
            ElevatedCard(
                onClick = {
                    mainViewModel.permissionDialog(context, activity)
                },
                modifier = modifier
                    .width(240.dp)
                    .height(80.dp)
                    .fillMaxSize()
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {

                val adaptiveInfo = currentWindowAdaptiveInfo()
                val displaySize =
                    "${adaptiveInfo.windowSizeClass.windowWidthSizeClass}\n" + "${adaptiveInfo.windowSizeClass.windowHeightSizeClass}"

                CardContent(
                    modifier,
                    displaySize,
                    {}
                ) /*todo how to set dynamic name for every card content*/
            }
        }
    }

}

@Composable
fun DisplaySize(size: String) {
    Text(
        text = size,
        color = Color.Magenta,
        modifier = Modifier.padding(WindowInsets.safeDrawing.asPaddingValues())
    )
}