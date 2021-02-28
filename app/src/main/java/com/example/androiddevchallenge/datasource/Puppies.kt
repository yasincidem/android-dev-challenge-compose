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
package com.example.androiddevchallenge.datasource

val Puppies = listOf(
    Puppy(
        "1",
        "https://images.unsplash.com/photo-1591160690555-5debfba289f0?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=720&q=80",
        "Charlie",
        "Moscow \uD83C\uDDF7\uD83C\uDDFA"
    ),
    Puppy(
        "2",
        "https://images.unsplash.com/photo-1560807707-8cc77767d783?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=720&q=80",
        "Max",
        "Los Angeles \uD83C\uDDFA\uD83C\uDDF8"
    ),
    Puppy(
        "3",
        "https://images.unsplash.com/photo-1593134257782-e89567b7718a?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=720&q=80",
        "Buddy",
        "Washington \uD83C\uDDFA\uD83C\uDDF8"
    ),
    Puppy(
        "4",
        "https://images.unsplash.com/photo-1507146426996-ef05306b995a?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=720&q=80",
        "Oscar",
        "Texas \uD83C\uDDFA\uD83C\uDDF8"
    ),
    Puppy(
        "5",
        "https://images.unsplash.com/photo-1574293876203-8bded53be0f0?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=720&q=80",
        "Milo",
        "North Carolina \uD83C\uDDFA\uD83C\uDDF8"
    ),
    Puppy(
        "6",
        "https://images.unsplash.com/photo-1517849845537-4d257902454a?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=720&q=80",
        "Archie",
        "Wisconsin \uD83C\uDDFA\uD83C\uDDF8"
    ),
    Puppy(
        "7",
        "https://images.unsplash.com/photo-1519098901909-b1553a1190af?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2167&q=80",
        "Bella",
        "Michigan \uD83C\uDDFA\uD83C\uDDF8"
    ),
    Puppy(
        "8",
        "https://images.unsplash.com/photo-1504826260979-242151ee45b7?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1234&q=80",
        "Molly",
        "California \uD83C\uDDFA\uD83C\uDDF8"
    ),
    Puppy(
        "9",
        "https://images.unsplash.com/photo-1503256207526-0d5d80fa2f47?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1233&q=80",
        "Coco",
        "Pennsylvania \uD83C\uDDFA\uD83C\uDDF8"
    )
)

class Puppy(
    val id: String,
    val imageUrl: String,
    val name: String,
    val address: String
)

fun findPuppyById(puppyId: String): Puppy? {
    for (item in Puppies)
        if (item.id == puppyId)
            return item
    return null
}
