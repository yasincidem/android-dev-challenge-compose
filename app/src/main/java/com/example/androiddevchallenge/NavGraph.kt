package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
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