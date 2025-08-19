package com.example.arsitekturmvvm.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.arsitekturmvvm.model.DataJK.JenisK
import com.example.arsitekturmvvm.view.FormulirSiswa
import com.example.arsitekturmvvm.view.TampilSiswa
import com.example.arsitekturmvvm.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulir,
    Detail
}

@Composable
fun SiswaApp(
    //edit1 : parameter viewModel
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { isiRuang->
        //edit 2: tambahkan variable uiState
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,

            modifier = Modifier.padding(isiRuang)){
            composable(route = Navigasi.Formulir.name){
                //edit 3: tambahkan var konteks
                val konteks = LocalContext.current
                FormulirSiswa (
                    //edit 4: parameter pilihanJK dan onSubmitButtonClicked
                    pilihanJK = JenisK.map{ id -> konteks.resources.getString(id) },
                    onSubmitButtonClicked = {
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name){
                TampilData(
                    onBackBtnClick = {cancelAndBackToFormulir(navController)}
                )
            }
        }
    }
}

private fun cancelAndBackToFormulir(
    navController: NavHostController
){
    navController.popBackStack(Navigasi.Formulir.name, inclusive = false)
}