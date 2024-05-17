package com.example.agendadecontatos

import AgendaDeContatosTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.agendadecontatos.views.AtualizarContato
import com.example.agendadecontatos.views.ListaContatos
import com.example.agendadecontatos.views.SalvarContatos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgendaDeContatosTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "listaContatos"
                ) {
                    composable("listaContatos") {
                        ListaContatos(navController = navController)
                    }

                    composable("salvarContatos") {
                        SalvarContatos(navController = navController)
                    }

                    composable(
                        "atualizarContato/{uid}",
                        arguments = listOf(navArgument("uid"){})
                        ) {
                        AtualizarContato(navController = navController, it.arguments?.getString("uid").toString())
                    }
                }

            }
        }
    }
}