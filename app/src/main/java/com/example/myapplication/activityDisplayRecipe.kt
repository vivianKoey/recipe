package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class activityDisplayRecipe : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var recipeList = ArrayList<Recipe>()
                    val arrayList = DataHolder.arrayList


                    Column {
                        Toolbar(
                            title = "Recipe List",
                            navigationIcon = Icons.Default.ArrowBack,
                            onNavigationIconClick = {

                                val intent = Intent(this@activityDisplayRecipe, activityReceipe::class.java)

                                startActivity(intent)
                            })
                        val selectedName = remember { mutableStateOf("Name") }
                        val selectedType = remember { mutableStateOf("Type") }
                        val selectedIngredients = remember { mutableStateOf("Ingredients") }
                        val selectedInstructions = remember { mutableStateOf("Instructions") }

                        LazyColumn {

                            itemsIndexed(arrayList) { index, item ->

                                var emID = arrayList[index].name
                                var emType=arrayList[index].type
                                var emIngredients=arrayList[index].ingredients
                                var emInstructions=arrayList[index].instructions
                                Card(
                                    modifier = Modifier.padding(8.dp)
                                        .clickable {
                                            selectedName.value = emID
                                            selectedType.value=emType
                                            selectedIngredients.value=emIngredients
                                            selectedInstructions.value=emInstructions

                                            var recipeList2 = ArrayList<Recipe>()
                                            recipeList2.add(Recipe(selectedName.value,selectedType.value,selectedInstructions.value,selectedIngredients.value))

                                            val intent = Intent(
                                                this@activityDisplayRecipe,
                                                activityDetail::class.java
                                            )
                                            intent.putExtra("recipeList2", recipeList2)
                                            intent.putExtra("recipeList3", recipeList)
                                            startActivity(intent)


                                        },
                                    elevation = 6.dp,

                                ) {
                                    Row {
                                        Column(
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .fillMaxWidth(),
                                            horizontalAlignment = Alignment.Start,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "Name: " + emID,

                                                modifier = Modifier.padding(4.dp),

                                                color = Color.Black, textAlign = TextAlign.Center
                                            )

                                            Spacer(modifier = Modifier.width(5.dp))

                                            Text(

                                                text = "Type : " + emType,

                                                modifier = Modifier.padding(4.dp),

                                                color = Color.Black, textAlign = TextAlign.Center
                                            )

                                            Spacer(modifier = Modifier.width(5.dp))

                                            Text(
                                                text = "Ingredients : " + emIngredients,


                                                modifier = Modifier.padding(4.dp),

                                                color = Color.Black, textAlign = TextAlign.Center
                                            )

                                            Spacer(modifier = Modifier.width(5.dp))

                                            Text(

                                                text = "Instructions : " + emInstructions,

                                                modifier = Modifier.padding(4.dp),

                                                color = Color.Black, textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
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


