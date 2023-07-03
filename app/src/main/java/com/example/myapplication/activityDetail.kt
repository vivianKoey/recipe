package com.example.myapplication


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import com.example.myapplication.ui.theme.MyApplicationTheme



class activityDetail : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column {
                        var recipeList = ArrayList<Recipe>()
                        var recipeList2 = ArrayList<Recipe>()
                        var selectedName: String = ""
                        var selectedType: String = ""
                        var selectedIngredients: String = ""
                        var selectedInstructions: String = ""

                        recipeList = intent.getSerializableExtra("recipeList2") as ArrayList<Recipe>

                        for (recipe in recipeList) {
                            selectedName = recipe.name
                            selectedType = recipe.type
                            selectedIngredients = recipe.instructions
                            selectedInstructions = recipe.ingredients

                        }

                        var txtUsername by rememberSaveable { mutableStateOf(selectedName) }
                        var txtType by rememberSaveable { mutableStateOf(selecteddrinks) }
                        var txtIngredients by rememberSaveable { mutableStateOf(selectedIngredients) }
                        var txtInstructions by rememberSaveable { mutableStateOf(selectedInstructions) }

                        Toolbar(
                            title = "Detail Recipe",
                            navigationIcon = Icons.Default.ArrowBack,
                            onNavigationIconClick = {
                                val intent =
                                    Intent(this@activityDetail, activityDisplayRecipe::class.java)
                                startActivity(intent)
                            })

                        Spacer(
                            modifier = Modifier
                                .width(16.dp)
                                .height(20.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Name: ")
                                TextField(
                                    value = txtUsername,
                                    onValueChange = {
                                        txtUsername = it
                                    },
                                    label = { Text("Name") }
                                )
                        }
                        Spacer(
                            modifier = Modifier
                                .width(16.dp)
                                .height(20.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Type: ")

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
                                        value = selectedType,
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
                        }

                        Spacer(
                            modifier = Modifier
                                .width(16.dp)
                                .height(20.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Ingredients: ")
                                TextField(
                                    value = txtIngredients,
                                    onValueChange = {
                                        txtIngredients = it
                                    },
                                    label = { Text("Ingredients") }
                                )
                        }

                        Spacer(
                            modifier = Modifier
                                .width(16.dp)
                                .height(20.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Instructions: ")
                                TextField(
                                    value = txtInstructions,
                                    onValueChange = {
                                        txtInstructions = it
                                    },
                                    label = { Text("Instructions") }
                                )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(modifier = Modifier.padding(20.dp),

                                onClick = {
                                    val arrayList = DataHolder.arrayList

                                    DataHolder.arrayList.remove(
                                        Recipe(
                                            selectedName,
                                            selectedType,
                                            selectedIngredients,
                                            selectedInstructions
                                        ))

                                    val intent = Intent(
                                        this@activityDetail,
                                        activityDisplayRecipe::class.java
                                    )
                                    intent.putExtra("recipeList", recipeList2)
                                    startActivity(intent)
                                }
                            ) {
                                Text(text = "Delete")
                            }

                            Button(modifier = Modifier.padding(20.dp),
                                onClick = {

                                    var bolRemove: Boolean =DataHolder.arrayList.remove(
                                        Recipe(
                                            selectedName,
                                            selectedType,
                                            selectedIngredients,
                                            selectedInstructions
                                        ))

                                    recipeList2.add(Recipe(txtUsername,selecteddrinks,txtInstructions,txtIngredients))

                                    DataHolder.arrayList.add(Recipe(txtUsername,selecteddrinks,txtInstructions,txtIngredients))
                                    val intent = Intent(
                                        this@activityDetail,
                                        activityDisplayRecipe::class.java
                                    )
                                    startActivity(intent)
                                }
                            ){
                                Text(text = "Edit")
                            }
                        }
                    }

                }
            }
        }
    }
    @Composable
    fun Toolbar(
        title: String,
        navigationIcon: ImageVector?,
        onNavigationIconClick: () -> Unit
    ) {
        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                if (navigationIcon != null) {
                    IconButton(onClick = onNavigationIconClick) {
                        Icon(imageVector = navigationIcon, contentDescription = null)
                    }
                }
            },
            modifier = Modifier.padding(bottom = 4.dp)
        )
    }
}
