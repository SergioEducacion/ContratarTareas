package com.example.contratartareas.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.contratartareas.TareasViewModel
import com.example.contratartareas.data.DataSource
import com.example.contratartareas.data.Tarea
import com.example.contratartareas.data.TareasUIState

@Composable
fun PantallaTareas(tareaViewModel: TareasViewModel, uiState: TareasUIState, onClickCambiarPantalla: () -> Unit) {

    var tareasCargadas= DataSource.tareas
    Column(){
        TareasScroll(Modifier,tareasCargadas,tareaViewModel)
        Editor(Modifier, tareaViewModel)
        TextoActualizandose(Modifier, uiState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Editor(modifier: Modifier,tareaViewModel: TareasViewModel){
    Row(modifier = modifier.fillMaxWidth().padding(20.dp).height(150.dp)) {
        TextField(
            value = tareaViewModel.valorTextFieldNuevaTarea.value,
            singleLine = true,
            modifier = modifier.padding(16.dp).weight(1f).fillMaxSize(),
            //onValueChange = segundoTextEditor,
            onValueChange = { tareaViewModel.cambiarValorTextFieldNuevaTarea(it) },
            label = { Text("Nombre nueva tarea") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
            )
        )
        Button(
            onClick =
            {
                tareaViewModel.addNuevaTarea()
            },
            modifier = modifier.weight(1f).fillMaxSize()
        ) {
            Text(text = "Nueva tarea")
        }
    }
}


@Composable
private fun TextoActualizandose(
    modifier: Modifier,
    uiState: TareasUIState,
    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            //.height(200.dp)
            .background(Color.LightGray)
            //.weight(0.25f)
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ultima acción:\n"+uiState.accionUltima,
            modifier = modifier.background(Color.Yellow).fillMaxWidth()
        )
        Text(
            text = "Resumen:\n"+uiState.TareasAdquiridas,
            modifier = modifier.background(Color.White).fillMaxWidth()
        )
        Text(
            text = "Total horas: "+uiState.accionUltima,
            modifier = modifier.background(Color.Yellow).fillMaxWidth()
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CambiarPantalla(modifier: Modifier) {
    Button(
        onClick =
        {

        },
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "Cambio pantalla")
    }
}





@Composable
private fun TareasScroll(
    modifier: Modifier,
    tareas: ArrayList<Tarea>,
    tareaViewModel: TareasViewModel,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = modifier.height(300.dp)
            .fillMaxWidth()
    ) {
        items(tareas) { tarea ->
            Card(
                modifier = modifier
                    .padding(5.dp)

            ) {
                Text(
                    text = "Tarea: ${tarea.nombre}",
                    modifier = Modifier
                        .background(Color.Yellow)
                        .fillMaxWidth()
                        .padding(20.dp)
                )
                Text(
                    text = "€/hora: ${tarea.precio.toString()}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Cyan)
                        .padding(20.dp)
                )
                Row(          modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick =
                        {
                            tareaViewModel.addTareaCantidad(
                                tarea
                            )
                        },
                        modifier = Modifier
                    ) {
                        Text(text = "+")
                    }
                }
            }
        }
    }
}

