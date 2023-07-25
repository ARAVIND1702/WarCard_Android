package com.example.cardgame

import android.app.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogScreen(){
    var isDialog by remember {
        mutableStateOf(false)
    }
    Column(
        modifier =  Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
       Button(onClick = {
          isDialog = true
       }) {
           Text(text = "Click Here")
       }
    }
    Dialog(onDismissRequest = {  isDialog = false }) {
          CircularProgressIndicator()
    }
}

//@Composable
//fun AlertDialogScreen(){
//    var isDialog by remember {
//        mutableStateOf(true)
//    }
////    Column(modifier = Modifier.fillMaxSize(),
////           horizontalAlignment = Alignment.CenterHorizontally,
////           verticalArrangement = Arrangement.Center
////
////    ){
////        Button(onClick = {
////            isDialog = true
////        }) {
////            Text(text = "Click Here")
////        }
////    }
//
//    if (isDialog) {
//        AlertDialog(
//            onDismissRequest = {
//                isDialog = false
//            },
//            title = {
//                Text("Victory", color = Color(0xFFF4725D), fontWeight = FontWeight.Bold ,fontSize = 34.sp)
//            },
//            icon ={ Image(painter = painterResource(id = R.drawable.trophy), contentDescription =null)} ,
//            text = {
//                Text("You Won the Game",textAlign = TextAlign.Center, fontSize = 16.sp , modifier = Modifier.padding(start = 50.dp))
//            },
//            confirmButton = {
//                Button(
//                    onClick = {
//                        isDialog = false
//                    }
//                ) {
//                    Text("Play Again")
//                }
//            }
//        )
//    }
//}











