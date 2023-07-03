package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme


class activityReceipe : ComponentActivity() {
    val recipeApp = RecipeApp()

    var recipes1 = ArrayList<Recipe>()
    var txtPath: String = ""

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    var expanded by remember {
                        mutableStateOf(false)
                    }
                    var totalRecipeType = ArrayList<String>()
                    val recipeTypes = resources.getStringArray(R.array.recipe_types)
                    for (recipeType in recipeTypes) {
                        totalRecipeType.add(recipeType)
                    }
                    var selecteddrinks by remember {
                        mutableStateOf(totalRecipeType[0])
                    }
                    Column {
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(70.dp)
                                .padding(10.dp)
                        ) {
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded }) {
                                TextField(
                                    value = selecteddrinks,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = expanded
                                        )
                                    },
                                    textStyle = TextStyle.Default.copy(fontSize = 10.sp)
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }) {
                                    totalRecipeType?.forEach {
                                        DropdownMenuItem(
                                            onClick = {
                                                selecteddrinks = it
                                                expanded = false
                                            }) {
                                            Text(text = it)
                                        }
                                    }
                                }

                            }
                        }

                        Spacer(modifier = Modifier
                            .width(16.dp)
                            .height(20.dp))
                        var txtName by rememberSaveable { mutableStateOf("") }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Name: ")
                            TextField(
                                value = txtName,
                                onValueChange = {
                                    txtName = it
                                },
                                label = { Text("Name") }
                            )
                        }
                        Spacer(modifier = Modifier
                            .width(16.dp)
                            .height(20.dp))
                        var txtIngredient by rememberSaveable { mutableStateOf("") }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Ingredient: ")
                            TextField(
                                value = txtIngredient,
                                onValueChange = {
                                    txtIngredient = it
                                },
                                label = { Text("Ingredient") }
                            )
                        }
                        Spacer(modifier = Modifier
                            .width(16.dp)
                            .height(20.dp))
                        var txtStep by rememberSaveable { mutableStateOf("") }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Steps: ")
                            TextField(
                                value = txtStep,
                                onValueChange = {
                                    txtStep = it
                                },
                                label = { Text("Steps") }
                            )
                        }
                        Spacer(modifier = Modifier
                            .width(16.dp)
                            .height(20.dp))

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                            ) {
                                Button(modifier = Modifier.padding(20.dp),
                                    onClick = {

                                        recipeApp.addNewRecipe(
                                            txtName,
                                            selecteddrinks,
                                            txtIngredient,
                                            txtStep
                                        )

                                        DataHolder.arrayList.add(
                                            Recipe(
                                                txtName,
                                                selecteddrinks,
                                                txtIngredient,
                                                txtStep
                                            )
                                        )

                                        txtName = ""
                                        selecteddrinks = selecteddrinks
                                        txtIngredient = ""
                                        txtStep = ""

                                    }

                                ) {
                                    Text(text = "Insert")
                                }
                                Button(modifier = Modifier.padding(20.dp),

                                    onClick = {
                                        val intent = Intent(
                                            this@activityReceipe,
                                            activityDisplayRecipe::class.java
                                        )
                                        startActivity(intent)
                                    }
                                ) {
                                    Text(text = "Display")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

