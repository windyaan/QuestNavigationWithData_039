package com.example.arsitekturmvvm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults.Thickness
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.arsitekturmvvm.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulirSiswa(
    //edit 1: parameter pilihanJK dan onSubmitButtonClicked
    pilihanJK:List<String> ,
    onSubmitButtonClicked : (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
){
    //edit 2: tambahkan 4 variable
    var txtNama by rememberSaveable { mutableStateOf("") }
    var txtAlamat by remember { mutableStateOf("") }
    var txtGender by remember { mutableStateOf("") }
    var listData: MutableList<String> = mutableListOf(txtNama,txtGender,txtAlamat)

    Scaffold(modifier = Modifier,
        { TopAppBar(
            title = { Text(stringResource(id = R.string.home), color = Color.White)},
            colors = TopAppBarDefaults.mediumTopAppBarColors(colorResource(id = R.color.purple_500))
        )}
    ){
            isiRuang ->
        Column (modifier = Modifier.padding(isiRuang),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //edit 3: value, onValueChange, selected, onClick
            OutlinedTextField(
                value = txtNama,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(250.dp),
                label = { Text(text = "Nama Lengkap")},
                onValueChange = {
                    txtNama = it
                },
            )

            HorizontalDivider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier
                    .padding(top = 12.dp)
                    .width(250.dp)
            )

            Row {
                pilihanJK.forEach { item ->
                    Row(modifier = Modifier.selectable(
                        selected = txtGender == item,
                        onClick = {
                            txtGender = item
                        }
                    ),
                        verticalAlignment = Alignment.CenterVertically){
                        RadioButton(
                            selected = txtGender == item,
                            onClick = {
                                txtGender = item
                            }
                        )
                        Text(item)
                    }
                }
            }
            HorizontalDivider(modifier = Modifier
                .padding(top = 20.dp)
                .width(250.dp),
                thickness = 1.dp,
                color = Color.Red
            )
            OutlinedTextField(
                value = txtAlamat,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(250.dp),
                label = { Text(text = "Alamat Lengkap")},
                onValueChange = {
                    txtAlamat = it
                },
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(1f),
                enabled = txtAlamat.isNotEmpty(),
                onClick = { onSubmitButtonClicked(listData) }
            ) {
                Text(text = stringResource(id = R.string.submit))
            }
        }
    }
}