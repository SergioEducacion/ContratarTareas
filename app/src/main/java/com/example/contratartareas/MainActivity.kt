package com.example.contratartareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contratartareas.data.DataSource
import com.example.contratartareas.data.Tarea
import com.example.contratartareas.data.TareasUIState
import com.example.contratartareas.ui.PantallaTareas
import com.example.contratartareas.ui.theme.ContratarTareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContratarTareasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }
}


enum class PrincipalScreen(@StringRes val title: Int) {
    Pantalla1(title = R.string.p1),
    Pantalla2(title = R.string.p2),
}

@Composable
fun Principal(navController: NavHostController = rememberNavController()) {
    val viewModelTareas: TareasViewModel = viewModel()
    val uiState by viewModelTareas.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = PrincipalScreen.Pantalla1.name,
        //modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = PrincipalScreen.Pantalla1.name) {
            PantallaTareas(
                tareaViewModel = viewModelTareas,
                uiState = uiState,
                onClickCambiarPantalla = { navController.navigate(PrincipalScreen.Pantalla2.name) })
        }
        composable(route = PrincipalScreen.Pantalla2.name) {
            PantallaVacia()
        }
    }
}




@Composable
fun PantallaVacia() {
    TODO("Not yet implemented")
}


