package com.example.contratartareas

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.contratartareas.data.DataSource
import com.example.contratartareas.data.Tarea
import com.example.contratartareas.data.TareaHoras
import com.example.contratartareas.data.TareasUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class TareasViewModel: ViewModel()  {

    private val tareas = DataSource.tareas
    private val tareasHoras= arrayListOf<TareaHoras>()
    private val _uiState = MutableStateFlow(TareasUIState())
    val uiState: StateFlow<TareasUIState> = _uiState.asStateFlow()

    var valorTextFieldNuevaTarea: MutableState<String> = mutableStateOf("")
        private set


    fun addTareaCantidad(tarea: Tarea) {

        var tareaHora=tareasHoras.firstOrNull({ it.tarea==tarea})
        if(tareaHora==null){
            tareaHora=TareaHoras(tarea,0)
            tareasHoras.add(tareaHora)
        }

            tareaHora.cantidad++
            var ultimaAccion="Se añade 1 hora a ${tareaHora.tarea.nombre}"
            

    }

    fun cambiarValorTextFieldNuevaTarea(it: String) {
        valorTextFieldNuevaTarea.value=it
    }

    fun addNuevaTarea() {
        val nuevoNombre =valorTextFieldNuevaTarea.value
        if(nuevoNombre.isBlank() ||nuevoNombre.isEmpty()){
            cambiarSoloAccionUltimaUIState("El valor es vacio o blanco")
            return;
        }

        if(tareas.firstOrNull({ it.nombre.compareTo(nuevoNombre)==0}) != null){
            cambiarSoloAccionUltimaUIState("Ya existe una tarea llamada así")
            return;
        }

        tareas.add(Tarea(nuevoNombre,10))
        cambiarSoloAccionUltimaUIState("Añadida la tarea: ${nuevoNombre}")
        valorTextFieldNuevaTarea.value="" // Lo vaciamos
    }


    private fun cambiarSoloAccionUltimaUIState(texto: String) {
        _uiState.update { currentState ->
            currentState.copy(
                accionUltima = texto
            )
        }
    }
}