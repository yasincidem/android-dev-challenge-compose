/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.datasource.Puppies
import com.example.androiddevchallenge.datasource.Puppy
import com.example.androiddevchallenge.datasource.findPuppyById
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    NavGraph()
}

@Composable
fun PuppyListScreen(clickPuppy: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Puppies")
                },
                backgroundColor = if (MaterialTheme.colors.isLight) Color.White else Color.Black,
                contentColor = MaterialTheme.colors.onBackground,
            )
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            List(
                list = Puppies,
                clickPuppy = clickPuppy
            )
        }
    }
}

@Composable
fun PuppyScreen(puppyId: String, navController: NavController) {
    val puppy = findPuppyById(puppyId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = puppy?.name ?: "")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                backgroundColor = if (MaterialTheme.colors.isLight) Color.White else Color.Black,
                contentColor = MaterialTheme.colors.onBackground,
            )
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Card(
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier.fillMaxHeight()
            ) {
                Column {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        CoilImage(
                            data = puppy?.imageUrl ?: "",
                            contentScale = ContentScale.Crop,
                            contentDescription = puppy?.name
                        )
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(all = 16.dp)
                        ) {
                            Text(text = puppy?.address ?: "")
                        }
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                        border = null,
                    ) {
                        Text(text = "Adopt ${puppy?.name}")
                    }
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        Text(
                            text = stringResource(id = R.string.details_text),
                            modifier = Modifier.padding(all = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun List(list: List<Puppy>, clickPuppy: (String) -> Unit) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    Box {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = list,
                itemContent = { item ->
                    ListItem(item = item, clickPuppy = clickPuppy)
                }
            )
        }
        val showButton = listState.firstVisibleItemIndex > 0

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier.padding(all = 16.dp)
            ) {
                Icon(Icons.Filled.ArrowDropUp, "Up")
            }
        }
    }
}

@Composable
fun ListItem(item: Puppy, clickPuppy: (String) -> Unit) {
    Card(
        modifier = Modifier.padding(all = 16.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier.clickable(
                onClick = {
                    clickPuppy(item.id)
                }
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.padding(all = 16.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.body1
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                CoilImage(
                    data = item.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = "My content description"
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
