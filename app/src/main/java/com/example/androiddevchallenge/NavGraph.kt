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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.MainDestinations.PUPPY_ID
import com.example.androiddevchallenge.MainDestinations.PUPPY_LIST_ROUTE
import com.example.androiddevchallenge.MainDestinations.PUPPY_ROUTE

object MainDestinations {
    const val PUPPY_LIST_ROUTE = "puppy_list"
    const val PUPPY_ROUTE = "puppy"
    const val PUPPY_ID = "puppyId"
}

@Composable
fun NavGraph(startDestination: String = PUPPY_LIST_ROUTE) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(PUPPY_LIST_ROUTE) { PuppyListScreen(clickPuppy = actions.clickPuppy) }
        composable(
            "$PUPPY_ROUTE/{$PUPPY_ID}",
            arguments = listOf(navArgument(PUPPY_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            PuppyScreen(arguments.getString(PUPPY_ID) ?: "", navController)
        }
    }
}

class MainActions(navController: NavHostController) {
    val clickPuppy: (String) -> Unit = {
        navController.navigate("$PUPPY_ROUTE/$it")
    }
}
