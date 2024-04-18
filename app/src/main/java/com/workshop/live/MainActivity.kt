package com.workshop.live

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.workshop.live.ui.theme.WorkshopLiveTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                setContent {
                    WorkshopLiveTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            when(state) {
                                is MainActivityViewModel.State.Idle -> MostrarMensaje("Está cargando")
                                is MainActivityViewModel.State.Success -> MostrarPersonaje(state.characters)
                                is MainActivityViewModel.State.Error -> MostrarMensaje("Ha habid un error")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MostrarPersonaje(personajes: List<Character>){
    LazyColumn {
        item {
            Text(
                text = "Listado de personajes",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Red,
            )
        }
        items(personajes) {
            Text(text = "Soy ${it.name} y mido ${it.height}")
        }
    }
}

@Composable
fun MostrarMensaje(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MostrarPersonajesPreview() {
    WorkshopLiveTheme {
        MostrarPersonaje(
            listOf(
                Character("Personaje1", "180"),
                Character("Personaje2", "160")
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorkshopLiveTheme {
        MostrarMensaje("Android")
    }
}