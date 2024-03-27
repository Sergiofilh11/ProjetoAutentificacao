package com.example.projetoautentificacao

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.projetoautentificacao.ui.theme.ProjetoAutentificacaoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetoAutentificacaoTheme {
                Login()
            }
        }
    }
}

@Composable
fun Login() {
    var logado by remember {
        mutableStateOf(false)
    }

    val entrarLaunch = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if(it.resultCode == Activity.RESULT_OK) {
            logado = true
            Log.d("[HelloWorld]", "Logado")
        } else {
            logado = false
            Log.d("[Hello World]", "Não logado")
        }
    }

    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = if(logado) "Usuário logado" else "Usuário não logado")
        Button(onClick = {
            val intent = Intent(context, FormActivity::class.java)
            entrarLaunch.launch(intent)
        }) {
            Text(text = "Login")
        }
    }
}