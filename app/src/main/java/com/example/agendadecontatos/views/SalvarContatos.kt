package com.example.agendadecontatos.views

import WHITE
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agendadecontatos.AppDatabase
import com.example.agendadecontatos.DAO.ContatoDao
import com.example.agendadecontatos.componentes.ButtonCustom
import com.example.agendadecontatos.componentes.OutlinedTextFieldCustom
import com.example.agendadecontatos.model.Contato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private lateinit var contatoDao: ContatoDao

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SalvarContatos(navController: NavController) {

    val listaContatos: MutableList<Contato> = mutableListOf()

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Salvar novo contato",
                        color = WHITE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextFieldCustom(
                value = nome,
                onValueChange = {
                    nome = it
                },
                label = {
                        Text(text = "Nome")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 150.dp, end = 20.dp, bottom = 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            OutlinedTextFieldCustom(
                value = sobrenome,
                onValueChange = {
                    sobrenome = it
                },
                label = {
                    Text(text = "Sobrenome")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, end = 20.dp, bottom = 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            OutlinedTextFieldCustom(
                value = idade,
                onValueChange = {
                    idade = it
                },
                label = {
                    Text(text = "Idade")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, end = 20.dp, bottom = 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextFieldCustom(
                value = celular,
                onValueChange = {
                    celular = it
                },
                label = {
                    Text(text = "Celular")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp, end = 20.dp, bottom = 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )

            ButtonCustom(
                onClick = {

                    var mensagem = false

                    scope.launch(Dispatchers.IO) {
                        if(nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || celular.isEmpty()) {
                            mensagem = true
                        } else {
                            val contato = Contato(nome, sobrenome, idade, celular)
                            listaContatos.add(contato)
                            contatoDao = AppDatabase.getInstance(context).contatoDao()
                            contatoDao.gravar(listaContatos)
                            mensagem = true
                            navController.navigate("listaContatos")
                        }
                    }

                    scope.launch(Dispatchers.Main) {
                        if(mensagem) {
                            Toast.makeText(context, "Sucesso ao salvar o contato!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                        }
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 0.dp),
                texto = "Salvar"
            )


        }
    }

}