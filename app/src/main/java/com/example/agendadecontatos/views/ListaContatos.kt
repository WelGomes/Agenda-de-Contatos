package com.example.agendadecontatos.views

import Purple500
import WHITE
import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendadecontatos.AppDatabase
import com.example.agendadecontatos.DAO.ContatoDao
import com.example.agendadecontatos.R
import com.example.agendadecontatos.itemlista.ContatoItem
import com.example.agendadecontatos.model.Contato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatoDao: ContatoDao

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun ListaContatos(navController: NavController) {

    val context = LocalContext.current

    val listarContatos: MutableList<Contato> = mutableListOf()

    val scope = rememberCoroutineScope()

    scope.launch(Dispatchers.IO) {
        contatoDao = AppDatabase.getInstance(context).contatoDao()
        val contatos = contatoDao.getContatos()

        for(contato in contatos) {
            listarContatos.add(contato)
        }
    }



    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                backgroundColor = Purple500,
                title = {
                    Text(
                        text = "Agenda de Contatos",
                        color = WHITE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Purple500,
                onClick = {
                    navController.navigate("salvarContatos")
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_adicionar),
                    contentDescription = "Adicionar novo contato",
                    tint = WHITE
                )
            }
        }
    ) {
        LazyColumn {
            itemsIndexed(listarContatos) {position, item ->
                ContatoItem(
                    navController = navController,
                    position = position,
                    listaContatos = listarContatos,
                    context = context
                )
            }
        }
    }
    
}